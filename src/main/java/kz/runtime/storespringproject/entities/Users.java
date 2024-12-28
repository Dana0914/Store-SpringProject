package kz.runtime.storespringproject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kz.runtime.storespringproject.roles.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;



    @JsonIgnore
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Orders> orders;


    @JsonIgnore
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Basket> baskets;


    @JsonIgnore
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Review> reviews;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
