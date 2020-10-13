package com.github.ricardovaldivia.finalreality.model.character.player;

import com.github.ricardovaldivia.finalreality.model.character.AbstractCharacter;
import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.ricardovaldivia.finalreality.model.weapon.Weapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class PlayerCharacter extends AbstractCharacter {

  /**
   * Creates a new character.
   *
   * @param name
   * the character's name
   * @param turnsQueue
   * the queue with the characters waiting for their turn
   * @param characterClass
   * the class of this character
   */
  private Weapon equippedWeapon = null;

  public PlayerCharacter(@NotNull String name,
                         @NotNull BlockingQueue<ICharacter> turnsQueue,
                         final CharacterClass characterClass) {
    super(turnsQueue, name, characterClass);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCharacterClass());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlayerCharacter)) {
      return false;
    }
    final PlayerCharacter that = (PlayerCharacter) o;
    return getCharacterClass() == that.getCharacterClass()
            && getName().equals(that.getName());
  }

  /**
   * Set the weapon to a character.
   */
  public void equip(Weapon weapon) {
      this.equippedWeapon = weapon;
  }

  /**
   * Returns the weapon equipped of this character.
   */
  public Weapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Returns the CharacterClass of this character.
   */
  public CharacterClass getCharacterClass() {
    return characterClass;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
      scheduledExecutor
              .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    }
}
