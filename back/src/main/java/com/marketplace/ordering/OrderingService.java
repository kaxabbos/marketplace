package com.marketplace.ordering;

import com.marketplace.appUser.AppUser;
import com.marketplace.appUser.UserService;
import com.marketplace.enums.Role;
import com.marketplace.product.Product;
import com.marketplace.product.ProductService;
import com.marketplace.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderingService {

    private final OrderingRepository repository;
    private final UserService userService;
    private final ProductService productService;

    public Ordering findById(String id) {
        try {
            return repository.findById(Long.parseLong(id)).orElseThrow();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Заказ не найден по ИД " + id);
        }
    }

    public List<Ordering> findAll() {
        AppUser user = userService.getCurrentUser();
        if (user.getRole() == Role.SELLER) return repository.findAllByProduct_Owner_Id(user.getId());
        return user.getOrderings();
    }

    public Ordering save(int count, String productId) {
        AppUser user = userService.getCurrentUser();
        Product product = productService.findById(productId);
        return repository.save(new Ordering(count, product, user));
    }

}
