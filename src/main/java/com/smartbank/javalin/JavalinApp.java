package com.smartbank.javalin;

import io.javalin.Javalin;

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
         javalin = Javalin.create().start( 7070 );
         javalin.get( "/", ctx -> ctx.result( "Hello from Javalin!" ) );
      }
      return javalin;
   }
}
