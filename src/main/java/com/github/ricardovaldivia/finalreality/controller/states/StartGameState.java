package com.github.ricardovaldivia.finalreality.controller.states;

import com.github.ricardovaldivia.finalreality.controller.Controller;
/**
 * A class that holds all the information of a start game State
 * @author Ricardo Valdivia Orellana.
 */
public class StartGameState extends State{

  public StartGameState(Controller controller) {
    super(controller);
  }


  @Override
  public boolean isStartGame() {
    return true;
  }

  @Override
  public void onTurn() {
    controller.setState(new OnTurnState(controller));
  }
}
