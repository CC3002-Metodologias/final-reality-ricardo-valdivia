package com.github.ricardovaldivia.finalreality.controller.states;

import com.github.ricardovaldivia.finalreality.controller.Controller;
/**
 * A class that holds all the information of a select attack target State
 * @author Ricardo Valdivia Orellana.
 */
public class SelectAttackTargetState extends State{
  public SelectAttackTargetState(Controller controller) {
    super(controller);
  }

  @Override
  public boolean isSelectAttackTarget() {
    return true;
  }

  @Override
  public void endTurn() {
    if(controller.isEnemy(controller.getAttacker())){
      controller.setAttacked(controller.pickRandomPlayerCharacter(controller.getPlayerParty()));
    }
    controller.setState(new EndTurnState(controller));
  }
}
