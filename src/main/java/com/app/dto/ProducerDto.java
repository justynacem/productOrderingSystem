package com.app.dto;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ProducerDto {
    private Long id;
    private String name;
    private CountryDto countryDto;
    private TradeDto tradeDto;

    public static ProducerDto.ProducerDtoBuilder builder() {
        return new ProducerDtoBuilder();
    }

    public ProducerDto(ProducerDtoBuilder producerDtoBuilder) {
        this.id = producerDtoBuilder.id;
        this.name = producerDtoBuilder.name;
        this.countryDto = producerDtoBuilder.countryDto;
        this.tradeDto = producerDtoBuilder.tradeDto;
    }

    public static class ProducerDtoBuilder {
        private Long id;
        private String name;
        private CountryDto countryDto;
        private TradeDto tradeDto;

        private static final String NAME_REGEX = "[A-Z ]+";

        public ProducerDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProducerDtoBuilder name(String name) {
            this.name = name != null && name.matches(NAME_REGEX) ? name : null;
            return this;
        }

        public ProducerDtoBuilder countryDto(CountryDto countryDto) {
            this.countryDto = countryDto == null ? null : countryDto;
            return this;
        }

        public ProducerDtoBuilder tradeDto(TradeDto tradeDto) {
            this.tradeDto = tradeDto == null ? null : tradeDto;
            return this;
        }

        public ProducerDto build() {
            return new ProducerDto(this);
        }
    }
}
