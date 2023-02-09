package be.technifutur.Benjartine.controller;

import be.technifutur.Benjartine.model.dto.SandwichDTO;
import be.technifutur.Benjartine.service.BasketService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PatchMapping("/add/{id:[0-9]+}")
    public void addSandwichToBasket(@PathVariable long id, Authentication auth,@RequestParam short quantity){
        basketService.addSandwichToBasket(id,(String) auth.getPrincipal(), quantity);
    }

    @PatchMapping("/remove/{id:[0-9]+}")
    public void removeSandwichToBasket(@PathVariable long id, Authentication auth){
        basketService.removeSandwichToBasket(id,(String) auth.getPrincipal());
    }

    @GetMapping()
    public List<SandwichDTO> getBasket(Authentication auth){
        return basketService.getBasket((String) auth.getPrincipal());
    }

    @PatchMapping("/clear")
    public void clearBasket(Authentication auth){
        basketService.clearBasket((String) auth.getPrincipal());
    }

}
