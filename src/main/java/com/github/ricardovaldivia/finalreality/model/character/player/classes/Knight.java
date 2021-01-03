package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.ricardovaldivia.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a single Knight of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class Knight extends AbstractPlayerCharacter {
  /**
   * Creates a new Knight.
   *
   * @param name       the character's name
   * @param turnsQueue the queue with the characters waiting for their turn
   */
  public Knight(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, int maxHealth, int defense) {
    super(name, turnsQueue, maxHealth, defense);
  }
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaxHealth(), getDefense());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Knight)) {
      return false;
    }
    final Knight that = (Knight) o;
    return getName().equals(that.getName()) &&
        getDefense() == that.getDefense() &&
        getMaxHealth() == that.getMaxHealth();
  }

  @Override
  public void equip(IWeapon weapon) {
    super.equip(weapon.equippedByKnight(this));
  }

  @Override
  public void attack(ICharacter character) {
    if (this.isAlive()) {
      character.attackByKnight(this);
    }
  }

  @Override
  public HashMap<String, String> getCurrentInfo() {
    var info = super.getCurrentInfo();
    info.put("Character Class","Knight");
    return info;
  }

  @Override
  public String toString(){
    return super.toString() + ", "+ "Knight";
  }
}
