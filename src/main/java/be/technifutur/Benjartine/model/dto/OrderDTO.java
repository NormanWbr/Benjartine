package be.technifutur.Benjartine.model.dto;

import be.technifutur.Benjartine.model.entity.Etat;
import be.technifutur.Benjartine.model.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {

    private Long id;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private Boolean discount;
    private Etat etat;

    private List<SandwichDTO> sandwichs;

    public static OrderDTO from(Order entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .orderDate(entity.getOrderDate())
                .deliveryDate(entity.getDeliveryDate())
                .discount(entity.getDiscount())
                .etat(entity.getEtat())
                .sandwichs(entity.getSandwiches().stream().map(SandwichDTO::from).toList())
                .build();
    }

}
