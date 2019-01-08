package com.app.service;

import com.app.dto.CategoryDto;
import com.app.dto.MyModelMapper;
import com.app.exceptions.MyException;
import com.app.model.Category;
import com.app.repository.CategoryRepository;
import com.app.repository.CategoryRepositoryImpl;

import java.time.LocalDateTime;

public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addCategory(CategoryDto categoryDto) {
        try {
            Category category = modelMapper.fromCategoryDtoToCategory(categoryDto);

            if (isCategory(category.getName())) {
                throw new MyException("CATEGORY ALREADY IN DB", LocalDateTime.now());
            }

            if (category.getName() == null) {
                throw new NullPointerException();
            }

            categoryRepository.addOrUpdate(category);

        } catch (Exception e) {
            throw new MyException("SERVICE, ADD CATEGORY", LocalDateTime.now());
        }
    }

    private boolean isCategory(String name) {
        return categoryRepository.findByName(name).isPresent();
    }

    public void newCategory(String name) {
        addCategory(CategoryDto.builder()
                .name(name)
                .build());
    }

    public void deleteCategory(String name) {
        categoryRepository.delete(categoryRepository
                .findByName(name)
                .orElseThrow(NullPointerException::new)
                .getId());
    }

    public void updateCategory(String name, String newName) {
        Category category = categoryRepository.findByName(name).orElseThrow(NullPointerException::new);
        category.setName(newName);
        categoryRepository.addOrUpdate(category);
    }

    public void showAll() {
        categoryRepository.findAll().forEach(
                category -> System.out.println(
                        "CATEGORY: id: " + category.getId() +
                                ", name: " + category.getName()));
    }
}
