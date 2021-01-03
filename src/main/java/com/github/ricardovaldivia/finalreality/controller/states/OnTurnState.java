package com.github.ricardovaldivia.finalreality.controller.states;

import com.github.ricardovaldivia.finalreality.controller.Controller;

/**
 * A class that holds all the information of a on turn State
 * @author Ricardo Valdivia Orellana.
 */
public class OnTurnState extends State{
  public OnTurnState(Controller controller) {
    super(controller);
  }

  @Override
  public void selectAttackTarget() {
    controller.setState(new SelectAttackTargetState(controller));
  }

  @Override
  public boolean isOnTurn() {
    return true;
  }
}
