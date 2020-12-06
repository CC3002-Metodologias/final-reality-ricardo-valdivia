package com.github.ricardovaldivia.finalreality.model.character;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;

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

  /**
   *
   * Returns this character's defense
   */
  int getDefense();

  /**
   * Returns this character's MaxHealth
   */
  int getMaxHealth();

  /**
   * Returns this character's CurrentHealth
   */
  int getCurrentHealth();

  void setCurrentHealth(int newHealth);

  void setAlive(boolean alive);


  void attack(ICharacter character);

  /**
   * Returns the current character's status
   */
  boolean isAlive();

  /**
   * Decrease this character's health an amount of damage
   */
  void attackBy(int damage);

  void attackByEnemy(Enemy enemy);

  void attackByBlackMage(BlackMage blackMage);

  void attackByWhiteMage(WhiteMage whiteMage);

  void attackByKnight(Knight knight);

  void attackByEngineer(Engineer engineer);

  void attackByThief(Thief thief);

}

