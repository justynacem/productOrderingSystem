package com.app.dto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class CustomerOrderDto {
    private Long id;
    private LocalDateTime date;
    private BigDecimal discount;
    private Integer quantity;
    private CustomerDto customerDto;
    private ProductDto productDto;
    private PaymentDto paymentDto;

    public static CustomerOrderDto.CustomerOrderDtoBuilder builder() {
        return new CustomerOrderDtoBuilder();
    }

    public CustomerOrderDto(CustomerOrderDtoBuilder customerOrderDtoBuilder) {
        this.id = customerOrderDtoBuilder.id;
        this.date = customerOrderDtoBuilder.date;
        this.discount = customerOrderDtoBuilder.discount;
        this.quantity = customerOrderDtoBuilder.quantity;
        this.customerDto = customerOrderDtoBuilder.customerDto;
        this.productDto = customerOrderDtoBuilder.productDto;
        this.paymentDto = customerOrderDtoBuilder.paymentDto;
    }

    public static class CustomerOrderDtoBuilder {
        private Long id;
        private LocalDateTime date;
        private BigDecimal discount;
        private Integer quantity;
        private CustomerDto customerDto;
        private ProductDto productDto;
        private PaymentDto paymentDto;

        public CustomerOrderDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerOrderDtoBuilder date(LocalDateTime date) {
            this.date = date.isAfter(LocalDateTime.now()) || date.isEqual(LocalDateTime.now()) ? date : null;
            return this;
        }

        public CustomerOrderDtoBuilder discount(BigDecimal discount) {
            this.discount = discount.compareTo(BigDecimal.ZERO) > 0
                    && discount.compareTo(BigDecimal.valueOf(1.1)) < 0 ? discount : null;
            return this;
        }

        public CustomerOrderDtoBuilder quantity(Integer quantity) {
            this.quantity = quantity > 0 ? quantity : null;
            return this;
        }

        public CustomerOrderDtoBuilder customerDto(CustomerDto customerDto) {
            this.customerDto = customerDto == null ? null : customerDto;
            return this;
        }

        public CustomerOrderDtoBuilder productDto(ProductDto productDto) {
            this.productDto = productDto == null ? null : productDto;
            return this;
        }

        public CustomerOrderDtoBuilder paymentDto(PaymentDto paymentDto) {
            this.paymentDto = paymentDto;
            return this;
        }

        public CustomerOrderDto build() {
            return new CustomerOrderDto(this);
        }
    }
}
