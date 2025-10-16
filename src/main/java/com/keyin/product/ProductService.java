package com.keyin.product;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product existing = getById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setQuantity(updated.getQuantity());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found: " + id);
        }
        repository.deleteById(id);
    }
}
