package com.app.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class CategoryDto {
    private Long id;
    private String name;

    public static CategoryDto.CategoryDtoBuilder builder() {
        return new CategoryDtoBuilder();
    }

    public CategoryDto(CategoryDtoBuilder categoryDtoBuilder) {
        this.id = categoryDtoBuilder.id;
        this.name = categoryDtoBuilder.name;
    }

    public static class CategoryDtoBuilder {
        private Long id;
        private String name;

        private static final String NAME_REGEX = "[A-Z ]+";

        public CategoryDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CategoryDtoBuilder name(String name) {
            this.name = name != null && name.matches(NAME_REGEX) ? name : null;
            return this;
        }

        public CategoryDto build() {
            return new CategoryDto(this);
        }
    }
}
