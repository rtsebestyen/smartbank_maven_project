package com.smartbank.exchange;

import java.math.BigDecimal;
import java.util.Map;

import com.smartbank.currency.Currency;

public class ExchangeService
{
   private final Map<Currency, BigDecimal> exchangeRates =
         Map.of( Currency.USD, BigDecimal.valueOf( 1.10 ), //
               Currency.HUF, BigDecimal.valueOf( 390.0 ), //
               Currency.RON, BigDecimal.valueOf( 5.0 ) );

   public BigDecimal convert( BigDecimal amount, Currency from, Currency to )
   {
      if ( from == to )
         return amount;

      final BigDecimal inEuro = from == Currency.EURO ? //
            amount : //
            amount.divide( exchangeRates.get( from ), 2, BigDecimal.ROUND_HALF_UP );

      return to == Currency.EURO ? //
            inEuro : //
            inEuro.multiply( exchangeRates.get( to ) );
   }
}
