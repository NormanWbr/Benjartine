package be.technifutur.Benjartine.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "diets")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;


    @ManyToMany(mappedBy = "diets")
    private Set<Sandwich> sandwiches = new LinkedHashSet<>();

}
