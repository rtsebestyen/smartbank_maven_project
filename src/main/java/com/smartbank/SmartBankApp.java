package com.smartbank;

import java.util.ArrayList;
import java.util.List;

import com.smartbank.account.AccountModule;
import com.smartbank.commoninterface.ModuleInterface;
import com.smartbank.person.PersonModule;

import io.javalin.Javalin;

public class SmartBankApp
{
   private final List<ModuleInterface> modules = new ArrayList<>();

   public SmartBankApp( )
   {
      // create a graph for the modules and their dependencies, check cycle dependencies
      final PersonModule personModule = new PersonModule();
      final AccountModule accountModule = new AccountModule( personModule.getService() );

      this.modules.add( personModule );
      this.modules.add( accountModule );
   }


   public void start( final Javalin javalin )
   {
      this.modules.forEach( module -> module.initializeModule( javalin ) );
   }
}
