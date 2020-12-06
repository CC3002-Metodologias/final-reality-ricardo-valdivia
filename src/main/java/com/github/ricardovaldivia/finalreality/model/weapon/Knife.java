package com.github.ricardovaldivia.finalreality.model.weapon;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.BlackMage;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.Knight;

import java.util.Objects;

public class Knife extends AbstractWeapon{

  /**
   * Creates a KNIFE with a name, a base damage, speed and it's type.
   *
   * @param name
   * name of the knife
   * @param damage
   * amount of damage of thew knife
   * @param weight
   * weight of the knife
   */
  public Knife(String name, int damage, int weight) {
    super(name, damage, weight);
  }
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Knife)) {
      return false;
    }
    final Knife weapon = (Knife) o;
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
  public IWeapon equippedByBlackMage(BlackMage blackMage) {
    return this;
  }
}
