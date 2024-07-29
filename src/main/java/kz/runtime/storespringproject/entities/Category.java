package kz.runtime.storespringproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Data
@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Options> options;


}
