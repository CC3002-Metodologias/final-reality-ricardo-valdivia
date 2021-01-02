package com.github.ricardovaldivia.finalreality.controller.handlers;

import com.github.ricardovaldivia.finalreality.controller.Controller;

import java.beans.PropertyChangeEvent;

public class TurnsEmptyHandler implements IHandler {

  private final Controller controller;

  public TurnsEmptyHandler(final Controller controller) {
    this.controller = controller;
  }


  /**
   * This method gets called when a bound property is changed.
   *
   * @param evt
   *     A PropertyChangeEvent object describing the event source
   *     and the property that has changed.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (!controller.isEndGame() && !controller.isEndingWaitTurn()){
      controller.tryEndWaitTurn();
    }
  }
}
