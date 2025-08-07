package com.smartbank.account;

import static com.smartbank.parameters.QueryParameter.ACCOUNT_ID;
import static com.smartbank.parameters.QueryParameter.ACCOUNT_TYPE;
import static com.smartbank.parameters.QueryParameter.AMOUNT;
import static com.smartbank.parameters.QueryParameter.PERSON_ID;

import java.util.List;

import com.smartbank.account.exceptions.InsufficientFundsException;
import com.smartbank.account.model.Account;
import com.smartbank.parameters.QueryParameterRetriever;
import com.smartbank.parameters.QueryParameterValidator;
import com.smartbank.parameters.ValidationResult;

import io.javalin.http.Context;

public class AccountRestController
{
   private final AccountService accountService;

   private final QueryParameterValidator parameterValidator;

   private final QueryParameterRetriever parameterRetriever;

   public AccountRestController( final AccountService accountService, final QueryParameterValidator parameterValidator,
         final QueryParameterRetriever parameterRetriever )
   {
      this.accountService = accountService;
      this.parameterValidator = parameterValidator;
      this.parameterRetriever = parameterRetriever;
   }


   public void findAllAccounts( final Context context )
   {
      List<Account> accounts = this.accountService.findAll();
      context.json( accounts );
   }


   public void findAccount( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of( ACCOUNT_ID ), context );
      if ( !result.isValid() )
      {
         context.status( 404 ).result( result.errors().toString() );
      }

      Account account = this.accountService.findById( this.parameterRetriever.longValue( ACCOUNT_ID, context ) );
      if ( account != null )
      {
         context.json( account );
      }
      else
      {
         context.status( 404 ).result( "Account not found" );
      }
   }


   public void findBalanceForAccount( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of( ACCOUNT_ID ), context );
      if ( result.isValid() )
      {
         Long balance = this.accountService.balance( this.parameterRetriever.longValue( ACCOUNT_ID, context ) );
         context.result( "Balance: " + balance );
      }
      else
      {
         context.status( 404 ).result( result.errors().toString() );
      }
   }


   public void createAccount( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of(PERSON_ID, ACCOUNT_TYPE ), context );
      if ( result.isValid() )
      {
         Account newAccount =
               this.accountService.createNewAccount( //
                     this.parameterRetriever.longValue(PERSON_ID, context ), //
                     this.parameterRetriever.accountTypeVlue( ACCOUNT_TYPE, context ) );
         context.json( newAccount );
      }
      else
      {
         context.status( 404 ).result( result.errors().toString() );
      }
   }


   public void depositIntoAccount( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of( ACCOUNT_ID, AMOUNT ), context );
      if ( result.isValid() )
      {
         this.accountService.deposit( //
               this.parameterRetriever.longValue( ACCOUNT_ID, context ), //
               this.parameterRetriever.bigDecimal( AMOUNT, context ) );
         context.result( "Deposit successful" );
      }
      else
      {
         context.status( 404 ).result( result.errors().toString() );
      }
   }


   public void withdrawFromAccount( final Context context )
   {
      final ValidationResult result = this.parameterValidator.validate( List.of( ACCOUNT_ID, AMOUNT ), context );
      if ( result.isValid() )
      {
         try
         {
            this.accountService.withdraw( //
                  this.parameterRetriever.longValue( ACCOUNT_ID, context ), //
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
