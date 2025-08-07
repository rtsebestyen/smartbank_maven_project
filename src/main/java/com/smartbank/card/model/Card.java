package com.smartbank.card.model;

import com.google.common.base.Preconditions;
import com.smartbank.utility.IDService;

public class Card
{
   private final Long cardId;

   private final Long personId;

   private final Long accountId;

   private final CardType type;

   public Card( final Long personId, final Long accountId, final CardType type )
   {
      Preconditions.checkNotNull( personId, "personId cannot be null" );
      Preconditions.checkNotNull( accountId, "accountId name cannot be null" );
      Preconditions.checkNotNull( type, "Card type cannot be null" );

      this.accountId = accountId;
      this.cardId = IDService.generate();
      this.personId = personId;
      this.type = type;
   }


   public Long getId()
   {
      return cardId;
   }


   public Long getPersonId()
   {
      return personId;
   }


   public CardType getType()
   {
      return type;
   }


   public Long getAccountId()
   {
      return accountId;
   }
}
