package be.technifutur.Benjartine.model.dto;

import be.technifutur.Benjartine.model.entity.Diet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DietDTO {
    private Long id;
    private String name;

    public static DietDTO from(Diet diet) {
        return DietDTO.builder()
                .id(diet.getId())
                .name(diet.getName())
                .build();
    }
}
