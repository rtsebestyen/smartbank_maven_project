package com.smartbank.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartbank.account.model.Account;

public class AccountRepository
{
   private final Map<Long, Account> store = Collections.synchronizedMap( new HashMap<>() );

   public void save( Account account )
   {
      store.put( account.getId(), account );
   }


   public Account findById( String id )
   {
      return store.get( id );
   }


   public List<Account> findAll()
   {
      return new ArrayList<>( store.values() );
   }
}
