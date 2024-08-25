package com.marketplace.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findByName(String name) {
        try {
            return repository.findByName(name).orElseThrow();
        } catch (Exception e) {
            return repository.save(new Category(name));
        }
    }
}
