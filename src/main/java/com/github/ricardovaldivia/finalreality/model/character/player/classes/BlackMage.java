package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.IPlayerCharacter;
import com.github.ricardovaldivia.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single Black Mage of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class BlackMage extends AbstractPlayerCharacter {
  /**
   * Creates a new Black Mage.
   *
   * @param name       the character's name
   * @param turnsQueue
   * @param maxMana  Max amount of mana of the WhiteMage
   */
  private final int maxMana;
  private int currentMana;

  public BlackMage(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, int maxHealth, int defense, int maxMana) {
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


  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaxMana(), getMaxHealth(), getDefense());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BlackMage)) {
      return false;
    }
    final BlackMage that = (BlackMage) o;
    return getName().equals(that.getName()) &&
        getDefense() == that.getDefense() &&
        getMaxHealth() == that.getMaxHealth() &&
        getMaxMana() == that.getMaxMana();
  }

  @Override
  public void equip(IWeapon weapon) {
    super.equip(weapon.equippedByBlackMage(this));
  }

  @Override
  public void attack(ICharacter character) {
    if (this.isAlive()) {
      character.attackByBlackMage(this);
    }
  }

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
