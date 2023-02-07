package be.technifutur.Benjartine.model.dto;

import be.technifutur.Benjartine.model.entity.Diet;
import be.technifutur.Benjartine.model.entity.Sandwich;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SandwichDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Set<DietDTO> diets;


    public static SandwichDTO from(Sandwich entity) {

        return SandwichDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .diets(entity.getDiets().stream().map(
                        DietDTO::from
                ).collect(java.util.stream.Collectors.toSet()
                ))
                .build();
    }

}


