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

    public Product save(Product product, String category) {
        product.setCategory(categoryService.findByName(category));
        return repository.save(product);
    }

    public Product update(String productId, Product product, String category) {
        Product old = findById(productId);
        old.set(product);
        old.setCategory(categoryService.findByName(category));
        return repository.save(old);
    }

    public void delete(String productId) {
        Product product = findById(productId);
        repository.deleteById(product.getId());
    }

}
