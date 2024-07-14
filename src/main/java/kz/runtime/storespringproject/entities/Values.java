package kz.runtime.storespringproject.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "values")
public class Values {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Options option;
}
