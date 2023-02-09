package be.technifutur.Benjartine.service;

import be.technifutur.Benjartine.model.dto.OrderDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {
    void confirm(String auth);

    List<OrderDTO> getAllByUser(Authentication auth);

    List<OrderDTO> getAll();
}
