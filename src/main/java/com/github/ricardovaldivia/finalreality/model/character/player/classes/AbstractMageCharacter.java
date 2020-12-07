package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the common information of a Mage of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public abstract class AbstractMageCharacter extends AbstractPlayerCharacter {

  private final int maxMana;
  private int currentMana;

  public AbstractMageCharacter(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, int maxHealth, int defense, int maxMana) {
    super(name, turnsQueue, maxHealth, defense);
    this.maxMana = maxMana;
    this.currentMana = maxMana;
  }
  /**
   * Returns the mana of this character.
   */
  public int getCurrentMana() {
    return currentMana;
  }
  /**
   * Returns the maxMana of this character.
   */
  public int getMaxMana(){return maxMana;}

  /**
   * Returns the current status of a black mage.
   */
  public HashMap<String, String> getCurrentInfo() {
    var info = super.getCurrentInfo();
    info.put("maxMana",String.valueOf(this.getMaxMana()));
    info.put("currentMana", String.valueOf(this.getCurrentMana()));
    return info;
  }
}
