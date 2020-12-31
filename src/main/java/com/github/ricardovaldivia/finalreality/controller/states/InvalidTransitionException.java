package com.github.ricardovaldivia.finalreality.controller.states;

public class InvalidTransitionException extends Exception{
  public InvalidTransitionException(String message){
    super(message);
  }
}
