package be.technifutur.Benjartine.service.impl;

import be.technifutur.Benjartine.exception.RessourceNotFoundException;
import be.technifutur.Benjartine.model.dto.SandwichDTO;
import be.technifutur.Benjartine.model.entity.Sandwich;
import be.technifutur.Benjartine.model.form.SandwichInsertForm;
import be.technifutur.Benjartine.repository.DietRepository;
import be.technifutur.Benjartine.repository.SandwichRepository;
import be.technifutur.Benjartine.service.SandwichService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class SandwichServiceImpl implements SandwichService {

    private SandwichRepository sandwichRepository;
    private final DietRepository dietRepository;

    public SandwichServiceImpl(SandwichRepository sandwichRepository,
                               DietRepository dietRepository) {
        this.sandwichRepository = sandwichRepository;
        this.dietRepository = dietRepository;
    }

    @Override
    public void create(SandwichInsertForm form) {

    }

    @Override
    public SandwichDTO getOne(long id) {
        return sandwichRepository.findById(id)
                .map(SandwichDTO::from)
                .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public List<SandwichDTO> getAll() {
        return sandwichRepository.findAll().stream()
                .map(SandwichDTO::from)
                .toList();
    }

    @Override
    public List<SandwichDTO> getByDietName(String dietName) {
        return dietRepository.getByDietName(dietName).stream()
                .map(SandwichDTO::from)
                .toList();
    }

    @Override
    public void addSandwich(SandwichInsertForm sandwichInsertForm) {
        Sandwich sandwich = new Sandwich();

        sandwich.setName(sandwichInsertForm.getNom());
        sandwich.setDescription(sandwichInsertForm.getDescription());
        sandwich.setPrice(sandwichInsertForm.getPrix());

        sandwichRepository.save(sandwich);
    }

    @Override
    public void updateSandwich(long id, SandwichInsertForm sandwichInsertForm) {
        Sandwich sandwich = sandwichRepository.getById(id);

        sandwich.setName(sandwichInsertForm.getNom());
        sandwich.setDescription(sandwichInsertForm.getDescription());
        sandwich.setPrice(sandwichInsertForm.getPrix());

        sandwichRepository.save(sandwich);
    }

    @Override
    public void deleteSandwich(long id) {
        sandwichRepository.delete(sandwichRepository.getById(id));
    }

}
