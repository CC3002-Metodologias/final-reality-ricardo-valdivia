package com.github.ricardovaldivia.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BowTest extends AbstractWeaponTest{
  private static final String BOW_NAME = "Bow";
  private int weight;
  private int physicalDamage;
  private Bow testBowWeapon;

  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    var seed =  new Random().nextInt();
    super.basicSetUp(seed);
    weight = r.nextInt(30);
    physicalDamage = r.nextInt(50);
    testBowWeapon = new Bow(BOW_NAME, physicalDamage, weight);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(1000)
  void constructorTest(){
    checkConstruction(new Bow(BOW_NAME, physicalDamage, weight),
        testBowWeapon,
        new Bow("Bow2", r.nextInt(50), r.nextInt(30)),
        new Knife("Knife", r.nextInt(50), r.nextInt(30)),
        new Bow("Bow3", physicalDamage, weight),
        new Bow(BOW_NAME, r.nextInt(50) + 51, weight),
        new Bow(BOW_NAME, physicalDamage, r.nextInt(30) + 31));
  }

  /**
   * Checks the getCurrentInfo method.
   */
  @RepeatedTest(60)
  void getInfoTest(){
    checkGetInfo(BOW_NAME, physicalDamage, weight,testBowWeapon);
  }

}
