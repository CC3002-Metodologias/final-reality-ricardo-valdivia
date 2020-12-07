package com.github.ricardovaldivia.finalreality.model.weapon;



import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of weapons.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 * @see IWeapon
 */
class AbstractWeaponTest {

  protected Random r;

  /**
   *  Checks that the weapon construction work properly
   */
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

  /**
   *  Set the basicSetUp, including a Random variable.
   */
  void basicSetUp(int seed){
    r = new Random(seed);
  }
}