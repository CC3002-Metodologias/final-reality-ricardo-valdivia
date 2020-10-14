package com.github.ricardovaldivia.finalreality.model.character;

import com.github.ricardovaldivia.finalreality.model.character.player.CharacterClass;
import com.github.ricardovaldivia.finalreality.model.weapon.Weapon;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  CharacterClass getCharacterClass();

  void equip(Weapon testWeapon);

  Weapon getEquippedWeapon();
}
