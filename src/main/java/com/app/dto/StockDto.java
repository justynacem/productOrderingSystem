package com.app.dto;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class StockDto {
    private Long id;
    private Integer quantity;
    private ProductDto productDto;
    private ShopDto shopDto;

    public static StockDto.StockDtoBuilder builder() {
        return new StockDtoBuilder();
    }

    public StockDto(StockDtoBuilder stockDtoBuilder) {
        this.id = stockDtoBuilder.id;
        this.quantity = stockDtoBuilder.quantity;
        this.productDto = stockDtoBuilder.productDto;
        this.shopDto = stockDtoBuilder.shopDto;
    }

    public static class StockDtoBuilder {
        private Long id;
        private Integer quantity;
        private ProductDto productDto;
        private ShopDto shopDto;

        public StockDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public StockDtoBuilder quantity(Integer quantity) {
            this.quantity = quantity >= 0 ? quantity : null;
            return this;
        }

        public StockDtoBuilder productDto(ProductDto productDto) {
            this.productDto = productDto;
            return this;
        }

        public StockDtoBuilder shopDto(ShopDto shopDto) {
            this.shopDto = shopDto;
            return this;
        }

        public StockDto build() {
            return new StockDto(this);
        }
    }
}
