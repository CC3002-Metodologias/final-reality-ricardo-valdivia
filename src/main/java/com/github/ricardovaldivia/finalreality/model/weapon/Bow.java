package com.github.ricardovaldivia.finalreality.model.weapon;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.Engineer;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.Thief;

import java.util.Objects;

public class Bow extends AbstractWeapon{
  /**
   * Creates a BOW with a name, a base damage, speed and it's type.
   *
   * @param name
   * name of the bow
   * @param damage
   * amount of damage of thew bow
   * @param weight
   * weight of the bow
   */
  public Bow(String name, int damage, int weight) {
    super(name, damage, weight);
  }
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Bow)) {
      return false;
    }
    final Bow weapon = (Bow) o;
    return getPhysicalDamage() == weapon.getPhysicalDamage() &&
        getWeight() == weapon.getWeight() &&
        getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPhysicalDamage(), getWeight());
  }

  @Override
  public IWeapon equippedByEngineer(Engineer engineer) {
    return this;
  }

  @Override
  public IWeapon equippedByThief(Thief thief) {
    return this;
  }
}
