package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WhiteMageTest extends AbstractPlayerCharacterTest {
  private static final String WHITE_MAGE_NAME = "Eiko";
  private int maxHealth;
  private int defense;
  private int maxMana;
  private WhiteMage testWhiteCharacter;

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
    testWhiteCharacter = new WhiteMage(WHITE_MAGE_NAME, turns, maxHealth, defense, maxMana);
  }

  /**
   * Checks that the WhiteMage constructor and equals method works properly.
   */
  public void checkWhiteMageConstruction(ICharacter expectedCharacter,
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
    checkWhiteMageConstruction(new WhiteMage(WHITE_MAGE_NAME, turns, maxHealth, defense, maxMana),
        testWhiteCharacter,
        new WhiteMage("Luna", turns, r.nextInt(50), r.nextInt(30), r.nextInt(50)),
        new BlackMage(WHITE_MAGE_NAME, turns, r.nextInt(50), r.nextInt(30), r.nextInt(50)),
        new WhiteMage("Dublin", turns, r.nextInt(50), r.nextInt(30), r.nextInt(50)),
        new WhiteMage(WHITE_MAGE_NAME, turns, r.nextInt(50) + 51, defense, maxMana),
        new WhiteMage(WHITE_MAGE_NAME, turns, maxHealth, r.nextInt(30) + 31, maxMana),
        new WhiteMage(WHITE_MAGE_NAME, turns, maxHealth, defense, r.nextInt(50) + 51));
  }

  /**
   * Checks that the class' equip method works properly.
   */
  @RepeatedTest(1000)
  void equipTest(){
    assertFalse(testWhiteCharacter.isEquipped());
    testWhiteCharacter.equip(testWeapons.get(0));
    assertFalse(testWhiteCharacter.isEquipped());
    testWhiteCharacter.equip(testWeapons.get(1));
    assertFalse(testWhiteCharacter.isEquipped());
    testWhiteCharacter.equip(testWeapons.get(2));
    assertFalse(testWhiteCharacter.isEquipped());
    testWhiteCharacter.equip(testWeapons.get(3));
    assertTrue(testWhiteCharacter.isEquipped());
    testWhiteCharacter.equip(testWeapons.get(4));
    assertFalse(testWhiteCharacter.isEquipped());
  }

  /**
   * Checks that the class' attack method works properly.
   */
  @RepeatedTest(1000)
  void attackByBlackMageTest(){
    var playerCharacterTest = new Thief("Thief", turns, r.nextInt(60), r.nextInt(30));
    testWhiteCharacter.attack(playerCharacterTest);
    testWhiteCharacter.equip(testWeapons.get(3));
    testWhiteCharacter.attack(playerCharacterTest);
    testWhiteCharacter.setCurrentHealth(0);
    testWhiteCharacter.setAlive(false);
    testWhiteCharacter.attack(playerCharacterTest);
    testWhiteCharacter.unEquip();
    assertNull(testWhiteCharacter.getEquippedWeapon());
  }

  /**
   * Checks that the class' getCurrentMana and getMaxMana methods works properly.
   */
  @Test
  void getManaTest(){
    var whiteMage = new WhiteMage(WHITE_MAGE_NAME, turns, 50, 30, 50);
    assertEquals(50,whiteMage.getCurrentMana());
    assertEquals(50,whiteMage.getMaxMana());
  }
}