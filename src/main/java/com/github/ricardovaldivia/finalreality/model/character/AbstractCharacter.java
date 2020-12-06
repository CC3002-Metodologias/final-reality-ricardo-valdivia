package com.github.ricardovaldivia.finalreality.model.character;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public abstract class AbstractCharacter implements ICharacter {

  private final BlockingQueue<ICharacter> turnsQueue;
  private final String name;
  private final int defense;
  private int currentHealth;
  private final int maxHealth;
  private boolean alive;
  protected ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param maxHealth
   *     the character's maxHealth
   * @param defense
   *     the character's defense
   */

  public AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                           @NotNull String name, int maxHealth, int defense) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.alive = true;
    this.defense = defense;
    this.maxHealth = maxHealth;
    this.currentHealth = maxHealth;
  }

  /**
   * Adds this character to the turns queue.
   */
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }
  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getDefense() {
    return this.defense;
  }
  @Override
  public int getMaxHealth(){
    return this.maxHealth;
  }

  @Override
  public int getCurrentHealth() {
    return this.currentHealth;
  }
  @Override
  public void setCurrentHealth(int newHealth) {
    this.currentHealth = newHealth;
  }
  @Override
  public void setAlive(boolean alive) {
    this.alive = alive;
  }
  @Override
  public boolean isAlive(){
    return this.alive;
  }

  @Override
  public void attackBy( int damage){
    if (this.isAlive()) {
      int finalDamage = damage - this.getDefense();
      if (this.getCurrentHealth() - finalDamage < 0) {
        this.setAlive(false);
        this.setCurrentHealth(0);
      } else {
        this.setCurrentHealth(this.getCurrentHealth() - finalDamage);
      }
    }
  }

  @Override
  public void attackByEnemy(Enemy enemy){
    this.attackBy(enemy.getAttack());
  }

  @Override
  public void attackByBlackMage(BlackMage blackMage) {
    if (blackMage.isEquipped()){
      this.attackBy(blackMage.getEquippedWeapon().getPhysicalDamage());
    }
    else{
        this.attackBy(0);
    }
  }

  @Override
  public void attackByWhiteMage(WhiteMage whiteMage) {
    if (whiteMage.isEquipped()){
      this.attackBy(whiteMage.getEquippedWeapon().getPhysicalDamage());
    }
    else{
      this.attackBy(0);
    }
  }

  @Override
  public void attackByKnight(Knight knight) {
    if (knight.isEquipped()){
      this.attackBy(knight.getEquippedWeapon().getPhysicalDamage());
    }
    else{
      this.attackBy(0);
    }
  }

  @Override
  public void attackByEngineer(Engineer engineer) {
    if (engineer.isEquipped()){
      this.attackBy(engineer.getEquippedWeapon().getPhysicalDamage());
    }
    else{
      this.attackBy(0);
    }
  }

  @Override
  public void attackByThief(Thief thief) {
    if (thief.isEquipped()){
      this.attackBy(thief.getEquippedWeapon().getPhysicalDamage());
    }
    else{
      this.attackBy(0);
    }
  }
}
