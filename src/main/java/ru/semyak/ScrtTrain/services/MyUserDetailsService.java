package ru.semyak.ScrtTrain.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.semyak.ScrtTrain.config.MyUserDetails;
import ru.semyak.ScrtTrain.models.MyUser;
import ru.semyak.ScrtTrain.repositories.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
// используется для
// получения информации о пользователе, прошедшем аутентификацию

    @Autowired
    private UserRepository userRepository;

    @Override
    // UserDetails содержит информацию о пользователе после успешной аутентификации
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByName(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
