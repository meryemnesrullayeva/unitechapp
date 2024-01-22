package az.unitech.auth.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.unitech.auth.entity.User;
import az.unitech.auth.repository.RegisterRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserJWTService{

    @Autowired
    RegisterRepository userRepository;


    public Optional<User> getByPin(@NonNull String pin) {
        Optional<User> user = userRepository.findByPin(pin);
        return user;
    }


}