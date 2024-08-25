package com.marketplace.product;

import com.marketplace.category.CategoryService;
import com.marketplace.system.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(String id) {
        try {
            return repository.findById(Long.parseLong(id)).orElseThrow();
        } catch (Exception e) {
            throw new BadRequestException("Товар не найден по ИД " + id);
        }
    }

    public Product save(Product save, String category) {
        save.setCategory(categoryService.findByName(category));
        return repository.save(save);
    }

    public Product updateById(String productId, Product update, String category) {
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
