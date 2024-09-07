package com.marketplace.ordering;

import com.marketplace.appUser.AppUser;
import com.marketplace.appUser.UserService;
import com.marketplace.enums.OrderingStatus;
import com.marketplace.enums.Role;
import com.marketplace.product.Product;
import com.marketplace.product.ProductService;
import com.marketplace.system.exception.BadRequestException;
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

    public Ordering update(String id, int count) {
        Ordering ordering = findById(id);
        ordering.setCount(count);
        return repository.save(ordering);
    }

    public Ordering ordered(String id) {
        Ordering ordering = findById(id);

        userService.checkOwner(ordering.getOwner().getId());

        ordering.setStatus(OrderingStatus.ORDERED);
        ordering.setPrice(ordering.getProduct().getPrice());

        return repository.save(ordering);
    }

    public Ordering done(String id) {
        Ordering ordering = findById(id);

        userService.checkOwner(ordering.getProduct().getOwner().getId());

        if (ordering.getCount() > ordering.getProduct().getCount()) {
            throw new BadRequestException("Недостаточное количество");
        }

        ordering.setStatus(OrderingStatus.DONE);

        ordering.getProduct().setCount(ordering.getProduct().getCount() - ordering.getCount());

        return repository.save(ordering);
    }

    public Ordering rejected(String id) {
        Ordering ordering = findById(id);

        userService.checkOwner(ordering.getProduct().getOwner().getId());

        ordering.setStatus(OrderingStatus.REJECTED);

        return repository.save(ordering);
    }

    public Ordering delivered(String id) {
        Ordering ordering = findById(id);

        userService.checkOwner(ordering.getProduct().getOwner().getId());

        ordering.setStatus(OrderingStatus.DELIVERED);
        return repository.save(ordering);
    }

    public Ordering accepted(String id) {
        Ordering ordering = findById(id);

        userService.checkOwner(ordering.getOwner().getId());

        ordering.setStatus(OrderingStatus.ACCEPTED);
        return repository.save(ordering);
    }

}
