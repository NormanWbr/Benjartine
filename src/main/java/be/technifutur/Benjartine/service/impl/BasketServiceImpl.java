package be.technifutur.Benjartine.service.impl;

import be.technifutur.Benjartine.model.dto.SandwichDTO;
import be.technifutur.Benjartine.model.entity.Basket;
import be.technifutur.Benjartine.model.entity.Sandwich;
import be.technifutur.Benjartine.repository.BasketRepository;
import be.technifutur.Benjartine.repository.SandwichRepository;
import be.technifutur.Benjartine.repository.UserRepository;
import be.technifutur.Benjartine.service.BasketService;
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
    public void addSandwichToBasket(long id, String auth, short quantity) {

        Long userId = userRepository.findByUsername(auth).get().getId();

        List list = basketRepository.findById(userId).get().getSandwiches();

        for (int i = 0;i<quantity;i++){
            list.add(sandwichRepository.findById(id).get());
        }

        Basket basket = basketRepository.findById(userId).get();

        basket.setSandwiches(list);

        basketRepository.save(basket);

    }

    @Override
    public void removeSandwichToBasket(long id, String auth) {
        Long userId = userRepository.findByUsername(auth).get().getId();

        List list = basketRepository.findById(userId).get().getSandwiches();

        list.remove(sandwichRepository.findById(id).get());

        Basket basket = basketRepository.findById(userId).get();

        basket.setSandwiches(list);

        basketRepository.save(basket);
    }

    @Override
    public List<SandwichDTO> getBasket(String auth) {

        Long userId = userRepository.findByUsername(auth).get().getId();

        List<Sandwich> list = basketRepository.findById(userId).get().getSandwiches();

        return list.stream().map(SandwichDTO::from).toList();

    }

    @Override
    public void clearBasket(String principal) {
        Long userId = userRepository.findByUsername(principal).get().getId();

        Basket basket = basketRepository.findById(userId).get();

        basket.setSandwiches(new ArrayList<>());

        basketRepository.save(basket);
    }
}
