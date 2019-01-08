package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "errors")
public class ExceptionModel {
    @Id
    @GeneratedValue
    private Long id;
    private String exceptionMessage;
    private LocalDateTime exceptionDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionModel that = (ExceptionModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(exceptionMessage, that.exceptionMessage) &&
                Objects.equals(exceptionDateTime, that.exceptionDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exceptionMessage, exceptionDateTime);
    }

    @Override
    public String toString() {
        return "ExceptionModel{" +
                "id=" + id +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", exceptionDateTime=" + exceptionDateTime +
                '}';
    }
}
