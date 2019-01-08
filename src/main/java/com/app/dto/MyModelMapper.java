package com.app.dto;

import com.app.model.*;

import java.util.HashSet;

public class MyModelMapper {
    public CategoryDto fromCategoryToCategoryDto(Category category) {
        return category == null ? null : CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return categoryDto == null ? null : Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .products(new HashSet<>())
                .build();
    }

    public CountryDto fromCountryToCountryDto(Country country) {
        return country == null ? null : CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }

    public Country fromCountryDtoToCountry(CountryDto countryDto) {
        return countryDto == null ? null : Country.builder()
                .id(countryDto.getId())
                .name(countryDto.getName())
                .customers(new HashSet<>())
                .producers(new HashSet<>())
                .shops(new HashSet<>())
                .build();
    }

    public CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return customer == null ? null : CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .age(customer.getAge())
                .countryDto(customer.getCountry() == null ? null : fromCountryToCountryDto(customer.getCountry()))
                .build();
    }

    public Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {
        return customerDto == null ? null : Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .age(customerDto.getAge())
                .country(customerDto.getCountryDto() == null ? null : fromCountryDtoToCountry(customerDto.getCountryDto()))
                .build();
    }

    public CustomerOrderDto fromCustomerOrderToCustomerOrderDto(CustomerOrder customerOrder) {
        return customerOrder == null ? null : CustomerOrderDto.builder()
                .id(customerOrder.getId())
                .date(customerOrder.getDate())
                .discount(customerOrder.getDiscount())
                .quantity(customerOrder.getQuantity())
                .customerDto(customerOrder.getCustomer() == null ? null : fromCustomerToCustomerDto(customerOrder.getCustomer()))
                .productDto(customerOrder.getProduct() == null ? null : fromProductToProductDto(customerOrder.getProduct()))
                .productDto(customerOrder.getProduct() == null ? null : fromProductToProductDto(customerOrder.getProduct()))
                .paymentDto(customerOrder.getPayment() == null ? null : fromPaymentToPaymentDto(customerOrder.getPayment()))
                .build();
    }

    public CustomerOrder fromCustomerOrderDtoToCustomer(CustomerOrderDto customerOrderDto) {
        return customerOrderDto == null ? null : CustomerOrder.builder()
                .id(customerOrderDto.getId())
                .date(customerOrderDto.getDate())
                .discount(customerOrderDto.getDiscount())
                .quantity(customerOrderDto.getQuantity())
                .customer(customerOrderDto.getCustomerDto() == null ? null : fromCustomerDtoToCustomer(customerOrderDto.getCustomerDto()))
                .product(customerOrderDto.getProductDto() == null ? null : fromProductDtoToProduct(customerOrderDto.getProductDto()))
                .payment(customerOrderDto.getPaymentDto() == null ? null : fromPaymentDtoToPayment(customerOrderDto.getPaymentDto()))
                .build();
    }

    public ExceptionModelDto fromExceptionModelToExceptionModelDto(ExceptionModel exceptionModel) {
        return exceptionModel == null ? null : ExceptionModelDto.builder()
                .exceptionMessage(exceptionModel.getExceptionMessage())
                .exceptionDateTime(exceptionModel.getExceptionDateTime())
                .build();
    }

    public ExceptionModel fromExceptionModelDtoToExceptionModel(ExceptionModelDto exceptionModelDto) {
        return exceptionModelDto == null ? null : ExceptionModel.builder()
                .exceptionMessage(exceptionModelDto.getExceptionMessage())
                .exceptionDateTime(exceptionModelDto.getExceptionDateTime())
                .build();
    }


    public PaymentDto fromPaymentToPaymentDto(Payment payment) {
        return payment == null ? null : PaymentDto.builder()
                .id(payment.getId())
                .ePayment(payment.getEPayment())
                .build();
    }

    public Payment fromPaymentDtoToPayment(PaymentDto paymentDto) {
        return paymentDto == null ? null : Payment.builder()
                .id(paymentDto.getId())
                .ePayment(paymentDto.getEPayment())
                .customerOrders(new HashSet<>())
                .build();
    }

    public ProducerDto fromProducerToProducerDto(Producer producer) {
        return producer == null ? null : ProducerDto.builder()
                .id(producer.getId())
                .name(producer.getName())
                .countryDto(producer.getCountry() == null ? null : fromCountryToCountryDto(producer.getCountry()))
                .tradeDto(producer.getTrade() == null ? null : fromTradeToTradeDto(producer.getTrade()))
                .build();
    }

    public Producer fromProducerDtoToProducer(ProducerDto producerDto) {
        return producerDto == null ? null : Producer.builder()
                .id(producerDto.getId())
                .name(producerDto.getName())
                .country(producerDto.getCountryDto() == null ? null : fromCountryDtoToCountry(producerDto.getCountryDto()))
                .trade(producerDto.getTradeDto() == null ? null : fromTradeDtoToTrade(producerDto.getTradeDto()))
                .products(new HashSet<>())
                .build();
    }

    public ProductDto fromProductToProductDto(Product product) {
        return product == null ? null : ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .categoryDto(product.getCategory() == null ? null : fromCategoryToCategoryDto(product.getCategory()))
                .producerDto(product.getProducer() == null ? null : fromProducerToProducerDto(product.getProducer()))
                .guaranteeComponents(product.getGuaranteeComponents())
                .build();
    }

    public Product fromProductDtoToProduct(ProductDto productDto) {
        return productDto == null ? null : Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(productDto.getCategoryDto() == null ? null : fromCategoryDtoToCategory(productDto.getCategoryDto()))
                .producer(productDto.getProducerDto() == null ? null : fromProducerDtoToProducer(productDto.getProducerDto()))
                .guaranteeComponents(new HashSet<>())
                .customerOrders(new HashSet<>())
                .stocks(new HashSet<>())
                .build();
    }

    public Shop fromShopDtoToShop(ShopDto shopDto) {
        return shopDto == null ? null : Shop.builder()
                .id(shopDto.getId())
                .name(shopDto.getName())
                .country(shopDto.getCountryDto() == null ? null : fromCountryDtoToCountry(shopDto.getCountryDto()))
                .stocks(new HashSet<>())
                .build();
    }

    public ShopDto fromShopToShopDto(Shop shop) {
        return shop == null ? null : ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .countryDto(shop.getCountry() == null ? null : fromCountryToCountryDto(shop.getCountry()))
                .build();
    }

    public StockDto fromStockToStockDto(Stock stock) {
        return stock == null ? null : StockDto.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .productDto(stock.getProduct() == null ? null : fromProductToProductDto(stock.getProduct()))
                .shopDto(stock.getShop() == null ? null : fromShopToShopDto(stock.getShop()))
                .build();
    }

    public Stock fromStockDtoToStock(StockDto stockDto) {
        return stockDto == null ? null : Stock.builder()
                .id(stockDto.getId())
                .quantity(stockDto.getQuantity())
                .product(stockDto.getProductDto() == null ? null : fromProductDtoToProduct(stockDto.getProductDto()))
                .shop(stockDto.getShopDto() == null ? null : fromShopDtoToShop(stockDto.getShopDto()))
                .build();
    }

    public TradeDto fromTradeToTradeDto(Trade trade) {
        return trade == null ? null : TradeDto.builder()
                .id(trade.getId())
                .name(trade.getName())
                .build();
    }

    public Trade fromTradeDtoToTrade(TradeDto tradeDto) {
        return tradeDto == null ? null : Trade.builder()
                .id(tradeDto.getId())
                .name(tradeDto.getName())
                .producers(new HashSet<>())
                .build();
    }

}
