package com.github.ricardovaldivia.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class Weapon {

  private final String name;
  private final int physicalDamage;
  private final int weight;
  private final WeaponType type;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   * @see WeaponType
   */
  public Weapon(final String name, final int damage, final int weight,
      final WeaponType type) {
    this.name = name;
    this.physicalDamage = damage;
    this.weight = weight;
    this.type = type;
  }

  /**
   * Returns the name of this weapon.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the physical damage of this weapon.
   */
  public int getPhysicalDamage() {
    return physicalDamage;
  }

  /**
   * Returns the weigh of this weapon.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Returns the type of this weapon.
   */
  public WeaponType getType() {
    return type;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Weapon)) {
      return false;
    }
    final Weapon weapon = (Weapon) o;
    return getPhysicalDamage() == weapon.getPhysicalDamage() &&
        getWeight() == weapon.getWeight() &&
        getName().equals(weapon.getName()) &&
        getType() == weapon.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPhysicalDamage(), getWeight(), getType());
  }
}
