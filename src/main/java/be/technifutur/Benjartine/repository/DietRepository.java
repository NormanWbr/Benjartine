package be.technifutur.Benjartine.repository;

import be.technifutur.Benjartine.model.entity.Diet;
import be.technifutur.Benjartine.model.entity.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {

    @Query("""
            SELECT s
            FROM Sandwich s
            JOIN s.diets d
            WHERE d.name = :dietName
""")
    List<Sandwich> getByDietName(String dietName);
}
