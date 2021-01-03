package com.github.ricardovaldivia.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;


class KnifeTest extends AbstractWeaponTest{
  private static final String KNIFE_NAME = "Knife";
  private int weight;
  private int physicalDamage;
  private Knife testKnifeWeapon;

  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    var seed =  new Random().nextInt();
    super.basicSetUp(seed);
    weight = r.nextInt(30);
    physicalDamage = r.nextInt(50);
    testKnifeWeapon = new Knife(KNIFE_NAME, physicalDamage, weight);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(500)
  void constructorTest(){
    checkConstruction(new Knife(KNIFE_NAME, physicalDamage, weight),
        testKnifeWeapon,
        new Knife("Knife2", r.nextInt(50), r.nextInt(30)),
        new Bow("BOW", r.nextInt(50), r.nextInt(30)),
        new Knife("Knife3", physicalDamage, weight),
        new Knife(KNIFE_NAME, r.nextInt(50) + 51, weight),
        new Knife(KNIFE_NAME, physicalDamage, r.nextInt(30) + 31));
  }

  /**
   * Checks the getCurrentInfo method.
   */
  @RepeatedTest(60)
  void getInfoTest(){
    checkGetInfo(KNIFE_NAME, physicalDamage, weight, testKnifeWeapon);
  }

}