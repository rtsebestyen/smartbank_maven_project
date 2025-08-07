package com.smartbank.card;

import static com.smartbank.parameters.QueryParameter.ACCOUNT_ID;
import static com.smartbank.parameters.QueryParameter.AMOUNT;
import static com.smartbank.parameters.QueryParameter.CARD_ID;
import static com.smartbank.parameters.QueryParameter.CARD_TYPE;
import static com.smartbank.parameters.QueryParameter.PERSON_ID;

import java.util.List;

import com.smartbank.account.exceptions.InsufficientFundsException;
import com.smartbank.card.model.Card;
import com.smartbank.parameters.QueryParameterRetriever;
import com.smartbank.parameters.QueryParameterValidator;
import com.smartbank.parameters.ValidationResult;

import io.javalin.http.Context;

public class CardRestController
{
   private final QueryParameterValidator parameterValidator;

   private final QueryParameterRetriever parameterRetriever;

   private final CardService cardService;

   public CardRestController( final QueryParameterValidator parameterValidator,
         final QueryParameterRetriever parameterRetriever, final CardService cardService )
   {
      this.parameterValidator = parameterValidator;
      this.parameterRetriever = parameterRetriever;
      this.cardService = cardService;
   }


   public void findAllCards( final Context context )
   {
      context.json( this.cardService.findAll() );
   }


   public void findCard( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of( CARD_ID ), context );
      if ( !result.isValid() )
      {
         context.status( 404 ).result( result.errors().toString() );
      }

      final Card account = this.cardService.findById( this.parameterRetriever.longValue( CARD_ID, context ) );
      if ( account != null )
      {
         context.json( account );
      }
      else
      {
         context.status( 404 ).result( "Card not found" );
      }
   }


   public void findBalanceForCard( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of( CARD_ID ), context );
      if ( result.isValid() )
      {
         final Long balance = this.cardService.balance( this.parameterRetriever.longValue( CARD_ID, context ) );
         context.result( "Balance: " + balance );
      }
      else
      {
         context.status( 404 ).result( result.errors().toString() );
      }
   }


   public void createCard( final Context context )
   {
      final ValidationResult result =
            this.parameterValidator.validate( List.of( PERSON_ID, ACCOUNT_ID, CARD_TYPE ), context );
      if ( result.isValid() )
      {
         final Card newCard =
               this.cardService.createNewCard( //
                     this.parameterRetriever.longValue( ACCOUNT_ID, context ), //
                     this.parameterRetriever.longValue( PERSON_ID, context ), //
                     this.parameterRetriever.cardTypeValue( CARD_TYPE, context ) );
         context.json( newCard );
      }
      else
      {
         context.status( 404 ).result( result.errors().toString() );
      }
   }


   public void depositIntoCard( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of( CARD_ID, AMOUNT ), context );
      if ( result.isValid() )
      {
         this.cardService.deposit( //
               this.parameterRetriever.longValue( CARD_ID, context ), //
               this.parameterRetriever.bigDecimal( AMOUNT, context ) );
         context.result( "Deposit successful" );
      }
      else
      {
         context.status( 404 ).result( result.errors().toString() );
      }
   }


   public void withdrawFromCard( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of( CARD_ID, AMOUNT ), context );
      if ( result.isValid() )
      {
         try
         {
            this.cardService.withdraw( //
                  this.parameterRetriever.longValue( CARD_ID, context ), //
                  this.parameterRetriever.bigDecimal( AMOUNT, context ) );
         }
         catch ( InsufficientFundsException e )
         {
            context.result( "Withdraw failed! Insufficient funds." );
         }
         context.result( "Withdraw successful" );
      }
      else
      {
         context.status( 404 ).result( result.errors().toString() );
      }
   }
}
