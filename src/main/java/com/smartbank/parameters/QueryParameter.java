package com.smartbank.parameters;

import java.math.BigDecimal;

import com.smartbank.account.model.AccountType;
import com.smartbank.card.model.CardType;

public enum QueryParameter
{
   ACCOUNT_ID("accountId", Long.class), //
   PERSON_ID("personId", Long.class), //
   ACCOUNT_TYPE("accountType", AccountType.class),
   AMOUNT("amount", BigDecimal.class),
   CARD_ID("cardId", Long.class),
   CARD_TYPE("cardType", CardType.class);

   private final String queryParam;

   private final Class<?> internalType;

   QueryParameter( String queryParam, Class<?> typeExpected )
   {
      this.queryParam = queryParam;
      this.internalType = typeExpected;
   }


   public String getQueryParam()
   {
      return queryParam;
   }


   public Class<?> getInternalType()
   {
      return internalType;
   }

}
