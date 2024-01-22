package az.unitech.app.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import az.unitech.app.entity.UserAccount;
import az.unitech.app.mapper.UserAccountMapper;
import az.unitech.app.model.UserAccountDto;
import az.unitech.app.repository.UserAccountRepository;
import az.unitech.app.service.UserAccountServiceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private UserAccountMapper userAccountMapper;

    @InjectMocks
    private UserAccountServiceImpl userAccountService;

    // Mock data
    private Long userId;
    private List<UserAccount> userAccounts;
    private List<UserAccountDto> userAccountDtos;

    @BeforeEach
    public void setup() {
        userId = 1L;
        // userAccounts = // Initialize with test data;
        // userAccountDtos = // Initialize with corresponding DTO test data

        when(userAccountRepository.getAccountByUserId(userId)).thenReturn(userAccounts);
        when(userAccountMapper.entityToNumberDtos(userAccounts)).thenReturn(userAccountDtos);
    }

    @Test
    public void getByUserId_ShouldReturnUserAccounts_WhenUserIdIsValid() {
        ResponseEntity<?> response = userAccountService.getByUserId(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userAccountDtos, response.getBody());

        verify(userAccountRepository, times(1)).getAccountByUserId(userId);
        verify(userAccountMapper, times(1)).entityToNumberDtos(userAccounts);
    }

}
