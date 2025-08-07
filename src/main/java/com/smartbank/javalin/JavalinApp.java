package com.smartbank.javalin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

public final class JavalinApp
{

   private static Javalin javalin;

   private JavalinApp( )
   {
   }


   public static Javalin get()
   {
      if ( javalin == null )
      {
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.registerModule( new JavaTimeModule() );

         javalin = Javalin.create( config ->
         {
            config.jsonMapper( new JavalinJackson( objectMapper ) );
         } ).start( 7070 );
         javalin.get( "/", ctx -> ctx.result( "Hello from Javalin!" ) );
      }
      return javalin;
   }
}
