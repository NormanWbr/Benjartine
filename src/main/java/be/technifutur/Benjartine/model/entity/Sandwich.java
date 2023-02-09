package be.technifutur.Benjartine.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sandwiches")
public class Sandwich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sandwich_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToMany
    @JoinTable(name = "sandwiches_diets",
            joinColumns = @JoinColumn(name = "sandwich_id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id"))
    private Set<Diet> diets = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "sandwiches")
    private Set<Basket> baskets = new LinkedHashSet<>();

}
