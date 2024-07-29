package kz.runtime.storespringproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kz.runtime.storespringproject.roles.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    private int role;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Orders> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Basket> baskets;


    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Review> reviews;

}
