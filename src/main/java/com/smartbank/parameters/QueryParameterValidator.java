package com.smartbank.parameters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.javalin.http.Context;

public class QueryParameterValidator
{

   public ValidationResult validate( final List<QueryParameter> expectedParams, final Context javalinContext )
   {
      final List<String> errors = new ArrayList<>();
      final Map<QueryParameter, String> parameterValues = new HashMap<>();
      String actualParamValue = null;
      for ( final QueryParameter ep : expectedParams )
      {
         actualParamValue = getParamValue( javalinContext, ep );
         if ( paramIsPresent( actualParamValue ) && paramHasValidType( ep, actualParamValue, errors ) )
         {
            parameterValues.put( ep, actualParamValue );
         }
      }

      if ( parameterValues.size() != expectedParams.size() )
      {
         return ValidationResult.invalid( errors );
      }

      return ValidationResult.valid();
   }


   private boolean paramHasValidType( final QueryParameter expectedParam, final String value, List<String> errors )
   {
      Class<?> targetType = expectedParam.getInternalType();
      try
      {
         if ( targetType == Long.class || targetType == long.class || targetType == BigDecimal.class )
         {
            Long.parseLong( value );
         }
         else if ( targetType == Integer.class || targetType == int.class )
         {
            Integer.parseInt( value );
         }
         else if ( targetType == Double.class || targetType == double.class )
         {
            Double.parseDouble( value );
         }
         else if ( targetType == Boolean.class || targetType == boolean.class )
         {
            if ( !value.equalsIgnoreCase( "true" ) && !value.equalsIgnoreCase( "false" ) )
            {
               errors.add( createErrorMessage( expectedParam, "is not a boolean", value ) );
               return false;
            }
         }
         else if ( targetType == String.class )
         {
            return true;
         }
         else if ( targetType.isEnum() )
         {
            return validateEnum( value, ( Class<? extends Enum> ) targetType, errors, expectedParam );
         }
         else
         {
            errors.add( createErrorMessage( expectedParam, "is not supported type", value ) );
            return false;
         }
         return true;
      }
      catch ( Exception e )
      {
         errors.add( createErrorMessage( expectedParam, "could not parse value", value ) );
         return false;
      }
   }


   private boolean validateEnum( String value, Class<? extends Enum> targetType, List<String> errors,
         QueryParameter expectedParam )
   {
      try
      {
         @SuppressWarnings("unchecked")
         Class<? extends Enum> enumType = targetType;
         Enum.valueOf( enumType, value ); // throws if invalid
      }
      catch ( Exception e )
      {
         errors.add( createErrorMessage( expectedParam, "incorrect enum value", value ) );
         return false;
      }
      return true;
   }


   private String createErrorMessage( QueryParameter expectedParam, String message, String value )
   {
      return String.format( "Query parameter \"%s\" with value \"%s\" is invalid because: %s",
            expectedParam.getQueryParam(), value, message ); // invert
   }


   private boolean paramIsPresent( String value )
   {
      return value != null && !value.trim().isEmpty() && !value.isBlank();
   }


   private String getParamValue( final Context acualContext, final QueryParameter expectedParam )
   {
      String queryParamValue = acualContext.queryParam( expectedParam.getQueryParam() );
      if ( queryParamValue == null )
      {
         return acualContext.pathParam( expectedParam.getQueryParam() );
      }
      return queryParamValue;
   }
}
