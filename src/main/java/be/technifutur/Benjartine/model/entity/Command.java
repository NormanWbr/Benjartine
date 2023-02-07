package be.technifutur.Benjartine.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "command_id")
    private Long id;

    @Column(name = "command_date", nullable = false)
    private LocalDateTime commandDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(nullable = false)
    private Boolean discount;

    @Column(nullable = false)
    private Etat etat;
}
