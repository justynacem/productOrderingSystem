package com.app.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class CountryDto {
    private Long id;
    private String name;

    public static CountryDto.CountryDtoBuilder builder() {
        return new CountryDtoBuilder();
    }

    public CountryDto(CountryDtoBuilder countryDtoBuilder) {
        this.id = countryDtoBuilder.id;
        this.name = countryDtoBuilder.name;
    }

    public static class CountryDtoBuilder {
        private Long id;
        private String name;

        private static final String NAME_REGEX = "[A-Z ]+";

        public CountryDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CountryDtoBuilder name(String name) {
            this.name = name != null && name.matches(NAME_REGEX) ? name : null;
            return this;
        }

        public CountryDto build() {
            return new CountryDto(this);
        }
    }
}
