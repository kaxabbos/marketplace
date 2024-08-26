package com.marketplace.product;

import com.marketplace.appUser.UserService;
import com.marketplace.category.CategoryService;
import com.marketplace.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final UserService userService;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAllByBind(String bind) {
        System.out.println(repository.findAllByBindAndOwner_Id(bind, userService.getCurrentUser().getId()).size());
        return repository.findAllByBindAndOwner_Id(bind, userService.getCurrentUser().getId());
    }


    public Product findById(String id) {
        try {
            return repository.findById(Long.parseLong(id)).orElseThrow();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Товар не найден по ИД " + id);
        }
    }

    public Product save(Product save, String category) {
        save.setCategory(categoryService.findByName(category));
        save.setOwner(userService.getCurrentUser());
        return repository.save(save);
    }

    public Product update(String productId, Product update, String category) {
        Product old = findById(productId);
        old.set(update);
        old.setCategory(categoryService.findByName(category));
        return repository.save(old);
    }

    public void deleteById(String productId) {
        Product product = findById(productId);
        repository.deleteById(product.getId());
    }

}

