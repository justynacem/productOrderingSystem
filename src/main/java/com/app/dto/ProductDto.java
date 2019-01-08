package com.app.dto;

import com.app.model.EGuarantee;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

//@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private CategoryDto categoryDto;
    private ProducerDto producerDto;
    private Set<EGuarantee> guaranteeComponents;

    public static ProductDto.ProductDtoBuilder builder() {
        return new ProductDtoBuilder();
    }


    public ProductDto(ProductDtoBuilder productDtoBuilder) {
        this.id = productDtoBuilder.id;
        this.name = productDtoBuilder.name;
        this.price = productDtoBuilder.price;
        this.categoryDto = productDtoBuilder.categoryDto;
        this.producerDto = productDtoBuilder.producerDto;
        this.guaranteeComponents = productDtoBuilder.guaranteeComponents;
    }

    public static class ProductDtoBuilder {
        private Long id;
        private String name;
        private BigDecimal price;
        private CategoryDto categoryDto;
        private ProducerDto producerDto;
        private Set<EGuarantee> guaranteeComponents;

        private static final String NAME_REGEX = "[A-Z ]+";

        public ProductDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductDtoBuilder name(String name) {
            this.name = name != null && name.matches(NAME_REGEX) ? name : null;
            return this;
        }

        public ProductDtoBuilder price(BigDecimal price) {
            this.price = price.compareTo(BigDecimal.ZERO) > 0 ? price : null;
            return this;
        }

        public ProductDtoBuilder categoryDto(CategoryDto categoryDto) {
            this.categoryDto = categoryDto == null ? null : categoryDto;
            return this;
        }

        public ProductDtoBuilder producerDto(ProducerDto producerDto) {
            this.producerDto = producerDto == null ? null : producerDto;
            return this;
        }

        public ProductDtoBuilder guaranteeComponents(Set<EGuarantee> guaranteeComponents) {
            this.guaranteeComponents = guaranteeComponents;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
