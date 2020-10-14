package com.github.ricardovaldivia.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a MixWeapon. That it have magic damage.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class MixWeapon extends Weapon {
    private final int magicDamage;

    /**
     * Creates a weapon with a name, a physical damage, speed and his magic damage.
     *
     * @see WeaponType
     */
    public MixWeapon(final String name, final int physicalDamage, final int weight, final int weaponMixDamage) {
        super(name, physicalDamage, weight, WeaponType.STAFF);
        magicDamage = weaponMixDamage;
    }
    /**
     * Returns the magic damage of this weapon.
     */
    public int getMagicDamage() {
        return magicDamage;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MixWeapon)) {
            return false;
        }
        final MixWeapon weapon = (MixWeapon) o;
        return getPhysicalDamage() == weapon.getPhysicalDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName()) &&
                getMagicDamage() == weapon.getMagicDamage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(),getMagicDamage());
    }
}
