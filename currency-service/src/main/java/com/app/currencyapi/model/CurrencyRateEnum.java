package com.app.currencyapi.model;

import java.math.BigDecimal;
import java.util.Random;

public enum CurrencyRateEnum {
    AZN_USD("AZN/USD", BigDecimal.valueOf(0.6)),
    USD_AZN("USD/AZN", BigDecimal.valueOf(1.7)),
    AZN_TL("AZN/TL", BigDecimal.valueOf(17.77)),
    TL_AZN("TL/AZN", BigDecimal.valueOf(0.06));

    final String currencyPair;
    final BigDecimal rate;

    CurrencyRateEnum(String currencyPair,BigDecimal rate){
        this.currencyPair = currencyPair;
        this.rate = rate;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }
    
    public BigDecimal getRate() {
        return rate;
    }

    public static BigDecimal getRateByCurrencyPair(String currencyPair) {
        try {
            for (CurrencyRateEnum rateEnum : CurrencyRateEnum.values()) {
                if (rateEnum.getCurrencyPair().equals(currencyPair)) {
                    Random random = new Random();
                    return rateEnum.getRate().add(BigDecimal.valueOf(random.nextDouble() * 0.2 - 0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }
}
