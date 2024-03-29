package io.zensoft.food.repository;

import io.zensoft.food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findUserByEmail(String email);

    Boolean existsByEmail(String email);

    List<User> findByFirstnameStartingWithIgnoreCaseOrLastnameStartingWithIgnoreCase(String firstname, String lastname);
}
