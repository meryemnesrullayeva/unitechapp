package az.unitech.app.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import az.unitech.app.entity.Transaction;
import az.unitech.app.entity.UserAccount;
import az.unitech.app.errors.ErrorsFinal;
import az.unitech.app.exception.ApplicationException;
import az.unitech.app.model.TransferAmountDto;
import az.unitech.app.repository.TransactionRepository;
import az.unitech.app.repository.UserAccountRepository;
import az.unitech.app.service.PaymentServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Long accountNumberFrom;
    private Long accountNumberTo;
    private TransferAmountDto transferAmountDto;
    private UserAccount userAccountFrom;
    private UserAccount userAccountTo;
    private BigDecimal transferAmount;

    @BeforeEach
    public void setup() {
        accountNumberFrom = 123L;
        accountNumberTo = 456L;
        transferAmount = new BigDecimal("100.00");

        transferAmountDto = new TransferAmountDto();
        transferAmountDto.setTransferAmount(transferAmount);

        userAccountFrom = new UserAccount();
        userAccountFrom.setBalance(new BigDecimal("200.00"));

        userAccountTo = new UserAccount();
        userAccountTo.setBalance(new BigDecimal("150.00"));
    }

    @Test
    public void testTransfer_ValidScenario() {
        when(userAccountRepository.getByNumber(accountNumberFrom))
                .thenReturn(Collections.singletonList(userAccountFrom));
        when(userAccountRepository.getByNumber(accountNumberTo)).thenReturn(Collections.singletonList(userAccountTo));
        when(userAccountRepository.getByNumberAndStatus(accountNumberTo, '0')).thenReturn(Collections.emptyList());
        when(userAccountRepository.getByAccountNumber(accountNumberTo)).thenReturn(Collections.emptyList());

        // Call the transfer method
        ResponseEntity<?> response = paymentService.transfer(accountNumberFrom, accountNumberTo, transferAmountDto);

        // Assertions
        assertNotNull(response);

        // Verify interactions
        verify(userAccountRepository, times(1)).save(userAccountFrom);
        verify(userAccountRepository, times(1)).save(userAccountTo);
        verify(transactionRepository, times(2)).save(any(Transaction.class));
    }

    @Test
    public void testTransfer_SameAccount() {
        ApplicationException thrown = assertThrows(
                ApplicationException.class,
                () -> paymentService.transfer(accountNumberFrom, accountNumberFrom, transferAmountDto));
        assertEquals(ErrorsFinal.SAME_ACCOUNT, thrown.getMessage());
    }

    @Test
    public void testTransfer_SourceAccountNotFound() {
        when(userAccountRepository.getByNumber(accountNumberFrom)).thenReturn(Collections.emptyList());

        ApplicationException thrown = assertThrows(
                ApplicationException.class,
                () -> paymentService.transfer(accountNumberFrom, accountNumberTo, transferAmountDto));
        assertEquals(ErrorsFinal.ACCOUNT_NUMBER_NOT_FOUND, thrown.getMessage());
    }

    @Test
    public void testTransfer_InsufficientBalance() {
        userAccountFrom.setBalance(new BigDecimal("50.00")); // Less than transfer amount
        when(userAccountRepository.getByNumber(accountNumberFrom))
                .thenReturn(Collections.singletonList(userAccountFrom));

        ApplicationException thrown = assertThrows(
                ApplicationException.class,
                () -> paymentService.transfer(accountNumberFrom, accountNumberTo, transferAmountDto));
        assertEquals(ErrorsFinal.NOT_ENOUGH_BALANCE, thrown.getMessage());
    }

    @Test
    public void testTransfer_DestinationAccountNotFoundOrInactive() {
        when(userAccountRepository.getByNumber(accountNumberFrom))
                .thenReturn(Collections.singletonList(userAccountFrom));
        when(userAccountRepository.getByNumberAndStatus(accountNumberTo, '0'))
                .thenReturn(Collections.singletonList(userAccountTo));

        ApplicationException thrown = assertThrows(
                ApplicationException.class,
                () -> paymentService.transfer(accountNumberFrom, accountNumberTo, transferAmountDto));
        assertEquals(ErrorsFinal.ACCOUNT_NUMBER_NOT_FOUND, thrown.getMessage());
    }

}
