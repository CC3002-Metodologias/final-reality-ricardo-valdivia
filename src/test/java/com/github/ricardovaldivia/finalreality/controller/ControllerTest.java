package com.github.ricardovaldivia.finalreality.controller;


import com.github.ricardovaldivia.finalreality.controller.states.EndGameState;
import com.github.ricardovaldivia.finalreality.controller.states.EndTurnState;
import com.github.ricardovaldivia.finalreality.controller.states.WaitTurnState;
import com.github.ricardovaldivia.finalreality.model.character.Enemy;
import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.IPlayerCharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;
import com.github.ricardovaldivia.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the controller.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
class ControllerTest {
  private static Controller controller;
  private static int maxHealth;
  private static int maxMana;
  private static int defense;
  private static int attack;
  private static int enemyWeight;
  private static int weaponWeight;
  private static int physicalDamage;
  private static int magicDamage;
  private static final String BLACK_MAGE_NAME = "Vivi";
  private static final String ENGINEER_NAME = "Cid";
  private static final String KNIGHT_NAME = "Adelbert";
  private static final String THIEF_NAME = "Mundungus";
  private static final String WHITE_MAGE_NAME = "Eiko";
  private static final String ENEMY_NAME = "Goblin";
  private static final String AXE_NAME = "AXE";
  private static final String BOW_NAME = "BOW";
  private static final String SWORD_NAME = "SWORD";
  private static final String STAFF_NAME = "STAFF";
  private static final String KNIFE_NAME = "KNIFE";
  private static BlackMage testBlackMage;
  private static WhiteMage testWhiteMage;
  private static Engineer testEngineer;
  private static Thief testThief;
  private static Knight testKnight;
  private static Enemy testEnemy;
  private static Axe testAxe;
  private static Bow testBow;
  private static Sword testSword;
  private static Knife testKnife;
  private static Staff testStaff;
  private static BlockingQueue<ICharacter> turns;
  private static int seed =  new Random().nextInt();
  private static final Random r = new Random(seed);
  
  /**
   * Set the basic status with random values.
   */
  public void setStatus(){
    maxHealth = r.nextInt(50)+5;
    maxMana = r.nextInt(50)+1;
    defense = r.nextInt(30)+1;
    attack = r.nextInt(30)+20;
    enemyWeight = 2;
    physicalDamage = r.nextInt(50)+10;
    magicDamage = r.nextInt(50);
    weaponWeight = r.nextInt(30)+5;
  }

  /**
   * Using the setStatus, create the basics elements
   */
  @BeforeEach
  void basicSetUp(){
    controller = new Controller();
    turns = controller.getTurns();
    setStatus();
    seed = new Random().nextInt();
    testEnemy = new Enemy(ENEMY_NAME, enemyWeight, turns, maxHealth + 500, defense, attack);
    testEngineer = new Engineer(ENGINEER_NAME, turns, maxHealth, defense);
    testThief = new Thief(THIEF_NAME, turns, maxHealth, defense);
    testKnight = new Knight(KNIGHT_NAME, turns, maxHealth, defense);
    testBlackMage = new BlackMage(BLACK_MAGE_NAME, turns, maxHealth, defense, maxMana);
    testWhiteMage = new WhiteMage(WHITE_MAGE_NAME, turns, maxHealth, defense, maxMana);
    testAxe = new Axe(AXE_NAME, physicalDamage, weaponWeight);
    testBow = new Bow(BOW_NAME, physicalDamage, weaponWeight);
    testSword = new Sword(SWORD_NAME, physicalDamage, weaponWeight);
    testKnife = new Knife(KNIFE_NAME, physicalDamage, weaponWeight);
    testStaff = new Staff(STAFF_NAME, physicalDamage, weaponWeight, magicDamage);
  }


  /**
   * Creates item's using the controller function.
   */
  public void createItems(){
    controller.createEnemyCharacter(ENEMY_NAME, enemyWeight, maxHealth + 500, defense, attack);
    controller.createEngineerCharacter(ENGINEER_NAME, maxHealth, defense);
    controller.createThiefCharacter(THIEF_NAME, maxHealth, defense);
    controller.createKnightCharacter(KNIGHT_NAME, maxHealth, defense);
    controller.createBlackMageCharacter(BLACK_MAGE_NAME, maxHealth, defense, maxMana);
    controller.createWhiteMageCharacter(WHITE_MAGE_NAME, maxHealth, defense, maxMana);
    controller.createAxeWeapon(AXE_NAME, physicalDamage, weaponWeight);
    controller.createBowWeapon(BOW_NAME, physicalDamage, weaponWeight);
    controller.createSwordWeapon(SWORD_NAME, physicalDamage, weaponWeight);
    controller.createKnifeWeapon(KNIFE_NAME, physicalDamage, weaponWeight);
    controller.createStaffWeapon(STAFF_NAME, physicalDamage, weaponWeight, magicDamage);
  }

  /**
   * Check if the controller's functions create works properly
   */
  @RepeatedTest(1000)
  void checkConstructionTest(){
    createItems();
    var testEnemiesList = controller.getEnemies();
    assertTrue(testEnemiesList.contains(testEnemy));
    var testParty = controller.getPlayerParty();
    assertTrue(testParty.contains(testBlackMage));
    assertTrue(testParty.contains(testEngineer));
    assertTrue(testParty.contains(testKnight));
    assertTrue(testParty.contains(testThief));
    assertTrue(testParty.contains(testWhiteMage));
    var testInventory = controller.getInventory();
    assertTrue(testInventory.contains(testBow));
    assertTrue(testInventory.contains(testAxe));
    assertTrue(testInventory.contains(testKnife));
    assertTrue(testInventory.contains(testStaff));
    assertTrue(testInventory.contains(testSword));
  }

  /**
   * Check if the controller's function attack works properly
   */
  @RepeatedTest(5)
  void checkEquipAttackTest(){
    createItems();
    controller.putOnQueue();
    var testEnemiesList = new ArrayList<>(controller.getEnemies());
    var testParty = new ArrayList<>(controller.getPlayerParty());
    var testInventory = controller.getInventory();
    for (Enemy enemyTest : testEnemiesList){
      for(IPlayerCharacter playerCharacter : testParty){
        controller.setState(new EndTurnState(controller));
        controller.attack(enemyTest, playerCharacter);

      }
    }
    for(IPlayerCharacter playerCharacter : testParty){
      int index = 0;
      while(!playerCharacter.isEquipped()){
        controller.equip(testInventory.get(index), playerCharacter);
        index++;
      }
    }
    for (IPlayerCharacter playerCharacter : testParty) {
      for (Enemy enemyTest : testEnemiesList) {
        controller.setState(new EndTurnState(controller));
        controller.attack(playerCharacter, enemyTest);
      }
    }
    controller.createSwordWeapon(SWORD_NAME, physicalDamage, weaponWeight);
    controller.equip(testInventory.get(0),testParty.get(4));
  }
  /**
   * Check if the controller's function get info works properly
   */
  @RepeatedTest(1000)
  void checkGetInfoTest(){
    createItems();
    var enemyInfo = controller.getCurrentInfo(testEnemy);
    var blackMageInfo = controller.getCurrentInfo(testBlackMage);
    var knightInfo = controller.getCurrentInfo(testKnight);
    var staffInfo = controller.getCurrentInfo(testStaff);
    String maxHealthString = String.valueOf(maxHealth);
    String enemyMaxHealthString = String.valueOf(maxHealth + 500);
    String maxManaString = String.valueOf(maxMana);
    String defenseString = String.valueOf(defense);
    String attackString = String.valueOf(attack);
    String weightString = String.valueOf(enemyWeight);
    assertEquals(ENEMY_NAME, enemyInfo.get("Name"));
    assertEquals(enemyMaxHealthString, enemyInfo.get("maxHealth"));
    assertEquals(enemyMaxHealthString, enemyInfo.get("currentHealth"));
    assertEquals(defenseString, enemyInfo.get("defense"));
    assertEquals("true", enemyInfo.get("status"));
    assertEquals(attackString, enemyInfo.get("Attack"));
    assertEquals(weightString, enemyInfo.get("Weight"));
    assertEquals(KNIGHT_NAME, knightInfo.get("Name"));
    assertEquals(maxHealthString, knightInfo.get("maxHealth"));
    assertEquals(maxHealthString, knightInfo.get("currentHealth"));
    assertEquals(defenseString, knightInfo.get("defense"));
    assertEquals("true", knightInfo.get("status"));
    assertEquals("false", knightInfo.get("EquippedWeapon"));
    assertEquals(BLACK_MAGE_NAME, blackMageInfo.get("Name"));
    assertEquals(maxHealthString, blackMageInfo.get("maxHealth"));
    assertEquals(maxHealthString, blackMageInfo.get("currentHealth"));
    assertEquals(defenseString, blackMageInfo.get("defense"));
    assertEquals("true", blackMageInfo.get("status"));
    assertEquals("false", blackMageInfo.get("EquippedWeapon"));
    assertEquals(maxManaString, blackMageInfo.get("maxMana"));
    assertEquals(maxManaString, blackMageInfo.get("currentMana"));
    assertEquals(STAFF_NAME,staffInfo.get("Name"));
    assertEquals(String.valueOf(physicalDamage), staffInfo.get("PhysicalDamage"));
    assertEquals(String.valueOf(weaponWeight),staffInfo.get("Weight"));
    assertEquals(String.valueOf(magicDamage),staffInfo.get("MagicDamage"));

  }

  /**
   * Check if the Turns empty work properly
   */
  @RepeatedTest(5)
  void checkTurnsEmptyTest() {
    createItems();
    assertTrue(controller.isTurnsEmpty());
    controller.waitTurn(controller.getEnemies().get(0));
    try {
      Thread.sleep(1200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assertFalse(controller.isTurnsEmpty());
  }

  /**
   * Check if the wait turn work properly
   */
  @RepeatedTest(5)
  void checkWaitTurnTest() {
    createItems();
    controller.setState(new WaitTurnState(controller));
    controller.waitTurn(controller.getEnemies().get(0));
    try {
      Thread.sleep(1200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assertEquals(testEnemy,controller.getFirstFromQueue());
  }

  /**
   * Check if the remove from turns work properly
   */
  @RepeatedTest(5)
  void checkRemoveFromTurnsTest() {
    createItems();
    controller.putOnQueue();
    assertTrue(controller.getTurns().contains(controller.getEnemies().get(0)));
    controller.removeFromQueue(controller.getEnemies().get(0));
    assertFalse(controller.getTurns().contains(controller.getEnemies().get(0)));
  }

  /**
   * Check if the enemiesDeathHandler and the playerDeathHandler works properly.
   */
  @Test
  void listenerTest(){
    createItems();
    controller.putOnQueue();
    controller.createEnemyCharacter("Enemy2", 10, 100,10,200);
    var voldemort = new Enemy("Voldemort", 10, turns, 10000, 0, 10000);
    var party = new ArrayList<>(controller.getPlayerParty());
    controller.setState(new EndTurnState(controller));
    for(var character: party){
      controller.attack(voldemort, character);
      controller.setState(new EndTurnState(controller));
    }
    assertTrue(controller.getPlayerParty().isEmpty());
    controller.setState(new EndTurnState(controller));
    controller.attack(voldemort, controller.getEnemies().get(0));
    controller.setState(new EndTurnState(controller));
    controller.attack(voldemort,controller.getEnemies().get(0));
    assertTrue(controller.getEnemies().isEmpty());
  }


  /**
   * Creates three random characters for the player.
   */
  void createThreePlayerCharacter(){
    for (int i = 0; i < 3; i++) {
      var characterNumber = r.nextInt(5);
      if(characterNumber == 0){
        controller.createBlackMageCharacter(BLACK_MAGE_NAME + i, 30, 15, maxMana);
      }
      else if(characterNumber == 1){
        controller.createWhiteMageCharacter(WHITE_MAGE_NAME + i, 30, 15, maxMana);
      }
      else if (characterNumber == 2){
        controller.createEngineerCharacter(ENGINEER_NAME + i, 30, 15);
      } else if (characterNumber == 3){
        controller.createKnightCharacter(KNIGHT_NAME + i, 30, 15);
      }
      else {
        controller.createThiefCharacter(THIEF_NAME + i, 30, 15);
      }
    }
  }

  /**
   * Creates three random characters for the enemy.
   */
  void createThreeEnemyCharacter(){
    controller.createEnemyCharacter("ENEMY1", 50,
          100 , defense , 25);
    controller.createEnemyCharacter("ENEMY2", 100,
        80, defense , 25);
    controller.createEnemyCharacter("ENEMY3", 60,
      100, defense , 25);
  }

  /**
   * Pick a random enemy from the enemies list.
   */
  Enemy pickRandomEnemy(){
    return controller.getEnemies().get(controller.randomValue(0, controller.getEnemies().size()));
  }

  /**
   * Equip a random weapon.
   */
  void randomEquip(){
    while(!controller.isEquipped((IPlayerCharacter) controller.getAttacker())){
      var randIndex = r.nextInt(5);
      controller.equip(controller.getInventory().get(randIndex), (IPlayerCharacter) controller.getAttacker());
    }
  }

  /**
   * This test emulate full game using random values to simulate the user entry.
   */
  @Test
  void gameTest(){
    controller.tryStartGame(3);
    createThreePlayerCharacter();
    controller.tryStartGame(3);
    createThreeEnemyCharacter();
    controller.tryStartGame(3);
    assertTrue(controller.isStartGame());
    controller.tryOnTurn();
    assertFalse(controller.isStartGame());
    assertTrue(controller.isOnTurn());
    while(!controller.isEndGame() && (controller.isOnTurn() | controller.isWaitTurn())) {
      if (controller.isOnTurn()) {
        if (controller.isEnemy(controller.getAttacker())) {
          assertFalse(controller.isSelectAttackTarget());
          controller.trySelectAttackTarget();
          assertTrue(controller.isSelectAttackTarget());
          assertFalse(controller.isEndTurn());
          controller.tryEndTurn();
          assertTrue(controller.isEndTurn());
          controller.attack(controller.getAttacker(), controller.getAttacked());
        } else if (controller.isPlayerCharacter(controller.getAttacker())) {
          assertFalse(controller.isSelectAttackTarget());
          randomEquip();
          controller.trySelectAttackTarget();
          assertTrue(controller.isSelectAttackTarget());
          assertFalse(controller.isEndTurn());
          controller.setAttacked(pickRandomEnemy());
          controller.tryEndTurn();
          assertTrue(controller.isEndTurn());
          controller.attack(controller.getAttacker(), controller.getAttacked());
        }
      }
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    assertTrue(controller.isEndGame());
    if (controller.getWinner().equals("ENEMY")){
      assertTrue(controller.getPlayerParty().isEmpty());
    }else if (controller.getWinner().equals("PLAYER")){
      assertTrue(controller.getEnemies().isEmpty());
    }
  }

  /**
   * Test that controller catch the exceptions on all his catch statements.
   */
  @Test
  void testControllerExceptionCatch(){
    controller.setState(new EndGameState(controller));
    // Catch Exceptions check
    controller.tryOnTurn();
    controller.trySelectAttackTarget();
    controller.tryEndTurn();
    controller.tryWaitTurn();
    controller.tryEndGame("PLAYER");
    controller.tryEndGame("ENEMY");

  }

}