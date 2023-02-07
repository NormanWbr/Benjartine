package be.technifutur.Benjartine.service;

import org.springframework.stereotype.Service;

public interface BasketService {
    void addSandwichToBasket(long id, String principal);
}
