package be.technifutur.Benjartine.repository;

import be.technifutur.Benjartine.model.entity.Basket;
import be.technifutur.Benjartine.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

@Query("select b from Basket b where b.user.username = :principal")
    Basket findByUsername(String principal);
}
