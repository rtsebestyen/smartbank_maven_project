package com.smartbank.account.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.smartbank.transaction.Transaction;
import com.smartbank.transaction.TransactionType;
import com.smartbank.utility.IDService;

public class Account
{
   private final Long id;

   private final Long ownerId;

   private final AccountType type;

   private final List<Transaction> transactions = new ArrayList<>();

   private BigDecimal balance;

   public Account( Long ownerId, AccountType type )
   {
      this.id = IDService.generate();
      this.ownerId = ownerId;
      this.type = type;
      this.balance = BigDecimal.ZERO;
   }


   public void deposit( BigDecimal amount )
   {
      if ( amount.signum() <= 0 )
         throw new IllegalArgumentException( "Amount must be positive" );
      this.balance = this.balance.add( amount );
      transactions.add( new Transaction( TransactionType.DEPOSIT, amount ) );
   }


   public void withdraw( BigDecimal amount )
   {
      if ( amount.signum() <= 0 )
         throw new IllegalArgumentException( "Amount must be positive" );
      if ( this.balance.compareTo( amount ) < 0 )
         throw new IllegalStateException( "Insufficient funds" );
      this.balance = this.balance.subtract( amount );
      transactions.add( new Transaction( TransactionType.WITHDRAW, amount ) );
   }


   public void applyInterest( double rate )
   {
      BigDecimal interest = this.balance.multiply( BigDecimal.valueOf( rate ) );
      deposit( interest );
   }


   public Long getId()
   {
      return id;
   }


   public BigDecimal getBalance()
   {
      return balance;
   }


   public AccountType getType()
   {
      return type;
   }


   public Long getOwnerId()
   {
      return ownerId;
   }


   public List<Transaction> getTransactions()
   {
      return transactions;
   }
}
