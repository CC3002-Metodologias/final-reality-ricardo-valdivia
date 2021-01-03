package com.github.ricardovaldivia.finalreality.controller.states;

import com.github.ricardovaldivia.finalreality.controller.Controller;


/**
 * A class that holds all the information of a EndingWaitTurn State
 * @author Ricardo Valdivia Orellana.
 */
public class EndingWaitTurnState extends State{

  public EndingWaitTurnState(Controller controller) {
    super(controller);
  }

  @Override
  public void onTurn() {
    controller.setState(new OnTurnState(controller));
  }

  @Override
  public boolean isEndWaitState() {
    return true;
  }
}
