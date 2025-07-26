package com.smartbank.safetydeposit;

public class SafetyDepositBox
{
   private final String id;

   private final String ownerName;

   private final String contents;

   public SafetyDepositBox( String id, String ownerName, String contents )
   {
      this.id = id;
      this.ownerName = ownerName;
      this.contents = contents;
   }


   public String getId()
   {
      return id;
   }


   public String getOwnerName()
   {
      return ownerName;
   }


   public String getContents()
   {
      return contents;
   }
}
