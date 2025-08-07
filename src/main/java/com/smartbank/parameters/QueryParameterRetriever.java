package com.smartbank.parameters;

import java.math.BigDecimal;

import com.smartbank.account.model.AccountType;
import com.smartbank.card.model.CardType;

import io.javalin.http.Context;

public class QueryParameterRetriever
{

   public Long longValue( final QueryParameter queryParameter, final Context context )
   {
      return Long.valueOf( getParamValue( queryParameter, context ) );
   }


   public AccountType accountTypeVlue( final QueryParameter queryParameter, final Context context )
   {
      return AccountType.valueOf( getParamValue( queryParameter, context ) );
   }


   public CardType cardTypeValue( final QueryParameter queryParameter, final Context context )
   {
      return CardType.valueOf( getParamValue( queryParameter, context ) );
   }


   public BigDecimal bigDecimal( final QueryParameter queryParameter, final Context context )
   {
      return new BigDecimal( getParamValue( queryParameter, context ) );
   }


   private String getParamValue( final QueryParameter expectedParam, final Context acualContext )
   {
      String queryParamValue = acualContext.queryParam( expectedParam.getQueryParam() );
      if ( queryParamValue == null )
      {
         return acualContext.pathParam( expectedParam.getQueryParam() );
      }
      return queryParamValue;
   }

}
