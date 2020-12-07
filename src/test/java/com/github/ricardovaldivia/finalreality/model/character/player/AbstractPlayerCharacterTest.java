package com.github.ricardovaldivia.finalreality.model.character.player;

import com.github.ricardovaldivia.finalreality.model.character.AbstractCharacterTest;

import java.util.*;


import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;
import com.github.ricardovaldivia.finalreality.model.weapon.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
   * Set of tests for the {@code AbstractPlayerCharacter} class.
   *
   * @author Ignacio Slater Muñoz.
   * @author Ricardo Valdivia Orellana.
   * @see IPlayerCharacter
 */
public class AbstractPlayerCharacterTest extends AbstractCharacterTest {

  protected List<IWeapon> testWeapons;
  protected Random r;
  Knight testCharacter;
  IWeapon testWeapon;

  /**
   * Setup method.
   *
   */

  protected void setUp(int seed) {
    super.basicSetUp();
    r = new Random(seed);
    testWeapons = new ArrayList<>();
    testWeapons.add(new Axe("Axe", r.nextInt(50),r.nextInt(30)));
    testWeapons.add(new Bow("Bow", r.nextInt(50),r.nextInt(30)));
    testWeapons.add(new Knife("Knife", r.nextInt(50),r.nextInt(30)));
    testWeapons.add(new Staff("Staff", r.nextInt(50),r.nextInt(30),r.nextInt(50)));
    testWeapons.add(new Sword("Sword", r.nextInt(50),r.nextInt(30)));
  }


  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {
    super.basicSetUp();
    testCharacter = new Knight("Adelbert",turns,30,5);
    testWeapon = new Knife("Knife", 50,15);
    Assertions.assertTrue(turns.isEmpty());
    testCharacter.equip(testWeapon);
    testCharacter.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testCharacter, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}