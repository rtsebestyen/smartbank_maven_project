package com.smartbank.person;

import java.time.LocalDate;
import java.time.Period;

import com.google.common.base.Preconditions;
import com.smartbank.utility.IDService;

public class Person
{

   private Long id;

   private String firstName;

   private String lastName;

   private LocalDate birthDate;

   public static Person create( final String firstName, final String lastName, final String birthDate )
   {
      Preconditions.checkNotNull( firstName, "First name cannot be null" );
      Preconditions.checkArgument( !firstName.isBlank(), "First name cannot be blank" );

      Preconditions.checkNotNull( lastName, "Last name cannot be null" );
      Preconditions.checkArgument( !lastName.isBlank(), "Last name cannot be blank" );

      Preconditions.checkNotNull( birthDate, "Birth date cannot be null" );
      Preconditions.checkArgument( !birthDate.isBlank(), "Birth date cannot be blank" );
      final LocalDate birthLocalDate = LocalDate.parse( birthDate );
      Preconditions.checkArgument( calculateAge( birthLocalDate ) >= 18, "Must be at least 18 years old" );

      return new Person( firstName, lastName, birthLocalDate );
   }


   private Person( final String firstName, final String lastName, final LocalDate birthDate )
   {
      this.id = IDService.generate();
      this.firstName = firstName;
      this.lastName = lastName;
      this.birthDate = birthDate;
   }


   private static int calculateAge( final LocalDate birthDate )
   {
      final LocalDate now = LocalDate.now();
      final Period age = Period.between( birthDate, now );
      return age.getYears();
   }


   public Long getId()
   {
      return id;
   }


   public String getFirstName()
   {
      return firstName;
   }


   public String getLastName()
   {
      return lastName;
   }


   public LocalDate getBirthDate()
   {
      return birthDate;
   }
}
