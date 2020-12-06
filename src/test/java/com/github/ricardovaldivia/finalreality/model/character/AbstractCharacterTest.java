package com.github.ricardovaldivia.finalreality.model.character;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.Thief;
import org.junit.jupiter.api.Test;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 * @see ICharacter
 */
public abstract class AbstractCharacterTest{

  protected BlockingQueue<ICharacter> turns;

  /**
   *  Checks that the character construction work properly
   */
  public void checkConstruction(final ICharacter expectedCharacter,
      final ICharacter testEqualCharacter,
      final ICharacter sameClassDifferentCharacter,
      final ICharacter differentClassCharacter,
      final ICharacter sameClassDifferentName,
      final ICharacter sameClassDifferentHp,
      final ICharacter sameClassDifferentDefense) {
    assertEquals(expectedCharacter, expectedCharacter);
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
    assertNotEquals(expectedCharacter, sameClassDifferentName);
    assertNotEquals(expectedCharacter, sameClassDifferentHp);
    assertNotEquals(expectedCharacter, sameClassDifferentDefense);
  }

  /**
   * Set the basic Queue for the turns
   */
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
  }

  /**
   * Check if the Alive methods works properly
   */
  @Test
  void isNotAliveTest(){
    var character = new Thief("ThiefName", turns, 30, 10);
    assertTrue(character.isAlive());
    character.setCurrentHealth(0);
    character.setAlive(false);
    assertFalse(character.isAlive());
  }

}
