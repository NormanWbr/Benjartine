package be.technifutur.Benjartine.service;

import be.technifutur.Benjartine.model.dto.SandwichDTO;

import java.util.List;

public interface BasketService {
    void addSandwichToBasket(long id, String principal, short quantity);
    void removeSandwichToBasket(long id, String principal);
    List<SandwichDTO> getBasket(String auth);
    void clearBasket(String principal);
}
