package com.github.ricardovaldivia.finalreality.model.weapon;

public class MixWeapon extends Weapon {
    private final int magicDamage;

    public MixWeapon(final String name, final int phisicalDamage, final int weight, final int weaponMixDamage) {
        super(name, phisicalDamage, weight, WeaponType.BOW);
        magicDamage = weaponMixDamage;
    }

    private int getMagicDamage() {
        return magicDamage;
    }
}
