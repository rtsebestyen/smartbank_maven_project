package com.smartbank.card;

import com.smartbank.commoninterface.RestInterface;

import io.javalin.Javalin;

public class CardRestInterface implements RestInterface
{

   private final CardRestController cardRestController;

   public CardRestInterface( final CardRestController cardRestController )
   {
      this.cardRestController = cardRestController;
   }


   @Override
   public void startController( final Javalin javalin )
   {
      javalin.get( "/cards", this.cardRestController::findAllCards );
      javalin.get( "/cards/{cardId}", this.cardRestController::findCard );
      javalin.get( "/cards/{cardId}/balance", this.cardRestController::findBalanceForCard );

      javalin.post( "/cards", this.cardRestController::createCard );
      javalin.post( "/cards/{cardId}/deposit", this.cardRestController::depositIntoCard );
      javalin.post( "/cards/{cardId}/withdraw", this.cardRestController::withdrawFromCard );
   }

}
