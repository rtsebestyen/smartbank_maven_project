package com.smartbank.person;

import com.smartbank.commoninterface.ModuleInterface;

import io.javalin.Javalin;

public class PersonModule implements ModuleInterface
{

   private final PersonService personService;

   private final PersonRestController personRestController;

   public PersonModule( )
   {
      this.personService = new PersonService();
      this.personRestController = new PersonRestController( this.personService );
   }


   public PersonService getService()
   {
      return this.personService;
   }


   @Override
   public void initializeModule( final Javalin javalin )
   {
      this.personRestController.startController( javalin );
   }
}
