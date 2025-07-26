package com.smartbank.person;

import java.util.List;

import com.smartbank.commoninterface.RestControllerInterface;

import io.javalin.Javalin;

public class PersonRestController implements RestControllerInterface
{
   private final PersonService personService;

   public PersonRestController( final PersonService personService )
   {
      this.personService = personService;
   }


   @Override
   public void startController( final Javalin javalin )
   {
      javalin.get( "/persons", ctx ->
      {
         List<Person> accounts = this.personService.findAll();
         ctx.json( accounts );
      } );

      javalin.get( "/person/{id}", ctx ->
      {
         String id = ctx.pathParam( "id" );
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
      } );

      javalin.post( "/person", ctx ->
      {
         String firstName = ctx.queryParam( "firstName" );
         String lastName = ctx.queryParam( "lastName" );
         String birthday = ctx.queryParam( "birthday" ); //yyyy-MM-dd

         final Person newPerson = this.personService.createNewPerson( firstName, lastName, birthday );

         ctx.json( newPerson );
      } );
   }
}
