package org.example.guest_app.repositories;
import org.example.guest_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserById(Long userId);
}
