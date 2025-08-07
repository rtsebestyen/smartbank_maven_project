package com.smartbank.utility;

public class IDService
{

   private static Long lastGenerated = 0L;

   public static Long generate()
   {
      return ++lastGenerated;
   }

}
