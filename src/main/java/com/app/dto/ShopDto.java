package com.app.dto;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ShopDto {
    private Long id;
    private String name;
    private CountryDto countryDto;

    public static ShopDto.ShopDtoBuilder builder() {
        return new ShopDtoBuilder();
    }

    public ShopDto(ShopDtoBuilder shopDtoBuilder) {
        this.id = shopDtoBuilder.id;
        this.name = shopDtoBuilder.name;
        this.countryDto = shopDtoBuilder.countryDto;
    }

    public static class ShopDtoBuilder {
        private Long id;
        private String name;
        private CountryDto countryDto;

        private static final String NAME_REGEX = "[A-Z ]+";
        private static final int AGE_MIN = 18;

        public ShopDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ShopDtoBuilder name(String name) {
            this.name = name != null && name.matches(NAME_REGEX) ? name : null;
            return this;
        }

        public ShopDtoBuilder countryDto(CountryDto countryDto) {
            this.countryDto = countryDto;
            return this;
        }

        public ShopDto build() {
            return new ShopDto(this);
        }
    }

}
