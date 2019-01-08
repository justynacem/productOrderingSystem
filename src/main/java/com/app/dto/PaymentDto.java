package com.app.dto;

import com.app.model.EPayment;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class PaymentDto {
    private Long id;
    private EPayment ePayment;

    public static PaymentDto.PaymentDtoBuilder builder() {
        return new PaymentDtoBuilder();
    }

    public PaymentDto(PaymentDtoBuilder paymentDtoBuilder) {
        this.id = paymentDtoBuilder.id;
        this.ePayment = paymentDtoBuilder.ePayment;
    }

    public static class PaymentDtoBuilder {
        private Long id;
        private EPayment ePayment;

        public PaymentDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PaymentDtoBuilder ePayment(EPayment ePayment) {
            this.ePayment = ePayment;
            return this;
        }

        public PaymentDto build() {
            return new PaymentDto(this);
        }
    }
}
