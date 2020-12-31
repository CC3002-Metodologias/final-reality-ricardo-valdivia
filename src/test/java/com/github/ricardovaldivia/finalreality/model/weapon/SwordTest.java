package com.github.ricardovaldivia.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;


class SwordTest extends AbstractWeaponTest{
  private static final String SWORD_NAME = "Sword";
  private int weight;
  private int physicalDamage;
  private Sword testSwordWeapon;

  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    var seed =  new Random().nextInt();
    super.basicSetUp(seed);
    weight = r.nextInt(30);
    physicalDamage = r.nextInt(50);
    testSwordWeapon = new Sword(SWORD_NAME, physicalDamage, weight);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(1000)
  void constructorTest(){
    checkConstruction(new Sword(SWORD_NAME, physicalDamage, weight),
        testSwordWeapon,
        new Sword("Sword2", r.nextInt(50), r.nextInt(30)),
        new Knife("Knife", r.nextInt(50), r.nextInt(30)),
        new Sword("Sword3", physicalDamage, weight),
        new Sword(SWORD_NAME, r.nextInt(50) + 51, weight),
        new Sword(SWORD_NAME, physicalDamage, r.nextInt(30) + 31));
  }

  /**
   * Checks the getCurrentInfo method.
   */
  @RepeatedTest(60)
  void getInfoTest(){
    checkGetInfo(SWORD_NAME, physicalDamage, weight,testSwordWeapon);
  }

}