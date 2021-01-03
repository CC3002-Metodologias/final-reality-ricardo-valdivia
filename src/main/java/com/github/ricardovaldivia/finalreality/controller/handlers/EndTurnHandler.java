package com.github.ricardovaldivia.finalreality.controller.handlers;

import com.github.ricardovaldivia.finalreality.controller.Controller;
import com.github.ricardovaldivia.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;


/**
 * @author Ricardo Valdivia Orellana.
 */
public class EndTurnHandler implements IHandler{
  private final Controller controller;

  public EndTurnHandler(final Controller controller) {
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
    controller.endTurn((ICharacter) evt.getNewValue());
  }
}
