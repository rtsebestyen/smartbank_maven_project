package com.smartbank.parameters;

import java.util.Collections;
import java.util.List;

public class ValidationResult
{

   private final boolean isValid;

   private final List<String> errors;

   public static ValidationResult invalid( final List<String> errors )
   {
      return new ValidationResult( false, errors );
   }


   public static ValidationResult valid()
   {
      return new ValidationResult( true, Collections.emptyList() );
   }


   private ValidationResult( final boolean isValid, final List<String> errors )
   {
      this.isValid = isValid;
      this.errors = errors;
   }


   public boolean isValid()
   {
      return isValid;
   }


   public List<String> errors()
   {
      return errors;
   }

}
