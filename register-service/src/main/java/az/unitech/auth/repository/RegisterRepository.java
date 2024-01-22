package az.unitech.auth.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.unitech.auth.entity.User;

@Repository
public interface RegisterRepository extends JpaRepository<User, Long> {

    Optional<User> findByPin(String pin);

}

