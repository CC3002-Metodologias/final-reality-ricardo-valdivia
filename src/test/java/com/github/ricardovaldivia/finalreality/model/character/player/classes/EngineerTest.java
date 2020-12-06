package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.Enemy;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EngineerTest extends AbstractPlayerCharacterTest {
  private static final String ENGINEER_NAME = "Cid";
  private int maxHealth;
  private int defense;
  private Engineer testEngineerCharacter;


  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    var seed = new Random().nextInt();
    super.setUp(seed);
    maxHealth = r.nextInt(50);
    defense = r.nextInt(30);
    testEngineerCharacter = new Engineer(ENGINEER_NAME, turns, maxHealth, defense);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(600)
  void constructorTest() {
    checkConstruction(new Engineer(ENGINEER_NAME, turns, maxHealth, defense),
        testEngineerCharacter,
        new Engineer("Dumbledore", turns, r.nextInt(50), r.nextInt(30)),
        new Enemy(ENGINEER_NAME, r.nextInt(15), turns, r.nextInt(30), r.nextInt(50), r.nextInt(20)),
        new Engineer("Dublin", turns, r.nextInt(50), r.nextInt(30)),
        new Engineer(ENGINEER_NAME, turns, r.nextInt(50)+51, defense),
        new Engineer(ENGINEER_NAME, turns, maxHealth, r.nextInt(30)+31));
  }

  /**
   * Checks that the class' equip method works properly.
   */
  @RepeatedTest(600)
  void equipTest(){
    assertFalse(testEngineerCharacter.isEquipped());
    testEngineerCharacter.equip(testWeapons.get(0));
    assertTrue(testEngineerCharacter.isEquipped());
    testEngineerCharacter.equip(testWeapons.get(1));
    assertTrue(testEngineerCharacter.isEquipped());
    testEngineerCharacter.equip(testWeapons.get(2));
    assertFalse(testEngineerCharacter.isEquipped());
    testEngineerCharacter.equip(testWeapons.get(3));
    assertFalse(testEngineerCharacter.isEquipped());
    testEngineerCharacter.equip(testWeapons.get(4));
    assertFalse(testEngineerCharacter.isEquipped());
  }

  /**
   * Checks that the class' attack method works properly.
   */
  @RepeatedTest(1000)
  void attackByEngineerTest(){
    var playerCharacterTest = new Thief("Thief", turns, r.nextInt(60), r.nextInt(30));
    testEngineerCharacter.attack(playerCharacterTest);
    testEngineerCharacter.equip(testWeapons.get(1));
    testEngineerCharacter.attack(playerCharacterTest);
    testEngineerCharacter.setCurrentHealth(0);
    testEngineerCharacter.setAlive(false);
    testEngineerCharacter.attack(playerCharacterTest);
  }
}