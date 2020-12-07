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
public class BlackMage extends AbstractMageCharacter {
  /**
   * Creates a new Black Mage.
   *
   * @param name       the character's name
   * @param turnsQueue
   * @param maxMana  Max amount of mana of the WhiteMage
   */
  
  public BlackMage(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, int maxHealth, int defense, int maxMana) {
    super(name, turnsQueue, maxHealth, defense, maxMana);
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
}
