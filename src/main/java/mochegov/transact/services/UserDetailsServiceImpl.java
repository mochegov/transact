package mochegov.transact.services;

import mochegov.transact.model.User;
import mochegov.transact.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(userName);
        if (user == null) {
            // Пользователь не найден по имени
            throw new UsernameNotFoundException("Пользователь по имени " + userName + " не найден");

        } else {
            // Пользователь найден по имени
            UserBuilder userBuilder = withUsername(userName);
            userBuilder.password(user.getPassword());
            userBuilder.roles(user.getRole());

            return userBuilder.build();
        }
    }
}
