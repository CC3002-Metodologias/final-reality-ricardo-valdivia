package com.github.ricardovaldivia.finalreality.model.weapon;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.Engineer;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.Knight;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.WhiteMage;

import java.util.Objects;

/**
 * A class that holds all the information of a Axe.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class Axe extends AbstractWeapon{
  /**
   * Creates a AXE with a name, a base damage, speed and it's type.
   *
   * @param name
   * name of the Axe
   * @param damage
   * amount of damage of thew Axe
   * @param weight
   * weight of the Axe
   */
  public Axe(String name, int damage, int weight) {
    super(name, damage, weight);
  }
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Axe)) {
      return false;
    }
    final Axe weapon = (Axe) o;
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
  public IWeapon equippedByEngineer(Engineer engineer) {
    return this;
  }
}

