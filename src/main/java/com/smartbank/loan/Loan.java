package com.smartbank.loan;

import java.math.BigDecimal;

public class Loan
{
   private final String loanId;

   private final BigDecimal amount;

   private final double interestRate;

   public Loan( String loanId, BigDecimal amount, double interestRate )
   {
      this.loanId = loanId;
      this.amount = amount;
      this.interestRate = interestRate;
   }


   public BigDecimal calculateTotalRepayable()
   {
      return amount.add( amount.multiply( BigDecimal.valueOf( interestRate ) ) );
   }


   public String getLoanId()
   {
      return loanId;
   }


   public BigDecimal getAmount()
   {
      return amount;
   }


   public double getInterestRate()
   {
      return interestRate;
   }
}
