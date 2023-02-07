package be.technifutur.Benjartine.repository;

import be.technifutur.Benjartine.model.entity.Diet;
import be.technifutur.Benjartine.model.entity.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SandwichRepository extends JpaRepository<Sandwich, Long> {

}
