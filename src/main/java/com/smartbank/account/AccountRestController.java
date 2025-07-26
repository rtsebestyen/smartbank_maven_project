package com.smartbank.account;

import java.math.BigDecimal;
import java.util.List;

import com.smartbank.account.model.Account;
import com.smartbank.commoninterface.RestControllerInterface;

import io.javalin.Javalin;

public class AccountRestController implements RestControllerInterface
{
   private final AccountService accountService;

   public AccountRestController( AccountService accountService )
   {
      this.accountService = accountService;
   }


   @Override
   public void startController( final Javalin javalin )
   {
      javalin.get( "/accounts", ctx ->
      {
         List<Account> accounts = this.accountService.findAll();
         ctx.json( accounts );
      } );

      javalin.post( "/account", ctx ->
      {
         String ownerId = ctx.queryParam( "ownerId" );
         String type = ctx.queryParam( "type" );

         Account newAccount = this.accountService.createNewAccount( ownerId, type );

         ctx.json( newAccount );
      } );

      javalin.post( "/accounts/{id}/deposit", ctx ->
      {
         String id = ctx.pathParam( "id" );
         BigDecimal amount = new BigDecimal( ctx.queryParam( "amount" ) );
         accountService.deposit( id, amount );
         ctx.result( "Deposit successful" );
      } );

      javalin.post( "/accounts/{id}/withdraw", ctx ->
      {
         String id = ctx.pathParam( "id" );
         BigDecimal amount = new BigDecimal( ctx.queryParam( "amount" ) );
         accountService.withdraw( id, amount );
         ctx.result( "Withdraw successful" );
      } );

      javalin.get( "/accounts/{id}", ctx ->
      {
         String id = ctx.pathParam( "id" );
         Account account = this.accountService.findById( id );
         if ( account != null )
         {
            ctx.json( account );
         }
         else
         {
            ctx.status( 404 ).result( "Account not found" );
         }
      } );

      javalin.get( "/accounts/rich", ctx ->
      {
         BigDecimal threshold = new BigDecimal( ctx.queryParam( "threshold" ) );
         ctx.json( accountService.findRichAccounts( threshold ) );
      } );

      javalin.post( "/accounts/apply-interest", ctx ->
      {
         double rate = Double.parseDouble( ctx.queryParam( "rate" ) );
         accountService.applyInterestToSavings( rate );
         ctx.result( "Interest applied" );
      } );
   }
}
