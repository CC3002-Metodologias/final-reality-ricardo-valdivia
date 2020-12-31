package com.github.ricardovaldivia.finalreality.model.weapon;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.BlackMage;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.Thief;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.WhiteMage;

import java.util.HashMap;
import java.util.Objects;

/**
 * A class that holds all the information of a Staff. That it have magic damage.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class Staff extends AbstractWeapon{
    private final int magicDamage;

    /**
     * Creates a weapon with a name, a physical damage, speed and his magic damage.
     *
     */
    public Staff(final String name, final int physicalDamage, final int weight, final int weaponMagicDamage) {
        super(name, physicalDamage, weight);
        magicDamage = weaponMagicDamage;
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
        if (!(o instanceof Staff)) {
            return false;
        }
        final Staff weapon = (Staff) o;
        return getPhysicalDamage() == weapon.getPhysicalDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName()) &&
                getMagicDamage() == weapon.getMagicDamage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhysicalDamage(), getWeight(),getMagicDamage());
    }

    @Override
    public IWeapon equippedByWhiteMage(WhiteMage whiteMage) {
        return this;
    }
    @Override
    public IWeapon equippedByBlackMage(BlackMage blackMage) {
        return this;
    }
    @Override
    public IWeapon equippedByThief(Thief thief) {
        return this;
    }

    @Override
    public HashMap<String, String> getCurrentInfo() {
        var info = super.getCurrentInfo();
        info.put("MagicDamage", String.valueOf(this.getMagicDamage()));
        return info;
    }
}
