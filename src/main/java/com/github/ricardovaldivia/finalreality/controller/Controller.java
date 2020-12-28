package com.github.ricardovaldivia.finalreality.controller;

import com.github.ricardovaldivia.finalreality.model.character.Enemy;
import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.IPlayerCharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;
import com.github.ricardovaldivia.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller {
  /**
   * A class that holds the basic logic of the game.
   *
   * @author Ignacio Slater Muñoz.
   * @author Ricardo Valdivia Orellana.
   */
  private final ArrayList<IPlayerCharacter> playerParty =
      new ArrayList<>();
  private final ArrayList<Enemy> enemies =
      new ArrayList<>();
  private final ArrayList<IWeapon> inventory =
      new ArrayList<>();
  private final BlockingQueue<ICharacter> turns =
      new LinkedBlockingQueue<>();


  /**
   * Creates a black mage character and add it to the player's party.
   */
  public void createBlackMageCharacter(@NotNull String name,
                                       int maxHealth, int defensePoints,
                                       int maxMana) {
    playerParty.add(new BlackMage(name, turns, maxHealth, defensePoints, maxMana));
  }

  /**
   * Creates a white mage character and add it to the player's party.
   */
  public void createWhiteMageCharacter(@NotNull String name,
                                       int maxHealth, int defensePoints,
                                       int maxMana) {
    playerParty.add(new WhiteMage(name, turns, maxHealth, defensePoints, maxMana));
  }

  /**
   * Creates a engineer character and add it to the player's party.
   */
  public void createEngineerCharacter(@NotNull String name,
                                      int maxHealth, int defensePoints) {
    playerParty.add(new Engineer(name, turns, maxHealth, defensePoints));
  }

  /**
   * Creates a knight character and add it to the player's party.
   */
  public void createKnightCharacter(@NotNull String name,
                                    int maxHealth, int defensePoints) {
    playerParty.add(new Knight(name, turns, maxHealth, defensePoints));
  }

  /**
   * Creates a thief character and add it to the player's party.
   */
  public void createThiefCharacter(@NotNull String name,
                                   int maxHealth, int defensePoints) {
    playerParty.add(new Thief(name, turns, maxHealth, defensePoints));
  }

  /**
   * Creates an enemy character and add it to the enemies party.
   */
  public void createEnemyCharacter(@NotNull String name, int weight, int maxHealth, int defense,
                                   int attack) {
    enemies.add(new Enemy(name, weight, turns, maxHealth, defense, attack));
  }

  /**
   * Creates a axe weapon and add it to the player's inventory.
   */
  public void createAxeWeapon(@NotNull String name,
                              int physicalDamage, int weight) {
    inventory.add(new Axe(name, physicalDamage, weight));
  }

  /**
   * Creates a bow weapon and add it to the player's inventory.
   */
  public void createBowWeapon(@NotNull String name,
                              int physicalDamage, int weight) {
    inventory.add(new Bow(name, physicalDamage, weight));
  }

  /**
   * Creates a knife weapon and add it to the player's inventory.
   */
  public void createKnifeWeapon(@NotNull String name,
                                int physicalDamage, int weight) {
    inventory.add(new Knife(name, physicalDamage, weight));
  }

  /**
   * Creates a sword weapon and add it to the player's inventory.
   */
  public void createSwordWeapon(@NotNull String name,
                                int physicalDamage, int weight) {
    inventory.add(new Sword(name, physicalDamage, weight));
  }

  /**
   * Create a staff weapon and add it to the player's inventory.
   */
  public void createStaffWeapon(@NotNull String name,
                                int physicalDamage, int weight, int magicDamage) {
    inventory.add(new Staff(name, physicalDamage, weight, magicDamage));
  }

  /**
   * Returns the player's inventory.
   */
  public ArrayList<IWeapon> getInventory() {
    return inventory;
  }

  /**
   * Returns the player's party.
   */
  public ArrayList<IPlayerCharacter> getPlayerParty() {
    return playerParty;
  }


  /**
   *
   * Returns the current status information of a character.
   */
  public HashMap<String, String> getCurrentInfo(ICharacter character){
    return character.getCurrentInfo();
  }

  /**
   * Returns the enemies list.
   */
  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }

  /**
   * Tries to equip a weapon to a player character.
   */
  public void equip(IWeapon weapon, IPlayerCharacter playerCharacter) {
    playerCharacter.equip(weapon);
    if (playerCharacter.isEquipped()){
      inventory.remove(weapon);
    }
  }

  /**
   * Makes the attack from one character to another.
   */
  public void attack(ICharacter attackerCharacter, ICharacter attackedCharacter) {
    attackerCharacter.attack(attackedCharacter);
  }

  /**
   * Returns the queue.
   */
  public BlockingQueue<ICharacter> getTurns() {
    return turns;
  }

  /**
   * Returns the first character from the queue.
   */
  public ICharacter getFirstFromQueue() {
    return turns.peek();
  }

  /**
   * Removes the character from the queue.
   */
  public void removeFromQueue(ICharacter character) {
    turns.remove(character);
  }

  /**
   * Puts a character to wait for his turn.
   */
  public void waitTurn(ICharacter character) {
    character.waitTurn();
  }

  /**
   * Checks if the turns is empty.
   */
  public boolean isTurnsEmpty() {
    return turns.isEmpty();
  }

  /**
   * Waits until the turns list has at least one element.
   */
  public void waitTurns() {
    boolean already;
    do {
      already = !turns.isEmpty();
    } while (!already);
  }
}