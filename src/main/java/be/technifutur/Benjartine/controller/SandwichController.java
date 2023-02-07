package be.technifutur.Benjartine.controller;

import be.technifutur.Benjartine.model.dto.SandwichDTO;
import be.technifutur.Benjartine.service.SandwichService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/diet/{dietName}")
    public List<SandwichDTO> getByDietName(@PathVariable String dietName){
        return sandwichService.getByDietName(dietName);
    }

}
