package be.technifutur.Benjartine.service;


import be.technifutur.Benjartine.jwt.JWTHolderDTO;
import be.technifutur.Benjartine.model.form.LoginForm;
import be.technifutur.Benjartine.model.form.RegistrationForm;

public interface AuthService {

    void register( RegistrationForm form );

    JWTHolderDTO login(LoginForm form );

}
