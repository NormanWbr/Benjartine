package be.technifutur.Benjartine.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SandwichDTO {

    private Long id;
    private String nom;
    private String desc;
    private Double prix;

    public static SandwichDTO from(SandwichDTO entity) {

        if (entity == null)
            return null;

        return SandwichDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .desc(entity.getDesc())
                .prix(entity.getPrix())
                .build();
    }
}
