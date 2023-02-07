package be.technifutur.Benjartine.service;

import be.technifutur.Benjartine.model.dto.SandwichDTO;
import be.technifutur.Benjartine.model.form.SandwichInsertForm;

import java.util.List;

public interface SandwichService {

    void create(SandwichInsertForm form);

    SandwichDTO getOne(long id);

    List<SandwichDTO> getAll();

    List<SandwichDTO> getByDietName(String dietName);
}
