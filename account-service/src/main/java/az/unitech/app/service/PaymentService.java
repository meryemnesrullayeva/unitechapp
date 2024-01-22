package az.unitech.app.service;

import org.springframework.http.ResponseEntity;

import az.unitech.app.model.TopUpAmountDto;
import az.unitech.app.model.TransferAmountDto;

public interface PaymentService {

    ResponseEntity<?> topUpBalance(Long accountNumber, TopUpAmountDto amountDto);

    ResponseEntity<?> transfer(Long accountNumberFrom, Long accountNumberTo, TransferAmountDto transferAmountDto);

    ResponseEntity<?> currency(Long accountNumber,String currencyPair);
}
