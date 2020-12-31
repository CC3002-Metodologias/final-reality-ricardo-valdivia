package com.github.ricardovaldivia.finalreality.controller.states;

import com.github.ricardovaldivia.finalreality.controller.Controller;

/**
 * A class that holds all the information of a State
 * @author Ricardo Valdivia Orellana.
 */
public class State {
  protected Controller controller;


  public State(Controller controller){
    this.controller = controller;
  }

  /**
   * returns if the state is marked.
   */
  public boolean isEndGame(){ return false;}

  /**
   * returns if the state is on turn.
   */
  public boolean isOnTurn(){ return false;}

  /**
   * returns if the state is wait turn.
   */
  public boolean isWaitTurn(){ return false;}

  /**
   * returns if the state is start game.
   */
  public boolean isStartGame(){return false;}

  /**
   * returns if the state is select attack target.
   */
  public boolean isSelectAttackTarget(){return false;}

  /**
   * returns if the state is end turn.
   */
  public boolean isEndTurn(){return false;}

  /**
   * Set the state as on turn. If not possible throws an exception.
   */
  public void onTurn()throws InvalidTransitionException{
    throw new InvalidTransitionException("Can't change to OnTurnState");
  }

  /**
   * Set the state as select attack target. If not possible throws an exception.
   */
  public void selectAttackTarget()throws InvalidTransitionException{
    throw new InvalidTransitionException("Can't change to SelectAttackTargetState");
  }

  /**
   * Set the state as end turn. If not possible throws an exception.
   */
  public void endTurn()throws InvalidTransitionException{
    throw new InvalidTransitionException("Can't change to EndTurnState");
  }

  /**
   * Set the state as wait turn. If not possible throws an exception.
   */
  public void waitTurn()throws InvalidTransitionException{
    throw new InvalidTransitionException("Can't change to WaitTurnState");
  }

  /**
   * Set the stat as end game. If not possible throws an exception.
   */
  public void endGame()throws InvalidTransitionException{
    throw new InvalidTransitionException("Can't change to EndGameState");
  }


}
