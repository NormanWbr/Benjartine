package be.technifutur.Benjartine.controller;

import be.technifutur.Benjartine.model.dto.SandwichDTO;
import be.technifutur.Benjartine.model.form.SandwichInsertForm;
import be.technifutur.Benjartine.service.SandwichService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sandwich")
public class SandwichController {

    private SandwichService sandwichService;

    public SandwichController(SandwichService sandwichService) {
        this.sandwichService = sandwichService;
    }

    @GetMapping("/all")
    public List<SandwichDTO> getAll(){
        return sandwichService.getAll();
    }

    @GetMapping("/{id:[0-9]+}")
    public SandwichDTO getOne(@PathVariable long id){
        return sandwichService.getOne(id);
    }

    @PostMapping("/add")
    public void addSandwich(@RequestBody SandwichInsertForm sandwichInsertForm){
        sandwichService.addSandwich(sandwichInsertForm);
    }

    @PatchMapping("/update/{id:[0-9]+}")
    public void updateSandwich(@PathVariable long id, @RequestBody SandwichInsertForm sandwichInsertForm){
        sandwichService.updateSandwich(id, sandwichInsertForm);
    }

    @DeleteMapping("/delete/{id:[0-9]+}")
    public void deleteSandwich(@PathVariable long id){
        sandwichService.deleteSandwich(id);
    }

    @GetMapping("/diet/{dietName}")
    public List<SandwichDTO> getByDietName(@PathVariable String dietName){
        return sandwichService.getByDietName(dietName);
    }

}
