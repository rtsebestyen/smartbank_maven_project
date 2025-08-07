package com.smartbank.account;

import com.smartbank.commoninterface.RestInterface;

import io.javalin.Javalin;

public class AccountRestInterface implements RestInterface
{

   private final AccountRestController accountRestController;

   public AccountRestInterface( final AccountRestController accountRestController )
   {
      this.accountRestController = accountRestController;
   }


   @Override
   public void startController( final Javalin javalin )
   {
      javalin.get( "/accounts", this.accountRestController::findAllAccounts );
      javalin.get( "/accounts/{accountId}", this.accountRestController::findAccount );
      javalin.get( "/accounts/{accountId}/balance", this.accountRestController::findBalanceForAccount );

      javalin.post( "/accounts", this.accountRestController::createAccount );
      javalin.post( "/accounts/{accountId}/deposit", this.accountRestController::depositIntoAccount );
      javalin.post( "/accounts/{accountId}/withdraw", this.accountRestController::withdrawFromAccount );
   }

}
