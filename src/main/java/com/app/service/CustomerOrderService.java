package com.app.service;

import com.app.dto.CustomerOrderDto;
import com.app.dto.DataManager;
import com.app.dto.MyModelMapper;
import com.app.exceptions.MyException;
import com.app.model.*;
import com.app.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomerOrderService {
    private CustomerOrderRepository customerOrderRepository = new CustomerOrderRepositoryImpl();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private StockRepository stockRepository = new StockRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addCustomerOrder(CustomerOrderDto customerOrderDto) {
        try {
            CustomerOrder customerOrder = modelMapper.fromCustomerOrderDtoToCustomer(customerOrderDto);

            if (customerOrder.getCustomer() == null) {
                throw new NullPointerException();
            }

            if (customerOrder.getDate() == null) {
                customerOrder.setDate(LocalDateTime.now());
            }

            if (customerOrder.getQuantity() == null) {
                throw new NullPointerException();
            }

            if (customerOrder.getDiscount() == null) {
                customerOrder.setDiscount(BigDecimal.ZERO);
            }

            Payment payment = paymentRepository
                    .findById(customerOrder.getPayment().getId())
                    .orElseThrow(NullPointerException::new);

            customerOrder.setPayment(payment);

            Customer customer
                    = customerRepository
                    .findByNameAndSurname(customerOrder.getCustomer().getName(),
                            customerOrder.getCustomer().getSurname())
                    .orElseThrow(NullPointerException::new);

            customerOrder.setCustomer(customer);

            Stock stock = stockRepository
                    .findByProduct(customerOrder.getProduct())
                    .orElseThrow(NullPointerException::new);

            if (customerOrder.getQuantity() > stock.getQuantity()) {
                throw new MyException("CUSTOMERORDER, QUANTITY HIGHER THAN STOCK QUANTITY", LocalDateTime.now());
            }

            if (customerOrder.getProduct() == null) {
                throw new NullPointerException();
            }

            Product product
                    = productRepository
                    .findByName(customerOrder.getProduct().getName())
                    .orElseThrow(NullPointerException::new);

            customerOrder.setProduct(product);

            customerOrderRepository.addOrUpdate(customerOrder);

            stock.setQuantity(stock.getQuantity() - customerOrder.getQuantity());
            stockRepository.addOrUpdate(stock);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("SERVICE, ADD CUSTOMER ORDER", LocalDateTime.now());
        }
    }

    public void newCustomerOrder(DataManager dataManager) {
        EPayment ePayment = EPayment.valueOf(dataManager.getPayment());
        addCustomerOrder(CustomerOrderDto.builder()
                .customerDto(modelMapper.fromCustomerToCustomerDto(customerRepository
                        .findByNameAndSurname(dataManager.getName(), dataManager.getSurname())
                        .orElseThrow(NullPointerException::new)))
                .productDto(modelMapper.fromProductToProductDto(productRepository
                        .findByNameCategory(dataManager.getProduct(), dataManager.getCategory())
                        .orElseThrow(NullPointerException::new)))
                .quantity(dataManager.getQuantity())
                .paymentDto(modelMapper.fromPaymentToPaymentDto(paymentRepository
                        .findByName(ePayment).orElseThrow(NullPointerException::new)))
                .discount(BigDecimal.valueOf(dataManager.getDiscount()))
                .build());
    }

    public void deleteCustomerOrder(Long id) {
        customerOrderRepository.delete(customerOrderRepository
                .findById(id).orElseThrow(NumberFormatException::new).getId());
    }

    public void updateCustomerOrder(Long id, DataManager dataManager) {

        CustomerOrder customerOrder = customerOrderRepository
                .findById(id)
                .orElseThrow(NullPointerException::new);

        if (dataManager.getName() != null || dataManager.getSurname() != null) {
            customerOrder.setCustomer(Customer.builder()
                    .name(dataManager.getName())
                    .surname(dataManager.getSurname())
                    .build());
        }
        if (dataManager.getCategory() != null && dataManager.getProduct() != null) {
            customerOrder.setProduct(productRepository
                    .findByNameCategory(dataManager.getName(), dataManager.getCategory())
                    .orElseThrow(NullPointerException::new));
        }
        if (dataManager.getQuantity() != null) {
            customerOrder.setQuantity(dataManager.getQuantity());
        }
        if (dataManager.getPayment() != null) {
            customerOrder.setPayment(paymentRepository
                    .findByName(EPayment.valueOf(dataManager.getPayment()))
                    .orElseThrow(NullPointerException::new));
        }
        if (dataManager.getDiscount() != null) {
            customerOrder.setDiscount(BigDecimal.valueOf(dataManager.getDiscount()));
        }

        if (dataManager.getDate() != null) {
            customerOrder.setDate(dataManager.getLocalDateTime());
        }

        customerOrderRepository.addOrUpdate(customerOrder);

    }

    public void showAll() {
        customerOrderRepository.findAll().forEach(
                customerOrder -> System.out.println(
                        "Customer order: \t id: " + customerOrder.getId() +
                                ", date: " + customerOrder.getDate() +
                                ", quantity: " + customerOrder.getQuantity() +
                                ", customer: " + customerOrder.getCustomer().getName() + " " + customerOrder.getCustomer().getSurname() +
                                ", payment: " + customerOrder.getPayment().getEPayment()
                ));
    }
}
