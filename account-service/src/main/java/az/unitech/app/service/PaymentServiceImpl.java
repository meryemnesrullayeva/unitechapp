package az.unitech.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import az.unitech.app.entity.Transaction;
import az.unitech.app.entity.TransactionType;
import az.unitech.app.entity.UserAccount;
import az.unitech.app.errors.ErrorsFinal;
import az.unitech.app.errors.SuccessMessage;
import az.unitech.app.exception.ApplicationException;
import az.unitech.app.mapper.TransactionMapper;
import az.unitech.app.model.TopUpAmountDto;
import az.unitech.app.model.TransactionBaseDto;
import az.unitech.app.model.TransactionTypeEnum;
import az.unitech.app.model.TransferAmountDto;
import az.unitech.app.repository.TransactionRepository;
import az.unitech.app.repository.UserAccountRepository;
import az.unitech.app.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CurrencyApiService currencyApiService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<?> topUpBalance(Long accountNumber, TopUpAmountDto amountDto) {

        List<UserAccount> userAccounts = userAccountRepository.getByNumber(accountNumber);
        if (userAccounts.isEmpty()) {
            throw new ApplicationException(ErrorsFinal.ACCOUNT_NUMBER_NOT_FOUND);
        }
        if (amountDto.getTopUpAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApplicationException(ErrorsFinal.WRONG_AMOUNT);
        }
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amountDto.getTopUpAmount());
        TransactionType topUpTransaction = new TransactionType(TransactionTypeEnum.TOP_UP.getId());
        topUpTransaction.setName(TransactionTypeEnum.TOP_UP.getName());
        transaction.setTransactionType(topUpTransaction);
        String transactionUUID = UUID.randomUUID().toString();
        transaction.setTransactionUUId(transactionUUID);
        transactionRepository.save(transaction);

        TransactionBaseDto transactionBaseDto = transactionMapper.entityToDto(transaction);
        return MessageResponse.response(SuccessMessage.SUCCESS_ADD, transactionBaseDto, null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> transfer(Long accountNumberFrom, Long accountNumberTo,
            TransferAmountDto transferAmountDto) {
        if (accountNumberFrom.equals(accountNumberTo)) {
            throw new ApplicationException(ErrorsFinal.SAME_ACCOUNT);
        }

        List<UserAccount> userAccountFroms = userAccountRepository.getByNumber(accountNumberFrom);
        if (userAccountFroms.isEmpty()) {
            throw new ApplicationException(ErrorsFinal.ACCOUNT_NUMBER_NOT_FOUND);
        }

        UserAccount userAccountFrom = userAccountFroms.get(0);
        if (userAccountFrom.getBalance().compareTo(transferAmountDto.getTransferAmount()) < 0) {
            throw new ApplicationException(ErrorsFinal.NOT_ENOUGH_BALANCE);
        }

        List<UserAccount> userAccountToDeactives = userAccountRepository.getByNumberAndStatus(accountNumberTo, '0');
        if (!userAccountToDeactives.isEmpty()) {
            throw new ApplicationException(ErrorsFinal.ACCOUNT_NUMBER_NOT_FOUND);
        }

        List<UserAccount> userAccountToAlls = userAccountRepository.getByAccountNumber(accountNumberTo);
        if (!userAccountToAlls.isEmpty()) {
            throw new ApplicationException(ErrorsFinal.ACCOUNT_NUMBER_NOT_FOUND);
        }

        userAccountFrom.setBalance(userAccountFrom.getBalance().subtract(transferAmountDto.getTransferAmount()));
        userAccountRepository.save(userAccountFrom);

        Transaction transactionFrom = new Transaction();
        transactionFrom.setAccountNumber(accountNumberFrom);
        transactionFrom.setAmount(transferAmountDto.getTransferAmount());
        TransactionType transferTransaction = new TransactionType(TransactionTypeEnum.TRANSFER.getId());
        transferTransaction.setName(TransactionTypeEnum.TRANSFER.getName());
        transactionFrom.setTransactionType(transferTransaction);
        String transactionFromUUID = UUID.randomUUID().toString();
        transactionFrom.setTransactionUUId(transactionFromUUID);
        transactionRepository.save(transactionFrom);

        List<UserAccount> userAccountTos = userAccountRepository.getByNumber(accountNumberTo);
        UserAccount userAccount = userAccountTos.get(0);
        userAccount.setBalance(userAccount.getBalance().add(transferAmountDto.getTransferAmount()));
        userAccountRepository.save(userAccount);

        Transaction transactionTo = new Transaction();
        transactionTo.setAccountNumber(accountNumberFrom);
        transactionTo.setAmount(transferAmountDto.getTransferAmount());
        TransactionType topUpTransaction = new TransactionType(TransactionTypeEnum.TOP_UP.getId());
        topUpTransaction.setName(TransactionTypeEnum.TOP_UP.getName());
        transactionTo.setTransactionType(topUpTransaction);
        String transactionToUUID = UUID.randomUUID().toString();
        transactionTo.setTransactionUUId(transactionToUUID);
        transactionRepository.save(transactionTo);

        return null;
    }

    @Override
    public ResponseEntity<?> currency(Long accountNumber, String currencyPair) {
        List<UserAccount> userAccountFroms = userAccountRepository.getByNumber(accountNumber);
        if (userAccountFroms.isEmpty()) {
            throw new ApplicationException(ErrorsFinal.ACCOUNT_NUMBER_NOT_FOUND);
        }

        UserAccount userAccountFrom = userAccountFroms.get(0);
        if (userAccountFrom.getBalance().compareTo(new BigDecimal(0.1)) < 0) {
            throw new ApplicationException(ErrorsFinal.NOT_ENOUGH_BALANCE);
        }
        BigDecimal currency=null;
        try{
             currency = currencyApiService.currency(currencyPair);
        }
        catch (Exception e) {
            throw new ApplicationException(ErrorsFinal.CURRENCY_SERVICE_ERROR);
        }

        userAccountFrom.setBalance(userAccountFrom.getBalance().subtract(new BigDecimal(0.1)));
        userAccountRepository.save(userAccountFrom);
        return MessageResponse.response(SuccessMessage.SUCCESS_ADD, currency, null, HttpStatus.OK);
    }
}
