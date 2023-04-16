package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.observers.CartItemsObserver;
import ru.geekbrains.march.market.core.repositories.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CartItemsObserver cartItemsObserver;

    private final Map<Long, Product> productMap = new HashMap<>();

    public Page<Product> findAll(int page, int pageSize, Specification<Product> specification) {
        return productRepository.findAll(specification, PageRequest.of(page, pageSize));
    }

    public void deleteById(Long id) {
        cartItemsObserver.update(id);
        productMap.remove(id);
        productRepository.deleteById(id);
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория с названием: " + productDto.getCategoryTitle() + " не найдена")));
        product = productRepository.save(product);
        productMap.put(product.getId(), product);
    }

    public Optional<Product> findById(Long id) {
        if (productMap.containsKey(id)){
            return Optional.ofNullable(productMap.get(id));
        } else {
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Товар ненайден"));
            productMap.put(id, product);
            return Optional.ofNullable(product);
        }
    }
}
