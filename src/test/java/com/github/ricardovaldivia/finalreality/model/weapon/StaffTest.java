package com.github.ricardovaldivia.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest extends AbstractWeaponTest{
  private static final String STAFF_NAME = "Staff";
  private int weight;
  private int physicalDamage;
  private Staff testStaffWeapon;
  private int magicDamage;

  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    var seed =  new Random().nextInt();
    super.basicSetUp(seed);
    weight = r.nextInt(30);
    physicalDamage = r.nextInt(50);
    magicDamage = r.nextInt(50);
    testStaffWeapon = new Staff(STAFF_NAME, physicalDamage, weight,magicDamage);
  }

  /**
   * Checks that the Staff constructor and equals method works properly.
   */
  public void checkStaffConstruction(final IWeapon expectedWeapon,
                                final IWeapon testEqualWeapon,
                                final IWeapon sameClassDifferentWeapon,
                                final IWeapon differentClassWeapon,
                                final IWeapon sameClassDifferentName,
                                final IWeapon sameClassDifferentAttack,
                                final IWeapon sameClassDifferentWeight,
                                final IWeapon sameClassDifferentMagic) {
    super.checkConstruction(expectedWeapon, testEqualWeapon, sameClassDifferentWeapon, differentClassWeapon,
        sameClassDifferentName, sameClassDifferentAttack,sameClassDifferentWeight);
    assertNotEquals(expectedWeapon, sameClassDifferentMagic);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(500)
  void constructorTest(){
    checkStaffConstruction(new Staff(STAFF_NAME, physicalDamage, weight, magicDamage),
        testStaffWeapon,
        new Staff("Sword2", r.nextInt(50), r.nextInt(30),magicDamage),
        new Knife("Knife", r.nextInt(50), r.nextInt(30)),
        new Staff("Sword3", physicalDamage, weight,magicDamage),
        new Staff(STAFF_NAME, r.nextInt(50) + 51, weight, magicDamage),
        new Staff(STAFF_NAME, physicalDamage, r.nextInt(30) + 31, magicDamage),
        new Staff(STAFF_NAME, physicalDamage, weight, r.nextInt(50) + 51));
  }

  /**
   * Checks the getCurrentInfo method.
   */
  @RepeatedTest(60)
  void getInfoTest(){
    checkGetInfo(STAFF_NAME, physicalDamage, weight,testStaffWeapon);
    assertEquals(String.valueOf(magicDamage),testStaffWeapon.getCurrentInfo().get("MagicDamage"));
  }


}