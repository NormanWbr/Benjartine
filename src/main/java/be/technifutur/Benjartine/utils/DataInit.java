package be.technifutur.Benjartine.utils;

import be.technifutur.Benjartine.model.entity.Diet;
import be.technifutur.Benjartine.model.entity.Sandwich;
import be.technifutur.Benjartine.model.form.LoginForm;
import be.technifutur.Benjartine.model.form.RegistrationForm;
import be.technifutur.Benjartine.repository.DietRepository;
import be.technifutur.Benjartine.repository.SandwichRepository;
import be.technifutur.Benjartine.repository.UserRepository;
import be.technifutur.Benjartine.service.AuthService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInit implements InitializingBean {

    private final AuthService authService;
    private final SandwichRepository sandwichRepository;
    private final DietRepository dietRepository;

    private final UserRepository userRepository;

    public DataInit(AuthService authService, SandwichRepository sandwichRepository, DietRepository dietRepository, UserRepository userRepository) {
        this.authService = authService;
        this.sandwichRepository = sandwichRepository;
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Diet vegan = new Diet();

        vegan.setName("Vegan");
        vegan.setDescription("Sans viande, sans gluten, sans lactose");

        dietRepository.save(vegan);

        Diet vegetarien = new Diet();

        vegetarien.setName("Vegetarien");
        vegetarien.setDescription("Sans viande, sans gluten");

        dietRepository.save(vegetarien);

        Diet sansGluten = new Diet();

        sansGluten.setName("Gluten free");
        sansGluten.setDescription("Sans gluten");

        dietRepository.save(sansGluten);

        Sandwich sandwich = new Sandwich();

        sandwich.setName("Sandwich au poulet");
        sandwich.setPrice(5.50);
        sandwich.setDescription("Sandwich au poulet et crudités");

        sandwichRepository.save(sandwich);

        sandwich = new Sandwich();

        sandwich.setName("Sandwich au jambon");
        sandwich.setPrice(4.50);
        sandwich.setDescription("Sandwich au jambon");

        sandwichRepository.save(sandwich);

        sandwich = new Sandwich();

        sandwich.setName("Sandwich au fromage");
        sandwich.setPrice(6.50);
        sandwich.setDescription("Sandwich au fromage");
        sandwich.setDiets(Set.of(vegetarien));

        sandwichRepository.save(sandwich);

        RegistrationForm registrationForm = new RegistrationForm();

        registrationForm.setUsername("admin");
        registrationForm.setPassword("pass");

        authService.register(registrationForm);

        LoginForm loginForm = new LoginForm();

        loginForm.setUsername("admin");
        loginForm.setPassword("pass");

        authService.login(loginForm);

    }
}
