package com.smartbank.person;

import com.smartbank.commoninterface.RestInterface;

import io.javalin.Javalin;

public class PersonRestInterface implements RestInterface
{
   private final PersonRestController personRestController;

   public PersonRestInterface( final PersonRestController personRestController )
   {
      this.personRestController = personRestController;
   }


   @Override
   public void startController( final Javalin javalin )
   {
      javalin.get( "/persons", this.personRestController::findEveryPerson );
      javalin.get( "/persons/{personId}", this.personRestController::findPerson );

      javalin.post( "/persons", this.personRestController::createPerson );
   }

}
