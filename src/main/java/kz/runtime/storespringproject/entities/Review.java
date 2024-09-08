package kz.runtime.storespringproject.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@Entity
@Table(name = "review")
public class Review {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @Column(name = "rating")
    private int rating;
    @Column(name = "review")
    @ToString.Exclude
    private String reviewText;
    @Column(name = "review_status")
    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "items_id")
    private Product product;
}
