package be.technifutur.Benjartine.service;

import be.technifutur.Benjartine.model.dto.SandwichDTO;
import be.technifutur.Benjartine.model.form.SandwichInsertForm;

import java.util.List;

public interface SandwichService {

    void create(SandwichInsertForm form);

    SandwichDTO getOne(long id);

    List<SandwichDTO> getAll();

    List<SandwichDTO> getByDietName(String dietName);

    void addSandwich(SandwichInsertForm sandwichInsertForm);

    void updateSandwich(long id, SandwichInsertForm sandwichInsertForm);

    void deleteSandwich(long id);
}
