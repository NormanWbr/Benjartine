package be.technifutur.Benjartine.repository;

import be.technifutur.Benjartine.model.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
