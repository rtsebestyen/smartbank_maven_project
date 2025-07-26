package com.smartbank.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService
{

   private final Map<Long, Person> personRepository;

   public PersonService( )
   {
      this.personRepository = new HashMap<>();
   }


   public Person createNewPerson(final String firstName, final String lastName, final String birthDate )
   {
      Person person =  Person.create( firstName, lastName, birthDate );
      this.personRepository.put( person.getId(), person );
      return person;
   }


   public Person findById( final Long id )
   {
      return this.personRepository.get( id );
   }


   public List<Person> findAll()
   {
      return new ArrayList<>( this.personRepository.values() );
   }
}
