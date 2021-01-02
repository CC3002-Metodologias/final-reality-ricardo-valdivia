package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BlackMageTest extends AbstractPlayerCharacterTest {
  private static final String BLACK_MAGE_NAME = "Vivi";
  private int maxHealth;
  private int defense;
  private int maxMana;
  private BlackMage testBlackCharacter;

  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    var seed =  new Random().nextInt();
    super.setUp(seed);
    maxHealth = r.nextInt(50);
    defense = r.nextInt(30);
    maxMana = r.nextInt(50);
    testBlackCharacter = new BlackMage(BLACK_MAGE_NAME, turns, maxHealth, defense, maxMana);
  }

  /**
   * Checks that the BlackMage constructor and equals method works properly.
   */
  public void checkBlackMageConstruction(ICharacter expectedCharacter,
                                   ICharacter testEqualCharacter,
                                   ICharacter sameClassDifferentCharacter,
                                   ICharacter differentClassCharacter,
                                   ICharacter sameClassDifferentName,
                                   ICharacter sameClassDifferentHp,
                                   ICharacter sameClassDifferentDefense,
                                   ICharacter sameClassDifferentMana) {
    super.checkConstruction(expectedCharacter, testEqualCharacter, sameClassDifferentCharacter,
        differentClassCharacter, sameClassDifferentName, sameClassDifferentHp, sameClassDifferentDefense);
    assertNotEquals(expectedCharacter, sameClassDifferentMana);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(1000)
  void constructorTest(){
    checkBlackMageConstruction(new BlackMage(BLACK_MAGE_NAME, turns, maxHealth, defense, maxMana),
        testBlackCharacter,
        new BlackMage("Severus", turns, r.nextInt(50), r.nextInt(30), r.nextInt(50)),
        new WhiteMage(BLACK_MAGE_NAME, turns, r.nextInt(50), r.nextInt(30), r.nextInt(50)),
        new BlackMage("Dublin", turns, r.nextInt(50), r.nextInt(30), r.nextInt(50)),
        new BlackMage(BLACK_MAGE_NAME, turns, r.nextInt(50)+51, defense, maxMana),
        new BlackMage(BLACK_MAGE_NAME, turns, maxHealth, r.nextInt(30)+31, maxMana),
        new BlackMage(BLACK_MAGE_NAME, turns, maxHealth, defense, r.nextInt(50)+51));
  }

  /**
   * Checks that the class' equip method works properly.
   */
  @RepeatedTest(1000)
  void equipTest(){
    assertFalse(testBlackCharacter.isEquipped());
    testBlackCharacter.equip(testWeapons.get(0));
    assertFalse(testBlackCharacter.isEquipped());
    testBlackCharacter.equip(testWeapons.get(1));
    assertFalse(testBlackCharacter.isEquipped());
    testBlackCharacter.equip(testWeapons.get(2));
    assertTrue(testBlackCharacter.isEquipped());
    testBlackCharacter.equip(testWeapons.get(3));
    assertTrue(testBlackCharacter.isEquipped());
    testBlackCharacter.equip(testWeapons.get(4));
    assertFalse(testBlackCharacter.isEquipped());
  }

  /**
   * Checks that the class' attack method works properly.
   */
  @RepeatedTest(1000)
  void attackByBlackMageTest(){
    var playerCharacterTest = new Thief("Thief", turns, r.nextInt(60), r.nextInt(30));
    testBlackCharacter.attack(playerCharacterTest);
    testBlackCharacter.equip(testWeapons.get(3));
    testBlackCharacter.attack(playerCharacterTest);
    testBlackCharacter.setCurrentHealth(0);
    testBlackCharacter.setAlive(false);
    testBlackCharacter.attack(playerCharacterTest);
    testBlackCharacter.unEquip();
    assertNull(testBlackCharacter.getEquippedWeapon());
  }

  /**
   * Checks that the class' getCurrentMana and getMaxMana methods works properly.
   */
  @Test
  void getManaTest(){
    var blackMageTest = new BlackMage(BLACK_MAGE_NAME, turns, 50, 30, 50);
    assertEquals(50,blackMageTest.getCurrentMana());
    assertEquals(50,blackMageTest.getMaxMana());
  }
}