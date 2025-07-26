package com.smartbank.commoninterface;

import io.javalin.Javalin;

public interface ModuleInterface
{

   void initializeModule( final Javalin app );
}
