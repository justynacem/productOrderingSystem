package com.app.service;

import com.app.dto.CustomerDto;
import com.app.dto.DataManager;
import com.app.dto.MyModelMapper;
import com.app.exceptions.MyException;
import com.app.model.Country;
import com.app.model.Customer;
import com.app.repository.CountryRepository;
import com.app.repository.CountryRepositoryImpl;
import com.app.repository.CustomerRepository;
import com.app.repository.CustomerRepositoryImpl;

import java.time.LocalDateTime;

public class CustomerService {
    CountryRepository countryRepository = new CountryRepositoryImpl();
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addCustomer(CustomerDto customerDto) {
        try {
            Customer customer = modelMapper.fromCustomerDtoToCustomer(customerDto);

            if (isCustomer(customer.getName(), customer.getSurname())) {
                throw new MyException("CUSTOMER ALREADY IN DB", LocalDateTime.now());
            }

            if (customer.getName() == null) {
                throw new NullPointerException();
            }

            if (customer.getSurname() == null) {
                throw new NullPointerException();
            }

            if (customer.getCountry() == null) {
                throw new NullPointerException();
            }

            if (customer.getAge() == null) {
                throw new NullPointerException();
            }

            Country country
                    = countryRepository
                    .findByName(customer.getCountry().getName())
                    .orElseThrow(NullPointerException::new);

            customer.setCountry(country);

            customerRepository.addOrUpdate(customer);

        } catch (Exception e) {
            throw new MyException("SERVICE, ADD CUSTOMER", LocalDateTime.now());
        }
    }

    private boolean isCustomer(String name, String surname) {
        return customerRepository.findByNameAndSurname(name, surname).isPresent();
    }

    public void newCustomer(DataManager dataManager) {
        addCustomer(CustomerDto.builder()
                .name(dataManager.getName())
                .surname(dataManager.getSurname())
                .age(dataManager.getAge())
                .countryDto(modelMapper.fromCountryToCountryDto(countryRepository.findByName(dataManager.getCountry())
                        .orElseThrow(MyException::new)))
                .build());
    }

    public void deleteCustomer(String name, String surname) {
        customerRepository.delete(customerRepository
                .findByNameAndSurname(name, surname)
                .orElseThrow(NullPointerException::new).getId());
    }

    public void updateCustomer(String name, String surname, DataManager dataManager) {
        Customer customer = customerRepository
                .findByNameAndSurname(name, surname)
                .orElseThrow(NullPointerException::new);

        if (dataManager.getName() != null) {
            customer.setName(dataManager.getName());
        }

        if (dataManager.getSurname() != null) {
            customer.setSurname(dataManager.getSurname());
        }

        if (dataManager.getCountry() != null) {
            customer.setCountry(countryRepository
                    .findByName(dataManager
                            .getCountry())
                    .orElseThrow(NullPointerException::new));
        }

        if (dataManager.getAge() != null) {
            customer.setAge(dataManager.getAge());
        }

        customerRepository.addOrUpdate(customer);
    }

    public void showAll() {
        customerRepository.findAll().forEach(
                customer -> System.out.println(
                        "CUSTOMER: " + customer.getName() + " " +
                                customer.getSurname() +
                                ", age: " + customer.getAge() +
                                ", country: " + customer.getCountry().getName()
                )
        );
    }
}
