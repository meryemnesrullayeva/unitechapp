package az.unitech.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import az.unitech.app.model.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpMethod;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;


@Service
@RequiredArgsConstructor
public class CurrencyApiServiceImpl implements CurrencyApiService {

   @Value("${currency.url}")
    private String currencyApiUrl;

    private final ApiCallService apiCallService;

    public BigDecimal currency(String currencyPair) {
        String urlToApiService = currencyApiUrl + "/currency-rate?currencyPair="+currencyPair;
        ResponseEntity<ApiResponse<BigDecimal>> response = apiCallService.performRequestForSingleObj(urlToApiService, HttpMethod.GET, null, null,
                new ParameterizedTypeReference<>() {
                });
        ApiResponse<BigDecimal> result = response.getBody();
        if (result != null) {
            return result.getData();
        }
        return null;
    }
}
