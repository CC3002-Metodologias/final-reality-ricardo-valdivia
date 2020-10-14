package com.github.ricardovaldivia.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MixWeaponTest {
    private static final String STAFF_NAME = "Test STAFF";
    private static final int PHYSICAL_DAMAGE = 15;
    private static final int SPEED = 10;
    private static final int MAGICAL_DAMAGE = 15;
    private static final String KNIFE_NAME = "Test Knife";
    private MixWeapon testSTAFF;
    private Weapon testKnife;


    @BeforeEach
    void setUp() {
        testSTAFF = new MixWeapon(STAFF_NAME, PHYSICAL_DAMAGE, SPEED, MAGICAL_DAMAGE);
        testKnife = new Weapon(KNIFE_NAME, PHYSICAL_DAMAGE, SPEED, WeaponType.KNIFE);
    }

    @Test
    void constructorTest() {
        var expectedSTAFF = new MixWeapon(STAFF_NAME, PHYSICAL_DAMAGE, SPEED, MAGICAL_DAMAGE);
        var OtherSTAFFName = new MixWeapon(KNIFE_NAME, PHYSICAL_DAMAGE, SPEED, MAGICAL_DAMAGE);
        var OtherSTAFFPhysicalDamage = new MixWeapon(STAFF_NAME, PHYSICAL_DAMAGE+10, SPEED, MAGICAL_DAMAGE);
        var OtherSTAFFMagicDamage = new MixWeapon(STAFF_NAME, PHYSICAL_DAMAGE, SPEED, MAGICAL_DAMAGE+10);
        var OtherSTAFFSpeed = new MixWeapon(STAFF_NAME, PHYSICAL_DAMAGE, SPEED+10, MAGICAL_DAMAGE);

        assertEquals(expectedSTAFF, testSTAFF);
        assertEquals(testSTAFF, testSTAFF);
        assertNotEquals(expectedSTAFF, testKnife);
        assertEquals(expectedSTAFF.hashCode(), testSTAFF.hashCode());
        assertNotEquals(expectedSTAFF,OtherSTAFFName);
        assertNotEquals(expectedSTAFF,OtherSTAFFPhysicalDamage);
        assertNotEquals(expectedSTAFF,OtherSTAFFMagicDamage);
        assertNotEquals(expectedSTAFF,OtherSTAFFSpeed);

    }
}