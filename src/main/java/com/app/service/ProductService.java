package com.app.service;

import com.app.dto.DataManager;
import com.app.dto.MyModelMapper;
import com.app.dto.ProductDto;
import com.app.exceptions.MyException;
import com.app.model.Category;
import com.app.model.EGuarantee;
import com.app.model.Producer;
import com.app.model.Product;
import com.app.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ProductService {
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addProduct(ProductDto productDto) {
        try {
            Product product = modelMapper.fromProductDtoToProduct(productDto);

            if (isProduct(product.getName(), product.getCategory().getName(), product.getProducer().getName())) {
                throw new MyException("PRODUCT ALREADY IN DB", LocalDateTime.now());
            }

            if (product.getCategory() == null) {
                throw new NullPointerException();
            }

            Category category
                    = categoryRepository
                    .findByName(product.getCategory().getName())
                    .orElseThrow(NullPointerException::new);

            product.setCategory(category);

            if (product.getProducer() == null) {
                throw new NullPointerException();
            }

            Producer producer
                    = producerRepository
                    .findByName(product.getProducer().getName())
                    .orElseThrow(NullPointerException::new);

            product.setProducer(producer);

            productRepository.addOrUpdate(product);

        } catch (Exception e) {
            throw new MyException("SERVICE, ADD PRODUCT", LocalDateTime.now());
        }
    }

    private boolean isProduct(String name, String category, String producer) {
        return productRepository.findByNameCategoryProducer(name, category, producer).isPresent();
    }

    public void newProduct(DataManager dataManager) {
        Set<EGuarantee> guaranteeComponents = new HashSet<>();
        for (String s : dataManager.getGuaranteeComponents()) {
            guaranteeComponents.add(EGuarantee.valueOf(s));
        }

        addProduct(ProductDto.builder()
                .name(dataManager.getName())
                .price(BigDecimal.valueOf(dataManager.getPrice()))
                .categoryDto(modelMapper.fromCategoryToCategoryDto(categoryRepository
                        .findByName(dataManager.getCategory())
                        .orElseThrow(MyException::new)))
                .guaranteeComponents(new HashSet<>(guaranteeComponents))
                .producerDto(modelMapper.fromProducerToProducerDto(
                        producerRepository.findByName(dataManager.getProducer())
                                .orElseThrow(NullPointerException::new)))
                .build());
    }

    public void deleteProduct(String name, String category, String producer) {
        producerRepository.delete(productRepository.findByNameCategoryProducer(
                name, category, producer)
                .orElseThrow(NullPointerException::new).getId());
    }

    public void updateProduct() {

    }

    public void showAll() {
        productRepository.findAll().forEach(
                product -> System.out.println(
                        "PRODUCT: \t" + product.getName() + " price: " + product.getPrice() +
                                " category: " + product.getCategory() + " producer: " +
                                product.getProducer().getName()
                )
        );
    }
}