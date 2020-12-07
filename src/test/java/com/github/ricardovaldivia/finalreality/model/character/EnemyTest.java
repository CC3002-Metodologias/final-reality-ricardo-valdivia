package com.github.ricardovaldivia.finalreality.model.character;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.Thief;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private static Enemy testEnemy;

  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    basicSetUp();
    testEnemy = new Enemy(ENEMY_NAME, 10, turns, 30, 10, 5);
  }

  /**
   *  Checks that the Enemy construction work properly
   */
  public void checkEnemyConstruction(final ICharacter expectedCharacter,
                                   final ICharacter testEqualCharacter,
                                   final ICharacter sameClassDifferentCharacter,
                                   final ICharacter differentClassCharacter,
                                   final ICharacter sameClassDifferentName,
                                   final ICharacter sameClassDifferentHp,
                                   final ICharacter sameClassDifferentDefense,
                                   final ICharacter sameClassDifferentWeight,
                                   final ICharacter sameClassDifferentAttack) {
    super.checkConstruction(expectedCharacter,testEqualCharacter, sameClassDifferentCharacter, differentClassCharacter,
        sameClassDifferentName, sameClassDifferentHp, sameClassDifferentDefense);
    assertNotEquals(expectedCharacter, sameClassDifferentWeight);
    assertNotEquals(expectedCharacter, sameClassDifferentAttack);
  }

  /**
   *  Checks that the Enemy construction work properly for a variety of enemy's
   */
  @Test
  void constructorTest() {
    checkEnemyConstruction(new Enemy(ENEMY_NAME, 10, turns,30,10,5),
            testEnemy,
            new Enemy(ENEMY_NAME, 11, turns, 30, 10, 4),
            new Thief(ENEMY_NAME, turns, 30, 5),
            new Enemy("Dublin", 11, turns, 30, 10, 4),
            new Enemy(ENEMY_NAME, 10, turns, 20, 10, 5),
            new Enemy(ENEMY_NAME, 10, turns, 30, 9, 5),
            new Enemy(ENEMY_NAME, 13, turns, 30, 10, 5),
            new Enemy(ENEMY_NAME, 10, turns, 30, 10, 4));
  }
  /**
   * Checks that the enemy waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    testEnemy.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testEnemy, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks that the enemy attack work properly.
   */
  @Test
  void enemyAttackTest(){
    var playerCharacter = new Thief("Thief", turns, 6, 1);
    testEnemy.attack(playerCharacter);
    playerCharacter.setCurrentHealth(0);
    playerCharacter.setAlive(false);
    testEnemy.attack(playerCharacter);
  }
  
}