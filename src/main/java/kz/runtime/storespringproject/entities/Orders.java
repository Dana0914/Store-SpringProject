package kz.runtime.storespringproject.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status", insertable = false)
    private int status;
    @Column(name = "order_date", insertable = false)
    private Date orderDate;
    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "items_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
    @ManyToMany
    @JoinTable(
            name = "orders_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "items_id"))
    List<Product> items;

}
