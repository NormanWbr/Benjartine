package be.technifutur.Benjartine.controller;

import be.technifutur.Benjartine.model.dto.OrderDTO;
import be.technifutur.Benjartine.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PatchMapping("/confirm")
    public void buy(Authentication auth) {
        orderService.confirm((String) auth.getPrincipal());
    }

    @GetMapping()
    public List<OrderDTO> getAllByUser(Authentication auth) {
        return orderService.getAllByUser(auth);
    }

    @GetMapping("/all")
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @PatchMapping("/status/{id:[0-9]+}")
    public void validateOrder(@RequestBody String statusToUpdate, @PathVariable long id) {
        orderService.setStatus(id, statusToUpdate);
    }

}
