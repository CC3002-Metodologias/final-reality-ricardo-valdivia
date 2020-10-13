package com.github.ricardovaldivia.finalreality.model.weapon;

import java.util.Objects;

public class MixWeapon extends Weapon {
    private final int magicDamage;

    /**
     * Creates a weapon with a name, a physical damage, speed and it's magic damage.
     *
     * @see WeaponType
     */
    public MixWeapon(final String name, final int phisicalDamage, final int weight, final int weaponMixDamage) {
        super(name, phisicalDamage, weight, WeaponType.BOW);
        magicDamage = weaponMixDamage;
    }

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
                getType() == weapon.getType() &&
                getMagicDamage() == weapon.getMagicDamage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(), getType(),getMagicDamage());
    }
}
