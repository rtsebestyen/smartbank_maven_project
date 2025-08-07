package com.smartbank;

import java.util.ArrayList;
import java.util.List;

import com.smartbank.account.AccountModule;
import com.smartbank.card.CardModule;
import com.smartbank.commoninterface.ModuleInterface;
import com.smartbank.person.PersonModule;
import com.smartbank.person.PersonService;

import io.javalin.Javalin;

public class SmartBankApp
{
   private final List<ModuleInterface> modules = new ArrayList<>();

   public SmartBankApp( )
   {
      final PersonModule personModule = new PersonModule();
      final PersonService personService = personModule.getService();

      final AccountModule accountModule = new AccountModule( personService );

      final ModuleInterface cardModule = new CardModule( accountModule.getService(), personService );

      this.modules.add( personModule );
      this.modules.add( accountModule );
      this.modules.add( cardModule );
   }


   public void start( final Javalin javalin )
   {
      this.modules.forEach( module -> module.initializeModule( javalin ) );
   }
}
