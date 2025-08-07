package com.smartbank.card;

import com.smartbank.account.AccountService;
import com.smartbank.commoninterface.ModuleInterface;
import com.smartbank.parameters.QueryParameterRetriever;
import com.smartbank.parameters.QueryParameterValidator;
import com.smartbank.person.PersonService;

import io.javalin.Javalin;

public class CardModule implements ModuleInterface
{

   private final CardService cardService;

   private final CardRestInterface cardRestInterface;

   public CardModule( final AccountService accountService, PersonService personService )
   {
      final CardRepository accountRepository = new CardRepository();
      this.cardService = new CardService( accountRepository, accountService, personService );

      final QueryParameterValidator parameterValidator = new QueryParameterValidator();
      final QueryParameterRetriever parameterRetriever = new QueryParameterRetriever();
      final CardRestController accountRestController =
            new CardRestController( parameterValidator, parameterRetriever, this.cardService );

      this.cardRestInterface = new CardRestInterface( accountRestController );
   }


   @Override
   public void initializeModule( final Javalin javalin )
   {
      this.cardRestInterface.startController( javalin );
   }


   public CardService getService()
   {
      return this.cardService;
   }
}
