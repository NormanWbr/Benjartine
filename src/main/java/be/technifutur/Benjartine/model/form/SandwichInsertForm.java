package be.technifutur.Benjartine.model.form;

import be.technifutur.Benjartine.model.entity.Sandwich;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SandwichInsertForm {

    @NotNull
    private String nom;

    @NotNull
    private String desc;

    @NotNull
    @Positive
    private Double prix;

    public Sandwich toEntity(){
        Sandwich sandwich = new Sandwich();

        sandwich.setName(this.nom);
        sandwich.setDesc(this.desc);
        sandwich.setPrice(this.prix);

        return sandwich;
    }

}
