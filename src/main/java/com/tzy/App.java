package com.tzy;

/**
 * Hello world!
 *
 */


public class App    //Singleton
{

     private static App instance;

     private App(){

     }

     public static App getInstance(){
         if(instance == null){
             instance = new App();
         }
         return instance;
     }


    public static void main( String[] args )
    {






        System.out.println( "Hello World!" );
    }
}
