package az.unitech.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import az.unitech.app.logging.annotation.LogExecutionTime;
import az.unitech.app.model.TopUpAmountDto;
import az.unitech.app.model.TransferAmountDto;
import az.unitech.app.service.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payment")
@Tag(name = "Payment", description = "the Payment api")
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @LogExecutionTime
    @Operation(summary = "top-up balance", description = "there you top-up balance by accountnumber", tags = {
            "Payment" })
    @PostMapping("/top-up/{accountNumber}")
    ResponseEntity<?> topUp(@PathVariable("accountNumber") Long accountNumber,
            @RequestBody TopUpAmountDto topUpAmount) {
        return service.topUpBalance(accountNumber, topUpAmount);
    }

    @LogExecutionTime
    @Operation(summary = "transfer from one account to another", description = "there you transfer from one account to another", tags = {
            "Payment" })
    @PostMapping("/purchase/{accountNumberFrom}/{accountNumberTo}")
    ResponseEntity<?> transfer(@PathVariable("accountNumberFrom") Long accountNumberFrom,
            @PathVariable("accountNumberTo") Long accountNumberTo, @RequestBody TransferAmountDto transferAmount) {
        return service.transfer(accountNumberFrom, accountNumberTo, transferAmount);
    }


    @LogExecutionTime
    @Operation(summary = "check currency with  account ", description = "check currency with  account ", tags = {
            "Payment" })
    @GetMapping("/currency")
    ResponseEntity<?> currency(@RequestParam Long accountNumber,@RequestParam @Valid String currencyPair) {
       System.out.println("cdcsdc");
        return service.currency(accountNumber,currencyPair);
    }

}
