package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataManager {
    private String name;
    private String surname;
    private String country;
    private Integer age;
    private Integer quantity;
    private Double discount;
    private String payment;
    private String product;
    private String category;
    private String trade;
    private String shop;
    private Double price;
    private Set<String> guaranteeComponents;
    private String producer;
    private String date;
    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.of(dateGenerator(date, 2), dateGenerator(date, 1), dateGenerator(date, 0),
                dateGenerator(date, 3), dateGenerator(date, 4));
    }

    public int dateGenerator(String date, int i) {
        String[] s = date.split("[-]");
        return Integer.valueOf(s[i]);
    }

    //to do
//    public void setGuaranteeComponents(String... guaranteeComponents) {
//        Set<String> guaranteeComponentsSet = new HashSet<>(Arrays.asList(guaranteeComponents));
//        this.guaranteeComponents = guaranteeComponentsSet;
//    }
}
