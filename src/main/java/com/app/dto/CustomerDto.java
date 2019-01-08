package com.app.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class CustomerDto {
    private Long id;
    private Integer age;
    private String name;
    private String surname;
    private CountryDto countryDto;

    public static CustomerDto.CustomerDtoBuilder builder() {
        return new CustomerDtoBuilder();
    }

    public CustomerDto(CustomerDtoBuilder customerDtoBuilder) {
        this.id = customerDtoBuilder.id;
        this.age = customerDtoBuilder.age;
        this.name = customerDtoBuilder.name;
        this.surname = customerDtoBuilder.surname;
        this.countryDto = customerDtoBuilder.countryDto;
    }

    public static class CustomerDtoBuilder {
        private Long id;
        private Integer age;
        private String name;
        private String surname;
        private CountryDto countryDto;

        private static final String NAME_REGEX = "[A-Z ]+";
        private static final int AGE_MIN = 18;

        public CustomerDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerDtoBuilder name(String name) {
            this.name = name != null && name.matches(NAME_REGEX) ? name : null;
            return this;
        }

        public CustomerDtoBuilder age(int age) {
            this.age = age >= AGE_MIN ? age : null;
            return this;
        }

        public CustomerDtoBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public CustomerDtoBuilder countryDto(CountryDto countryDto) {
            this.countryDto = countryDto == null ? null : countryDto;
            return this;
        }

        public CustomerDto build() {
            return new CustomerDto(this);
        }

    }
}
