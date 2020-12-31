package com.github.ricardovaldivia.finalreality.controller;

import com.github.ricardovaldivia.finalreality.controller.handlers.*;
import com.github.ricardovaldivia.finalreality.controller.states.InvalidTransitionException;
import com.github.ricardovaldivia.finalreality.controller.states.StartGameState;
import com.github.ricardovaldivia.finalreality.controller.states.State;
import com.github.ricardovaldivia.finalreality.model.character.Enemy;
import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.IPlayerCharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;
import com.github.ricardovaldivia.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller {
  /**
   * A class that holds the basic logic of the game.
   *
   * @author Ignacio Slater Muñoz.
   * @author Ricardo Valdivia Orellana.
   */
  private final ArrayList<IPlayerCharacter> playerParty =
      new ArrayList<>();
  private final ArrayList<Enemy> enemies =
      new ArrayList<>();
  private final ArrayList<IWeapon> inventory =
      new ArrayList<>();
  private final BlockingQueue<ICharacter> turns =
      new LinkedBlockingQueue<>();
  private final IHandler enemiesDead = new EnemiesDeathHandler(this);
  private final IHandler playerDead = new PlayerDeathHandler(this);
  private final IHandler endTurn = new EndTurnHandler(this);
  private final IHandler notEmptyTurns = new TurnsEmptyHandler(this);
  private State state = null;
  private ICharacter attacked  = null;
  private ICharacter attacker = null;
  private final Random seed =  new Random();
  private final Random r = new Random(seed.nextLong());
  private String winner;


  public Controller(){
    this.createAxeWeapon("AXE", randomValue(40,60),120);
    this.createKnifeWeapon("KNIFE", randomValue(35,55),60);
    this.createSwordWeapon("SWORD", randomValue(50,56), 90);
    this.createBowWeapon("BOW", randomValue(30,40), 50);
    this.createStaffWeapon("STAFF", randomValue(15,26), 40, randomValue(2,51));
  }


  /**
   * Set the current state
   */
  public void setState(State s){
    state = s;
  }

  /**
   * Creates a black mage character and add it to the player's party.
   */
  public void createBlackMageCharacter(@NotNull String name,
                                       int maxHealth, int defensePoints,
                                       int maxMana) {
    var character = new BlackMage(name, turns, maxHealth, defensePoints, maxMana);
    character.addPlayerListener(playerDead);
    character.addTurnsListener(endTurn);
    character.addNotEmptyListener(notEmptyTurns);
    playerParty.add(character);
  }

  /**
   * Creates a white mage character and add it to the player's party.
   */
  public void createWhiteMageCharacter(@NotNull String name,
                                       int maxHealth, int defensePoints,
                                       int maxMana) {
    var character = new WhiteMage(name, turns, maxHealth, defensePoints, maxMana);
    character.addPlayerListener(playerDead);
    character.addTurnsListener(endTurn);
    character.addNotEmptyListener(notEmptyTurns);
    playerParty.add(character);
  }

  /**
   * Creates a engineer character and add it to the player's party.
   */
  public void createEngineerCharacter(@NotNull String name,
                                      int maxHealth, int defensePoints) {
    var character = new Engineer(name, turns, maxHealth, defensePoints);
    character.addPlayerListener(playerDead);
    character.addTurnsListener(endTurn);
    character.addNotEmptyListener(notEmptyTurns);
    playerParty.add(character);
  }

  /**
   * Creates a knight character and add it to the player's party.
   */
  public void createKnightCharacter(@NotNull String name,
                                    int maxHealth, int defensePoints) {
    var character = new Knight(name, turns, maxHealth, defensePoints);
    character.addPlayerListener(playerDead);
    character.addTurnsListener(endTurn);
    character.addNotEmptyListener(notEmptyTurns);
    playerParty.add(character);
  }

  /**
   * Creates a thief character and add it to the player's party.
   */
  public void createThiefCharacter(@NotNull String name,
                                   int maxHealth, int defensePoints) {
    var character = new Thief(name, turns, maxHealth, defensePoints);
    character.addPlayerListener(playerDead);
    character.addTurnsListener(endTurn);
    character.addNotEmptyListener(notEmptyTurns);
    playerParty.add(character);
  }

  /**
   * Creates an enemy character and add it to the enemies party.
   */
  public void createEnemyCharacter(@NotNull String name, int weight, int maxHealth, int defense,
                                   int attack) {
    var enemy = new Enemy(name, weight, turns, maxHealth, defense, attack);
    enemy.addEnemyListener(enemiesDead);
    enemy.addTurnsListener(endTurn);
    enemy.addNotEmptyListener(notEmptyTurns);
    enemies.add(enemy);
  }

  /**
   * Creates a axe weapon and add it to the player's inventory.
   */
  public void createAxeWeapon(@NotNull String name,
                              int physicalDamage, int weight) {
    inventory.add(new Axe(name, physicalDamage, weight));
  }

  /**
   * Creates a bow weapon and add it to the player's inventory.
   */
  public void createBowWeapon(@NotNull String name,
                              int physicalDamage, int weight) {
    inventory.add(new Bow(name, physicalDamage, weight));
  }

  /**
   * Creates a knife weapon and add it to the player's inventory.
   */
  public void createKnifeWeapon(@NotNull String name,
                                int physicalDamage, int weight) {
    inventory.add(new Knife(name, physicalDamage, weight));
  }

  /**
   * Creates a sword weapon and add it to the player's inventory.
   */
  public void createSwordWeapon(@NotNull String name,
                                int physicalDamage, int weight) {
    inventory.add(new Sword(name, physicalDamage, weight));
  }

  /**
   * Create a staff weapon and add it to the player's inventory.
   */
  public void createStaffWeapon(@NotNull String name,
                                int physicalDamage, int weight, int magicDamage) {
    inventory.add(new Staff(name, physicalDamage, weight, magicDamage));
  }

  /**
   * Returns the player's inventory.
   */
  public ArrayList<IWeapon> getInventory() {
    return inventory;
  }

  /**
   * Returns the player's party.
   */
  public ArrayList<IPlayerCharacter> getPlayerParty() {
    return playerParty;
  }

  /**
   *
   * Returns the current status information of a character.
   */
  public HashMap<String, String> getCurrentInfo(ICharacter character){
    return character.getCurrentInfo();
  }

  /**
   *
   * Returns the current status information of a weapon.
   */
  public HashMap<String, String> getCurrentInfo(IWeapon weapon){
    return weapon.getCurrentInfo();
  }

  /**
   * Returns the enemies list.
   */
  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }

  /**
   * Tries to equip a weapon to a player character.
   */
  public void equip(IWeapon weapon, IPlayerCharacter playerCharacter) {
    playerCharacter.equip(weapon);
  }

  /**
   * Makes the attack from one character to another.
   */
  public void attack(ICharacter attackerCharacter, ICharacter attackedCharacter) {
    attackerCharacter.attack(attackedCharacter);
  }

  /**
   * Returns if a weapon is equipped or not.
   */
  public boolean isEquipped(IPlayerCharacter character){
    return character.isEquipped();
  }

  /**
   * Returns the queue.
   */
  public BlockingQueue<ICharacter> getTurns() {
    return turns;
  }

  /**
   * Returns the first character from the queue.
   */
  public ICharacter getFirstFromQueue() {
    return turns.peek();
  }

  /**
   * Removes the character from the queue.
   */
  public void removeFromQueue(ICharacter character) {
    turns.remove(character);
  }


  /**
   * Pick a random character from a list
   */
  public ICharacter pickRandomPlayerCharacter(ArrayList<IPlayerCharacter> list){
    var index = randomValue(0,list.size());
    return list.get(index);
  }
  /**
   * Set the attacked attribute
   */
  public void setAttacked(ICharacter character){
    this.attacked = character;
  }

  /**
   * Set the attacker attribute
   */
  public void setAttacker(ICharacter character) {
    this.attacker = character;
  }

  /**
   * Set the state as StarGame if the party and the enemies got an specific size.
   */
  public void tryStartGame(int size){
      if(this.getPlayerParty().size() == size && this.getEnemies().size() == size) {
        this.putOnQueue();
        this.setState(new StartGameState(this));
      }
  }

  /**
   * Try to change to on turn state
   */
  public void tryOnTurn(){
    try {
        this.setAttacker(getFirstFromQueue());
        state.onTurn();
    } catch (InvalidTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Try to change to select attack target state
   */
  public void trySelectAttackTarget(){
    try {
      state.selectAttackTarget();
    } catch (InvalidTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Try to change to on tun state and remove the attacker from the queue.
   */
  public void tryEndTurn(){
    try {
      this.removeFromQueue(getAttacker());
      state.endTurn();
    } catch (InvalidTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Try to change to wait turn state
   */
  public void tryWaitTurn(){
    try {
      state.waitTurn();
    } catch (InvalidTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Set the winner of the game
   */
  public void setWinner(String winner){
    this.winner = winner;
  }

  /**
   * Try to change to end game state, and set the winner.
   */
  public void tryEndGame(String winner){
    try {
      setAttacker(null);
      setAttacked(null);
      this.setWinner(winner);
      state.endGame();
    } catch (InvalidTransitionException e) {
      e.printStackTrace();
    }
  }

  /**
   * returns a boolean value about the Start Game state
   */
  public boolean isStartGame(){
    return state.isStartGame();
  }

  /**
   * returns a boolean value about the End Game state
   */
  public boolean isEndGame(){
    return state.isEndGame();
  }
  /**
   * returns a boolean value about the End Turn state
   */
  public boolean isEndTurn(){
    return state.isEndTurn();
  }

  /**
   * returns a boolean value about the Select Attack Target state
   */
  public boolean isSelectAttackTarget(){
    return  state.isSelectAttackTarget();
  }

  /**
   * returns a boolean value about the On Turn state
   */
  public boolean isOnTurn(){
    return state.isOnTurn();
  }

  /**
   * returns a boolean value about the Wait Turn state
   */
  public boolean isWaitTurn(){
    return state.isWaitTurn();
  }

  /**
   * Puts a character to wait his turn, and try to change the state,
   * according to some conditions.
   */
  public void endTurn(ICharacter character){
    this.waitTurn(character);
    if(!state.isEndGame()){
      setAttacker(null);
      setAttacked(null);
      if(!getTurns().isEmpty()){
        this.tryOnTurn();
      }else{
        this.tryWaitTurn();
      }
    }
  }


  /**
   * Removes the player character from the party
   */
  public void removeFromParty(IPlayerCharacter playerCharacter) {
    playerParty.remove(playerCharacter);
    removeFromQueue(playerCharacter);
    if (playerParty.isEmpty()){
      this.tryEndGame("ENEMY");
    }
  }

  /**
   * Removes the enemy character from the enemies
   */
  public void removeFromEnemies(Enemy enemy) {
    enemies.remove(enemy);
    removeFromQueue(enemy);
    if (enemies.isEmpty()){
      this.tryEndGame("PLAYER");
    }
  }

  /**
   * Returns the attacker.
   */
  public ICharacter getAttacker(){return attacker;}

  /**
   * Returns de attacked.
   */
  public ICharacter getAttacked(){return attacked;}

  /**
   *
   * Returns a random value in a range.
   */
  public int randomValue(int min, int max){
    return this.r.nextInt(max) + min;
  }

  /**
   * Returns a truth value regarding whether the queried character is an enemy.
   */
  public boolean isEnemy(ICharacter character){
    return getEnemies().contains(character);
  }

  /**
   * Returns a truth value regarding whether the queried character is a player character
   */
  public  boolean isPlayerCharacter(ICharacter character){
    return getPlayerParty().contains(character);
  }

  /**
   * Puts a character to wait for his turn.
   */
  public void waitTurn(ICharacter character) {
    character.waitTurn();
  }

  /**
   * Checks if the turns is empty.
   */
  public boolean isTurnsEmpty() {
    return turns.isEmpty();
  }


  /**
   * Put enemies and player's characters randomly on the queue.
   */
  public void putOnQueue(){
    var list = new ArrayList<ICharacter>();
    list.addAll(this.getPlayerParty());
    list.addAll(this.getEnemies());
    Collections.shuffle(list);
    this.turns.addAll(list);
  }

  /**
   * Returns the winner of the game.
   */
  public String getWinner() {
    return winner;
  }
}