package com.github.ricardovaldivia.finalreality.controller.handlers;

import com.github.ricardovaldivia.finalreality.controller.Controller;
import com.github.ricardovaldivia.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;


/**
 * @author Ricardo Valdivia Orellana.
 */
public class PlayerDeathHandler implements IHandler{

  private final Controller controller;

  public PlayerDeathHandler(final Controller controller) {
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
    controller.removeFromParty((IPlayerCharacter) evt.getNewValue());
  }
}
