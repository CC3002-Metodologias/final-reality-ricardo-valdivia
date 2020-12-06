package com.github.ricardovaldivia.finalreality.model.weapon;


import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;


public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int physicalDamage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.physicalDamage = damage;
    this.weight = weight;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getPhysicalDamage() {
    return physicalDamage;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public IWeapon equippedByBlackMage(BlackMage blackMage) {
    return null;
  }

  @Override
  public IWeapon equippedByEngineer(Engineer engineer) {
    return null;
  }

  @Override
  public IWeapon equippedByKnight(Knight knight) {
    return null;
  }

  @Override
  public IWeapon equippedByThief(Thief thief) {
    return null;
  }

  @Override
  public IWeapon equippedByWhiteMage(WhiteMage whiteMage) {
    return null;
  }
}
