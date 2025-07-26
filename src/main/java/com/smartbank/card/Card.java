package com.smartbank.card;

import com.smartbank.utility.IDService;

public class Card
{
   private final Long cardId;

   private final Long ownerId;

   private final CardType type;

   public Card( Long ownerId, CardType type )
   {
      this.cardId = IDService.generate();
      this.ownerId = ownerId;
      this.type = type;
   }


   public Long getCardId()
   {
      return cardId;
   }


   public Long getOwnerId()
   {
      return ownerId;
   }


   public CardType getType()
   {
      return type;
   }
}
