package com.smartbank.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartbank.account.model.Account;

public class CardService
{
   private final Map<Long, List<Card>> cards = new HashMap<>();

   public void issueCard( Account account, CardType type )
   {
      cards.computeIfAbsent( account.getId(), k -> new ArrayList<>() ).add( new Card( account.getOwnerId(), type ) );
   }


   public List<Card> getCards( Long accountId )
   {
      return cards.getOrDefault( accountId, List.of() );
   }
}
