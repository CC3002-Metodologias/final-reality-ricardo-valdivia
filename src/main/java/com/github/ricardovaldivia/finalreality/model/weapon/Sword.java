package com.github.ricardovaldivia.finalreality.model.weapon;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.Knight;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.Thief;

import java.util.Objects;

/**
 * A class that holds all the information of a Sword.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class Sword extends AbstractWeapon{

  /**
   * Creates a SWORD with a name, a base damage, speed and it's type.
   *
   * @param name
   * name of the sword
   * @param damage
   * amount of damage of thew sword
   * @param weight
   * weight of the sword
   */
  public Sword(String name, int damage, int weight) {
    super(name, damage, weight);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Sword)) {
      return false;
    }
    final Sword weapon = (Sword) o;
    return getPhysicalDamage() == weapon.getPhysicalDamage() &&
        getWeight() == weapon.getWeight() &&
        getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPhysicalDamage(), getWeight());
  }

  @Override
  public IWeapon equippedByKnight(Knight knight) {
    return this;
  }

  @Override
  public IWeapon equippedByThief(Thief thief) {
    return this;
  }
}
