package kz.runtime.storespringproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kz.runtime.storespringproject.roles.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    private List<Role> role;
    @ToString.Exclude
    @Column(name = "email")
    private String email;
    @ToString.Exclude
    @Column(name = "password")
    private String password;
    @ToString.Exclude
    @Column(name = "first_name")
    private String firstName;
    @ToString.Exclude
    @Column(name = "last_name")
    private String lastName;


    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Orders> orders;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Basket> baskets;


    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Review> reviews;

}
