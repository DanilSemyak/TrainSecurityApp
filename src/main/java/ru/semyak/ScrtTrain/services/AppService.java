package ru.semyak.ScrtTrain.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.semyak.ScrtTrain.models.Application;
import ru.semyak.ScrtTrain.models.MyUser;
import ru.semyak.ScrtTrain.repositories.UserRepository;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {

    private List<Application> applications;
    public final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadAppInDB() {
        Faker faker = new Faker();
        applications = IntStream.range(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build()).toList();
    }

    public List<Application> allApplications() {
        return applications;
    }

    public Application applicationById(int id) {
        return applications.stream().filter(res -> res.getId() == id).findFirst().orElse(null);
    }

    public void addUser(MyUser myUser) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        userRepository.save(myUser);
    }
}
