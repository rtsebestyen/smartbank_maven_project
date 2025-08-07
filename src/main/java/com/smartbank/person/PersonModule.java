package com.smartbank.person;

import com.smartbank.commoninterface.ModuleInterface;

import io.javalin.Javalin;

public class PersonModule implements ModuleInterface
{

   private final PersonService personService;

   private final PersonRestInterface personRestInterface;

   public PersonModule( )
   {
      this.personService = new PersonService();
      final PersonRestController personRestController = new PersonRestController( this.personService );
      this.personRestInterface = new PersonRestInterface( personRestController );
   }


   public PersonService getService()
   {
      return this.personService;
   }


   @Override
   public void initializeModule( final Javalin javalin )
   {
      this.personRestInterface.startController( javalin );
   }
}
