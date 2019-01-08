package com.app.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class TradeDto {
    private Long id;
    private String name;

    public static TradeDto.TradeDtoBuilder builder() {
        return new TradeDtoBuilder();
    }

    public TradeDto(TradeDtoBuilder tradeDtoBuilder) {
        this.id = tradeDtoBuilder.id;
        this.name = tradeDtoBuilder.name;
    }

    public static class TradeDtoBuilder {
        private Long id;
        private String name;

        private static final String NAME_REGEX = "[A-Z ]+";

        public TradeDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TradeDtoBuilder name(String name) {
            this.name = name != null && name.matches(NAME_REGEX) ? name : null;
            return this;
        }

        public TradeDto build() {
            return new TradeDto(this);
        }
    }
}
