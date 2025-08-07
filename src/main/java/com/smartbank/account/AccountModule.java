package com.smartbank.account;

import com.smartbank.commoninterface.ModuleInterface;
import com.smartbank.parameters.QueryParameterRetriever;
import com.smartbank.parameters.QueryParameterValidator;
import com.smartbank.person.PersonService;

import io.javalin.Javalin;

public class AccountModule implements ModuleInterface
{

   private final AccountService accountService;

   private final AccountRestInterface accountRestInterface;

   public AccountModule( final PersonService personService )
   {
      final AccountRepository accountRepository = new AccountRepository();
      this.accountService = new AccountService( accountRepository, personService );

      final QueryParameterValidator parameterValidator = new QueryParameterValidator();
      final QueryParameterRetriever parameterRetriever = new QueryParameterRetriever();
      final AccountRestController accountRestController =
            new AccountRestController( this.accountService, parameterValidator, parameterRetriever );

      this.accountRestInterface = new AccountRestInterface( accountRestController );
   }



   @Override
   public void initializeModule( final Javalin javalin )
   {
      this.accountRestInterface.startController( javalin );
   }


   public AccountService getService()
   {
      return this.accountService;
   }
}
