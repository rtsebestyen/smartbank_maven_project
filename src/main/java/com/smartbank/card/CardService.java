package com.smartbank.card;

import java.math.BigDecimal;
import java.util.List;

import com.smartbank.account.AccountService;
import com.smartbank.account.exceptions.InsufficientFundsException;
import com.smartbank.account.model.Account;
import com.smartbank.card.model.Card;
import com.smartbank.card.model.CardType;
import com.smartbank.person.Person;
import com.smartbank.person.PersonService;

public class CardService
{

   private final CardRepository cardRepository;

   private final AccountService accountService;

   private final PersonService personService;

   public CardService( final CardRepository cardRepository, final AccountService accountService,
         final PersonService personService )
   {
      this.cardRepository = cardRepository;
      this.accountService = accountService;
      this.personService = personService;
   }


   public List<Card> findAll()
   {
      return this.cardRepository.findAll();
   }


   public Card findById( final Long cardId )
   {
      return this.cardRepository.findById( cardId );
   }


   public Card createNewCard( final Long accountId, final Long personId, final CardType type )
   {
      final Person person = this.personService.findById( personId );
      final Account account = this.accountService.findById( accountId );
      if ( person == null && account == null )
      {
         return null;
      }
      final Card card = new Card( personId, accountId, type );
      this.cardRepository.save( card );
      return card;
   }


   public Long balance( final Long cardId )
   {
      final Card card = findById( cardId );
      return this.accountService.balance( card.getAccountId() );
   }


   public void deposit( final Long cardId, final BigDecimal amount )
   {
      final Card card = findById( cardId );
      this.accountService.deposit( card.getAccountId(), amount );
   }


   public void withdraw( final Long cardId, final BigDecimal amount ) throws InsufficientFundsException
   {
      final Card card = findById( cardId );
      this.accountService.withdraw( card.getAccountId(), amount );
   }

}
