package com.app.currencyapi.controller;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.app.currencyapi.errors.ErrorsFinal;
import com.app.currencyapi.errors.SuccessMessage;
import com.app.currencyapi.model.CurrencyRateEnum;
import com.app.currencyapi.response.MessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Tag(name = "Currency", description = "the Currency api")
public class CurrencyApiController {
    @GetMapping("/currency-rate")
    @Operation(summary = "get currency rate by currency pair", description = "there you can get currency info", tags = {"Currency"})
    public ResponseEntity<?> getCurrency(@RequestParam @Valid String currencyPair){
        BigDecimal currencyRate = CurrencyRateEnum.getRateByCurrencyPair(currencyPair);
        if (currencyRate.equals(BigDecimal.ZERO)){
            return MessageResponse.response("",null,ErrorsFinal.DATA_NOT_FOUND,HttpStatus.BAD_REQUEST); 
        }
        return MessageResponse.response(SuccessMessage.SUCCESS_GET, currencyRate, null, HttpStatus.OK);
    }
}