package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private BigDecimal price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producer_id")
    private Producer producer;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    private Set<CustomerOrder> customerOrders = new HashSet<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    private Set<Stock> stocks = new HashSet<>();
    @ElementCollection
    @CollectionTable(
            name = "guarantee_components",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "guarantee_component")
    @Enumerated(EnumType.STRING)
    private Set<EGuarantee> guaranteeComponents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
