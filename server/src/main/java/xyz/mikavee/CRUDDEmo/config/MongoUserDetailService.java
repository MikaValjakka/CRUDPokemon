package xyz.mikavee.CRUDDEmo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import xyz.mikavee.CRUDDEmo.entities.Role;
import xyz.mikavee.CRUDDEmo.entities.Trainer;
import xyz.mikavee.CRUDDEmo.repositories.TrainerRepository;

import java.util.List;

@Configuration
public class MongoUserDetailService implements UserDetailsService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Trainer trainer= trainerRepository.findByUserName(username);

        // If no trainer in database
        if(trainer == null){
            throw new UsernameNotFoundException("User: "+ username + " not found");
        }
        List<Role> roles = trainer.getRoles();
        String[] rolesArray = roles.stream()
                .map(Role::toString) // Convert Role enum to string
                .toArray(String[]::new);


        return org.springframework.security.core.userdetails.User.withUsername(trainer.getUserName())
                .password(trainer.getPassword())
                .roles(rolesArray)
                .build();
    }
}
