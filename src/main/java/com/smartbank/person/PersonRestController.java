package com.smartbank.person;

import java.util.List;

import io.javalin.http.Context;

public class PersonRestController
{
   private final PersonService personService;

   public PersonRestController( final PersonService personService )
   {
      this.personService = personService;
   }


   public void createPerson( Context ctx )
   {
      String firstName = ctx.queryParam( "firstName" );
      String lastName = ctx.queryParam( "lastName" );
      String birthday = ctx.queryParam( "birthday" ); //yyyy-MM-dd

      final Person newPerson = this.personService.createNewPerson( firstName, lastName, birthday );

      ctx.json( newPerson );
   }


   public void findPerson( Context ctx )
   {
      String id = ctx.pathParam( "personId" );
      System.out.println( id );
      Person account = this.personService.findById( Long.valueOf( id ) );
      if ( account != null )
      {
         ctx.json( account );
      }
      else
      {
         ctx.status( 404 ).result( "Account not found" );
      }
   }


   public void findEveryPerson( Context ctx )
   {
      List<Person> accounts = this.personService.findAll();
      ctx.json( accounts );
   }
}
