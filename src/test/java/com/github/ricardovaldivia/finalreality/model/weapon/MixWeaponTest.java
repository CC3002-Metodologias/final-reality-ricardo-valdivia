package com.github.ricardovaldivia.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MixWeaponTest {
    private static final String BOW_NAME = "Test Bow";
    private static final int PHYSICAL_DAMAGE = 15;
    private static final int SPEED = 10;
    private static final int MAGICAL_DAMAGE = 15;
    private static final String KNIFE_NAME = "Test Knife";
    private MixWeapon testBow;
    private Weapon testKnife;


    @BeforeEach
    void setUp() {
        testBow = new MixWeapon(BOW_NAME, PHYSICAL_DAMAGE, SPEED, MAGICAL_DAMAGE);
        testKnife = new Weapon(KNIFE_NAME, PHYSICAL_DAMAGE, SPEED, WeaponType.KNIFE);
    }

    @Test
    void constructorTest() {
        var expectedBow = new MixWeapon(BOW_NAME, PHYSICAL_DAMAGE, SPEED, MAGICAL_DAMAGE);
        assertEquals(expectedBow, testBow);
        assertEquals(testBow, testBow);
        assertNotEquals(expectedBow, testKnife);
        assertEquals(expectedBow.hashCode(), testBow.hashCode());

    }
}