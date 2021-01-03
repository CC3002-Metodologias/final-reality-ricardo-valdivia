package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.Enemy;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest extends AbstractPlayerCharacterTest {
  private static final String THIEF_TEST = "Mundungus";
  private int maxHealth;
  private int defense;
  private Thief testThiefCharacter;


  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    var seed = new Random().nextInt();
    super.setUp(seed);
    maxHealth = r.nextInt(50);
    defense = r.nextInt(30);
    testThiefCharacter = new Thief(THIEF_TEST, turns, maxHealth, defense);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(500)
  void constructorTest() {
    checkConstruction(new Thief(THIEF_TEST, turns, maxHealth, defense),
        testThiefCharacter,
        new Thief("Umbrigde", turns, r.nextInt(50), r.nextInt(30)),
        new Enemy(THIEF_TEST, r.nextInt(15), turns, r.nextInt(30), r.nextInt(50), r.nextInt(20)),
        new Thief("Dublin", turns, r.nextInt(50), r.nextInt(30)),
        new Thief(THIEF_TEST, turns, r.nextInt(50) + 51, defense),
        new Thief(THIEF_TEST, turns, maxHealth, r.nextInt(30) + 31));
  }
  /**
   * Checks that the class' equip method works properly.
   */
  @RepeatedTest(500)
  void equipTest() {
    assertFalse(testThiefCharacter.isEquipped());
    testThiefCharacter.equip(testWeapons.get(0));
    assertFalse(testThiefCharacter.isEquipped());
    testThiefCharacter.equip(testWeapons.get(1));
    assertTrue(testThiefCharacter.isEquipped());
    testThiefCharacter.equip(testWeapons.get(2));
    assertFalse(testThiefCharacter.isEquipped());
    testThiefCharacter.equip(testWeapons.get(3));
    assertTrue(testThiefCharacter.isEquipped());
    testThiefCharacter.equip(testWeapons.get(4));
    assertTrue(testThiefCharacter.isEquipped());
  }

  /**
   * Checks that the class' attack method works properly.
   */
  @RepeatedTest(500)
  void attackByEngineerTest() {
    var playerCharacterTest = new Knight("Knight", turns, r.nextInt(60), r.nextInt(30));
    testThiefCharacter.attack(playerCharacterTest);
    testThiefCharacter.equip(testWeapons.get(4));
    testThiefCharacter.attack(playerCharacterTest);
    testThiefCharacter.setCurrentHealth(0);
    testThiefCharacter.setAlive(false);
    testThiefCharacter.attack(playerCharacterTest);
    testThiefCharacter.unEquip();
    assertNull(testThiefCharacter.getEquippedWeapon());
  }
}