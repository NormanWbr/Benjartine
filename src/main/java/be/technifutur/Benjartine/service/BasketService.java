package be.technifutur.Benjartine.service;

import be.technifutur.Benjartine.model.dto.SandwichDTO;
import be.technifutur.Benjartine.model.entity.Sandwich;

import java.util.List;

public interface BasketService {
    void addSandwichToBasket(long id, String principal);
    void removeSandwichToBasket(long id, String principal);
    List<SandwichDTO> getBasket(String auth);
    void clearBasket(String principal);
}
