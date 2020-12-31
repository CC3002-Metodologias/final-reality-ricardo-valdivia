package com.github.ricardovaldivia.finalreality.controller.states;

import com.github.ricardovaldivia.finalreality.controller.Controller;
/**
 * A class that holds all the information of a wait turn State
 * @author Ricardo Valdivia Orellana.
 */
public class WaitTurnState extends State{

  public WaitTurnState(Controller controller) {
    super(controller);
  }

  @Override
  public boolean isWaitTurn() {
    return true;
  }

  @Override
  public void onTurn() {
    controller.setState(new OnTurnState(controller));
  }
}
