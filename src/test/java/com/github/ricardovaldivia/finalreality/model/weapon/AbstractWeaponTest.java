package com.github.ricardovaldivia.finalreality.model.weapon;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWeaponTest {

  protected Random r;

  public void checkConstruction(final IWeapon expectedWeapon,
                                final IWeapon testEqualWeapon,
                                final IWeapon sameClassDifferentWeapon,
                                final IWeapon differentClassWeapon,
                                final IWeapon sameClassDifferentName,
                                final IWeapon sameClassDifferentAttack,
                                final IWeapon sameClassDifferentWeight) {
    assertEquals(expectedWeapon, expectedWeapon);
    assertEquals(expectedWeapon, testEqualWeapon);
    assertNotEquals(sameClassDifferentWeapon, testEqualWeapon);
    assertNotEquals(testEqualWeapon, differentClassWeapon);
    assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
    assertNotEquals(expectedWeapon, sameClassDifferentName);
    assertNotEquals(expectedWeapon, sameClassDifferentAttack);
    assertNotEquals(expectedWeapon, sameClassDifferentWeight);
  }

  void basicSetUp(int seed){
    r = new Random(seed);
  }
}