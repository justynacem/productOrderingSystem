package com.app.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ExceptionModelDto {
    private Long id;
    private String exceptionMessage;
    private LocalDateTime exceptionDateTime;

    public static ExceptionModelDto.ExceptionModelDtoBuilder builder() {
        return new ExceptionModelDtoBuilder();
    }

    public ExceptionModelDto(ExceptionModelDtoBuilder exceptionModelDtoBuilder) {
        this.id = exceptionModelDtoBuilder.id;
        this.exceptionMessage = exceptionModelDtoBuilder.exceptionMessage;
        this.exceptionDateTime = exceptionModelDtoBuilder.exceptionDateTime;
    }

    public static class ExceptionModelDtoBuilder {
        private Long id;
        private String exceptionMessage;
        private LocalDateTime exceptionDateTime;

        public ExceptionModelDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ExceptionModelDtoBuilder exceptionMessage(String exceptionMessage) {
            this.exceptionMessage = exceptionMessage != null ? exceptionMessage : null;
            return this;
        }

        public ExceptionModelDtoBuilder exceptionDateTime(LocalDateTime exceptionDateTime) {
            this.exceptionDateTime = exceptionDateTime != null ? exceptionDateTime : LocalDateTime.MIN;
            return this;
        }

        public ExceptionModelDto build() {
            return new ExceptionModelDto(this);
        }
    }
}
