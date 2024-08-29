package ru.semyak.ScrtTrain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.semyak.ScrtTrain.models.MyUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByName(String name);
}
