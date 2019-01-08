package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Shop> shops = new HashSet<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Customer> customers = new HashSet<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Producer> producers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) &&
                Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
