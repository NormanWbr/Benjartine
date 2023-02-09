package be.technifutur.Benjartine.service.impl;

import be.technifutur.Benjartine.model.dto.OrderDTO;
import be.technifutur.Benjartine.model.entity.Etat;
import be.technifutur.Benjartine.model.entity.Order;
import be.technifutur.Benjartine.model.entity.Sandwich;
import be.technifutur.Benjartine.model.entity.User;
import be.technifutur.Benjartine.repository.BasketRepository;
import be.technifutur.Benjartine.repository.OrderRepository;
import be.technifutur.Benjartine.repository.UserRepository;
import be.technifutur.Benjartine.service.BasketService;
import be.technifutur.Benjartine.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final BasketRepository basketRepository;

    private final BasketService basketService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(BasketRepository basketRepository, BasketService basketService, OrderRepository orderRepository, UserRepository userRepository) {
        this.basketRepository = basketRepository;
        this.basketService = basketService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void confirm(String auth) {
        Order order = new Order();

        User user = userRepository.findByUsername(auth).get();

        LocalDateTime now = LocalDateTime.now();

        boolean discount = Math.random() < 0.000000001;

        Long userId = userRepository.findByUsername(auth).get().getId();

        List<Sandwich> listeSandwiches = basketRepository.findById(userId).get().getSandwiches();

        order.setOrderDate(now);
        order.setUser(userRepository.findByUsername(auth).get());
        order.setDeliveryDate(now.plusDays(5));
        order.setDiscount(discount);
        order.setEtat(Etat.WAITING);
        order.setSandwiches(listeSandwiches);

        basketService.clearBasket(auth);
        orderRepository.save(order);

    }

    @Override
    public List<OrderDTO> getAllByUser(String principal) {
        Long userId = userRepository.findByUsername(principal).get().getId();
        return orderRepository.findAllByUserId(userId).stream().map(OrderDTO::from).toList();
    }
}
