package be.technifutur.Benjartine.model.form;

import be.technifutur.Benjartine.model.entity.Basket;
import be.technifutur.Benjartine.model.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationForm {

    @NotNull
    private String username;
    @NotNull
    @Size(min = 4)
    private String password;

    public User toEntity() {
        Basket basket = new Basket();
        User user = new User();
        user.setBasket(basket);
        user.setUsername(username);
        user.setPassword(password);
        user.getRoles().add("USER");
        return user;

    }

}
