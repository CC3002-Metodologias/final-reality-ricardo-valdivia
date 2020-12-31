package com.github.ricardovaldivia.finalreality.controller.states;

import com.github.ricardovaldivia.finalreality.controller.Controller;
/**
 * A class that holds all the information of a end game State
 * @author Ricardo Valdivia Orellana.
 */
public class EndGameState extends State{

  public EndGameState(Controller controller) {
    super(controller);
  }

  @Override
  public boolean isEndGame() {
    return true;
  }
}
