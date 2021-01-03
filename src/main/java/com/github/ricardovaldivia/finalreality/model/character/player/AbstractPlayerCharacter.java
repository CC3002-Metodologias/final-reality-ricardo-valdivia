package com.github.ricardovaldivia.finalreality.model.character.player;

import com.github.ricardovaldivia.finalreality.controller.handlers.IHandler;
import com.github.ricardovaldivia.finalreality.model.character.AbstractCharacter;
import com.github.ricardovaldivia.finalreality.model.character.Enemy;
import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public abstract class AbstractPlayerCharacter extends AbstractCharacter implements
    IPlayerCharacter {

  protected IWeapon equippedWeapon = null;
  private final PropertyChangeSupport playerDieNotification = new PropertyChangeSupport(
      this);

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
  public AbstractPlayerCharacter(@NotNull String name,
                                 @NotNull BlockingQueue<ICharacter> turnsQueue, int maxHealth, int defense) {
    super(turnsQueue, name, maxHealth,defense);
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
        .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public void equip(IWeapon weapon) {
    this.equippedWeapon = weapon;
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  @Override
  public boolean isEquipped(){
    if(getEquippedWeapon() == null){
      return false;
    }
    return true;
  }

  @Override
  public void addPlayerListener(final IHandler playerDeathHandler) {
    playerDieNotification.addPropertyChangeListener(playerDeathHandler);
  }

  @Override
  public void setCurrentHealth(int newHealth) {
    super.setCurrentHealth(newHealth);
    if (newHealth <= 0){
      playerDieNotification.firePropertyChange("PLAYER_DEATH", null, this);
    }
  }

  @Override
  public HashMap<String, String> getCurrentInfo() {
   var info = super.getCurrentInfo();
   info.put("EquippedWeapon", String.valueOf(this.isEquipped()));
   return info;
  }

  @Override
  public void unEquip() {
    this.equippedWeapon = null;
  }

}
