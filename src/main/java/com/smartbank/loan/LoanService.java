package com.smartbank.loan;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoanService
{
   private final Map<String, Loan> loanStore = new HashMap<>();

   public Loan createLoan( BigDecimal amount, double interestRate )
   {
      String id = UUID.randomUUID().toString();
      Loan loan = new Loan( id, amount, interestRate );
      loanStore.put( id, loan );
      return loan;
   }


   public BigDecimal getRepayableAmount( String loanId )
   {
      Loan loan = loanStore.get( loanId );
      return loan.calculateTotalRepayable();
   }
}
