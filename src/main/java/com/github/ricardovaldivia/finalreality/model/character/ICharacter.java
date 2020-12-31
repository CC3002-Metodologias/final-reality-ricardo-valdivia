package com.github.ricardovaldivia.finalreality.model.character;

import com.github.ricardovaldivia.finalreality.controller.handlers.IHandler;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
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

  /**
   * Set the current character health.
   */
  void setCurrentHealth(int newHealth);

  /**
   * Set the current character status.
   */
  void setAlive(boolean alive);

  /**
   * Generates the an attack.
   */
  void attack(ICharacter character);

  /**
   * Returns the current character's status
   */
  boolean isAlive();

  /**
   * Decrease this character's health an amount of damage
   */
  void attackBy(int damage);

  /**
   * Generates the attack by an Enemy.
   */
  void attackByEnemy(Enemy enemy);

  /**
   * Generates the attack by a BlackMage.
   */
  void attackByBlackMage(BlackMage blackMage);

  /**
   * Generates the attack by a WhiteMage.
   */
  void attackByWhiteMage(WhiteMage whiteMage);

  /**
   * Generates the attack by a Knight.
   */
  void attackByKnight(Knight knight);

  /**
   * Generates the attack by a Engineer.
   */
  void attackByEngineer(Engineer engineer);

  /**
   * Generates the attack by a Thief.
   */
  void attackByThief(Thief thief);


  /**
   * Returns the current status of the character.
   */
  HashMap<String, String> getCurrentInfo();

  /**
   * Adds a turnsListener to this character, to handle the end of the turn.
   */
  void addTurnsListener(IHandler endTurnHandler);

  /**
   * Adds a notEmptyListener to this character, to handle an empty queue.
   */
  void addNotEmptyListener(IHandler turnsEmptyHandler);
}

