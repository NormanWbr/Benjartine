package be.technifutur.Benjartine.service.impl;

import be.technifutur.Benjartine.exception.RessourceNotFoundException;
import be.technifutur.Benjartine.model.entity.Basket;
import be.technifutur.Benjartine.model.entity.Sandwich;
import be.technifutur.Benjartine.model.entity.User;
import be.technifutur.Benjartine.repository.BasketRepository;
import be.technifutur.Benjartine.repository.SandwichRepository;
import be.technifutur.Benjartine.repository.UserRepository;
import be.technifutur.Benjartine.service.BasketService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final SandwichRepository sandwichRepository;

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;

    public BasketServiceImpl(SandwichRepository sandwichRepository, BasketRepository basketRepository,
                             UserRepository userRepository) {
        this.sandwichRepository = sandwichRepository;
        this.basketRepository = basketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addSandwichToBasket(long id, String username) {


        Sandwich sandwich = sandwichRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException());

        List<Sandwich> list = new ArrayList<>();

        list.add(sandwich);

        Basket basket = basketRepository.findByUsername(username);

        if (basket == null) {
            basket = new Basket();
            basket.setUser(userRepository.findByUsername(username)
                    .orElseThrow(() -> new RessourceNotFoundException()));
        }

        basket.setSandwiches(list);

        basketRepository.save(basket);

    }
}
