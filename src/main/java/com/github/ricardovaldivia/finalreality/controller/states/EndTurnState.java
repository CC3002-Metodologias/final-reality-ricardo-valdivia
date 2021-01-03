package com.github.ricardovaldivia.finalreality.controller.states;

import com.github.ricardovaldivia.finalreality.controller.Controller;
/**
 * A class that holds all the information of a end turn State
 * @author Ricardo Valdivia Orellana.
 */
public class EndTurnState extends State{
  public EndTurnState(Controller controller) {
    super(controller);
  }

  @Override
  public boolean isEndTurn() {
    return true;
  }

  @Override
  public void onTurn() {
    controller.setState(new OnTurnState(controller));
  }

  @Override
  public void waitTurn() {
    controller.setState(new WaitTurnState(controller));
  }

  @Override
  public void endGame() {
    controller.setState(new EndGameState(controller));
  }
}
