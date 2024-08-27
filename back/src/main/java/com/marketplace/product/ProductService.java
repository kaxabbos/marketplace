package com.marketplace.product;

import com.marketplace.appUser.UserService;
import com.marketplace.category.CategoryService;
import com.marketplace.enums.ProductStatus;
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

    public List<Product> findAllByBind(String bind, String ownerId) {
        return repository.findAllByBindAndOwner_Id(bind, userService.findById(ownerId).getId());
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
        userService.checkOwner(old.getOwner().getId());
        old.set(update);
        old.setCategory(categoryService.findByName(category));
        return repository.save(old);
    }

    public void deleteById(String productId) {
        Product delete = findById(productId);
        userService.checkOwner(delete.getOwner().getId());
        repository.deleteById(delete.getId());
    }

    public Product active(String id) {
        Product product = findById(id);
        product.setStatus(ProductStatus.ACTIVE);
        return repository.save(product);
    }

    public Product refine(String id, String refine) {
        Product product = findById(id);
        product.setRefine(refine);
        product.setStatus(ProductStatus.REFINE);
        return repository.save(product);
    }

    public Product waiting(String id) {
        Product product = findById(id);
        userService.checkOwner(product.getOwner().getId());
        product.setStatus(ProductStatus.WAITING);
        return repository.save(product);
    }

    public Product archive(String id) {
        Product product = findById(id);
        userService.checkOwner(product.getOwner().getId());
        product.setStatus(ProductStatus.ARCHIVE);
        return repository.save(product);
    }
}
