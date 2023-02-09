package be.technifutur.Benjartine.service.impl;

import be.technifutur.Benjartine.exception.EmptyListException;
import be.technifutur.Benjartine.model.dto.OrderDTO;
import be.technifutur.Benjartine.model.entity.Etat;
import be.technifutur.Benjartine.model.entity.Order;
import be.technifutur.Benjartine.model.entity.Sandwich;
import be.technifutur.Benjartine.model.entity.User;
import be.technifutur.Benjartine.repository.BasketRepository;
import be.technifutur.Benjartine.repository.OrderRepository;
import be.technifutur.Benjartine.repository.SandwichRepository;
import be.technifutur.Benjartine.repository.UserRepository;
import be.technifutur.Benjartine.service.BasketService;
import be.technifutur.Benjartine.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final BasketRepository basketRepository;

    private final BasketService basketService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SandwichRepository sandwichRepository;

    public OrderServiceImpl(BasketRepository basketRepository, BasketService basketService, OrderRepository orderRepository, UserRepository userRepository,
                            SandwichRepository sandwichRepository) {
        this.basketRepository = basketRepository;
        this.basketService = basketService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.sandwichRepository = sandwichRepository;
    }

    @Override
    public void confirm(String auth) {

        User user = userRepository.findByUsername(auth).get();
        Long userId = userRepository.findByUsername(auth).get().getId();
        List<Sandwich> listeSandwiches = basketRepository.findById(userId).get().getSandwiches();

        if (listeSandwiches.isEmpty())
            throw new EmptyListException("Votre panier est vide");

        LocalDateTime now = LocalDateTime.now();

        Order order = new Order();

        order.setOrderDate(now);
        order.setUser(userRepository.findByUsername(auth).get());
        order.setDeliveryDate(now.plusDays(5));
        boolean discount = Math.random() < 0.000000001;
        order.setDiscount(discount);
        order.setEtat(Etat.WAITING);
        order.setSandwiches(listeSandwiches);
        listeSandwiches.forEach(System.out::println);

        Set<Order> listOrder = user.getOrders();
        listOrder.add(order);
        user.setOrders(listOrder);
        userRepository.save(user);
        basketService.clearBasket(auth);
        orderRepository.save(order);

    }

    @Override
        public List<OrderDTO> getAllByUser(Authentication auth) {
        Long userId = userRepository.findByUsername((String) auth.getPrincipal()).get().getId();
        return orderRepository.findAllByUserId(userId).stream().map(OrderDTO::from).toList();
    }

    @Override
    public List<OrderDTO> getAll() {
        return orderRepository.findAll().stream().map(OrderDTO::from).toList();
    }

}
