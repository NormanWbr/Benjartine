package be.technifutur.Benjartine.controller;

import be.technifutur.Benjartine.service.BasketService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PatchMapping("/add/{id:[0-9]+}")
    public void addSandwichToBasket(@PathVariable long id, Authentication auth){
        basketService.addSandwichToBasket(id,(String) auth.getPrincipal());
    }

}
