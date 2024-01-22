package az.unitech.auth.service.users;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import az.unitech.auth.config.CustomAuthorization;
import az.unitech.auth.converter.UserMapper;
import az.unitech.auth.dto.UserDto.*;
import az.unitech.auth.entity.*;
import az.unitech.auth.exception.ApplicationException;
import az.unitech.auth.exception.error.Errors;
import az.unitech.auth.repository.*;

@RequiredArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {
    private final CustomAuthorization customAuthorization;
    private final RegisterRepository registerRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    /**
     * Returns the user information based on the user ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user information in the form of a UserDto object.
     * @throws NullPointerException if the specified user ID is not found.
     */
    @Override
    public UserDto getUserById(Long id) {
        User entity = registerRepository.findById(id).orElseThrow(() -> new NullPointerException("not found id"));
        return userMapper.toDto(entity);
    }

    @Override
    public String getPinByUserId(Long id) {

        User user = registerRepository.findById(id).orElseThrow(() -> new ApplicationException(Errors.PIN_NOT_FOUND));

        return user.getPin();
    }

    /**
     * Creates a new user based on the specified UserDto object.
     *
     * @param dto The UserDto object containing the user information to create.
     * @return The UserDto object that was created.
     */
    @Override
    public UserDto insertUser(UserDto dto) {
        Optional<User> user = registerRepository.findByPin(dto.getPin());
        if (user != null) {
            throw new ApplicationException(Errors.PIN_EXIST);
        }
        User entity = userMapper.toEntity(dto);
        entity.setStatus('1');
        registerRepository.save(entity);
        return dto;
    }

    /**
     * Resets the password of a user with the given id.
     *
     * @param id       the id of the user whose password is to be reset.
     * @param password the new password to be set for the user.
     */
    public void resetPassword(Long id, String password) {
        User user = registerRepository.findById(id).get();
        user.setPassword(passwordEncoder.encode(password));
        registerRepository.save(user);

    }

    /**
     * Resets the password of a user with the given id.
     *
     * @param oldPassword the id of the user whose password is to be reset.
     * @param newPassword the new password to be set for the user.
     */
    public Long resetUserPassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) {
            return (long) 0;
        }
        Long userId = customAuthorization.getUserIdFromToken();
        User user = registerRepository.findById(userId).get();
        if (user.getStatus() == '1' && new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            registerRepository.save(user);
            return userId;
        }

        return (long) -1;
    }

    /**
     * Updates the user information of the user with the given id.
     *
     * @param id  the id of the user to be updated.
     * @param dto the DTO object containing the updated user information.
     * @return the DTO object of the updated user.
     * @throws NullPointerException if the user with the given id is not found.
     */
    @Override
    public UserGetDto updateUser(Long id, UserGetDto dto) {
        User entity = registerRepository.findById(id).orElseThrow(() -> new NotFoundException("id not found"));
        Optional<User> user = registerRepository.findByPin(dto.getPin());
        if (user != null) {
            if (user.get().getId() != dto.getId()) {
                throw new ApplicationException(Errors.PIN_EXIST);
            }
        }
        userMapper.update(entity, dto);
        registerRepository.save(entity);
        return dto;

    }

    /**
     * Deletes the user with the given user id along with all its associated roles
     * and permissions.
     *
     * @param userId the id of the user to be deleted.
     * @return true if the user is deleted successfully, false otherwise.
     * @throws NullPointerException if the user with the given id is not found.
     */
    @Override
    public boolean deleteUser(Long userId) {
        User entity = registerRepository.findById(userId).orElseThrow(() -> new NotFoundException(" not found"));

        registerRepository.delete(entity);
        return true;
    }

}