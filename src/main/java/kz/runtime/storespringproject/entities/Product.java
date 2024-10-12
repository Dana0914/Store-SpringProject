package kz.runtime.storespringproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@Table(name = "items")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<Values> values;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Orders> orders;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Basket> baskets;


    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ToString.Exclude
    @ManyToMany(mappedBy = "items")
    List<Orders> order;

}
