package com.smartbank;

import com.smartbank.javalin.JavalinApp;

public class StartApp
{
   public static void main( String[] args ) throws Exception
   {
      final SmartBankApp smartBankApp = new SmartBankApp();
      smartBankApp.start( JavalinApp.get() );
   }
}
