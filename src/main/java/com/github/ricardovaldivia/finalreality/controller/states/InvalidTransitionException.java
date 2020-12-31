package com.github.ricardovaldivia.finalreality.controller.states;

/**
 * A class that holds all the information for the exception
 * @author Ricardo Valdivia Orellana.
 */
public class InvalidTransitionException extends Exception{

  /**
   * Create the Exception with a message
   * @param message
   * the message that exception will throws.
   */
  public InvalidTransitionException(String message){
    super(message);
  }
}
