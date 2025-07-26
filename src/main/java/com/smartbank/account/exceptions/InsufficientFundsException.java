package com.smartbank.account.exceptions;

public class InsufficientFundsException extends Exception
{
   public InsufficientFundsException( String message, Throwable cause )
   {
      super( message, cause );
   }
}
