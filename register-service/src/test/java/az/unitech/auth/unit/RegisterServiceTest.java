package az.unitech.auth.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;

import az.unitech.auth.converter.UserMapper;
import az.unitech.auth.dto.UserDto.UserDto;
import az.unitech.auth.entity.User;
import az.unitech.auth.exception.ApplicationException;
import az.unitech.auth.exception.error.Errors;
import az.unitech.auth.repository.RegisterRepository;
import az.unitech.auth.service.users.RegisterServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    @Mock
    private RegisterRepository registerRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private RegisterServiceImpl registerService;

    private UserDto userDto;
    private User userEntity;

    @BeforeEach
    public void setup() {
        userDto = new UserDto();
        userDto.setPin("12345");

        userEntity = new User();
        userEntity.setPin("12345");

        when(userMapper.toEntity(any(UserDto.class))).thenReturn(userEntity);
    }

    @Test
    public void testInsertUser_Success() {
        when(registerRepository.findByPin("12345")).thenReturn(Optional.empty());

        UserDto result = registerService.insertUser(userDto);

        verify(registerRepository, times(1)).save(userEntity);
        assertEquals(userDto.getPin(), result.getPin());
    }

    @Test
    public void testInsertUser_DuplicatePin() {
        when(registerRepository.findByPin("12345")).thenReturn(Optional.of(userEntity));

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> registerService.insertUser(userDto));

        assertEquals(Errors.PIN_EXIST, exception.getMessage());
        verify(registerRepository, never()).save(any(User.class));
    }

}
