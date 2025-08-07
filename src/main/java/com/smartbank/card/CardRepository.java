package com.smartbank.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartbank.card.model.Card;

public class CardRepository
{
   final private Map<Long, Card> card = new HashMap<>();

   public void save( final Card card )
   {
      this.card.put( card.getId(), card );
   }


   public Card findById( Long accountId )
   {
      return this.card.get( accountId );
   }


   public List<Card> findAll()
   {
      return new ArrayList<>( this.card.values() );
   }
}
