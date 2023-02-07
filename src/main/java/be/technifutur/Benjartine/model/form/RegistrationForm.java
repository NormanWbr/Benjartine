package be.technifutur.Benjartine.model.form;

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

    public User toEntity(){

        User user = new User();

        user.setUsername( this.username );
        user.setPassword( this.password );

        return user;

    }

}
