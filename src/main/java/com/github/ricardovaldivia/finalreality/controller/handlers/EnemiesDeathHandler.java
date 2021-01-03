package com.github.ricardovaldivia.finalreality.controller.handlers;


import com.github.ricardovaldivia.finalreality.controller.Controller;
import com.github.ricardovaldivia.finalreality.model.character.Enemy;
import com.github.ricardovaldivia.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * @author Ricardo Valdivia Orellana.
 */
public class EnemiesDeathHandler implements IHandler{
  private final Controller controller;

  public EnemiesDeathHandler(final Controller controller) {
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
    controller.removeFromEnemies((Enemy) evt.getNewValue());
  }
}
