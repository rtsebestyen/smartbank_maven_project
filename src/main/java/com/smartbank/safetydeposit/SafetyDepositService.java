package com.smartbank.safetydeposit;

import java.util.HashMap;
import java.util.Map;

public class SafetyDepositService
{
   private final Map<String, SafetyDepositBox> boxes = new HashMap<>();

   public void createBox( String id, String owner, String contents )
   {
      boxes.put( id, new SafetyDepositBox( id, owner, contents ) );
   }


   public SafetyDepositBox getBox( String id )
   {
      return boxes.get( id );
   }
}
