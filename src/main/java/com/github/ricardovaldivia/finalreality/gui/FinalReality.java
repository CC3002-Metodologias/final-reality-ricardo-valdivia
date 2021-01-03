package com.github.ricardovaldivia.finalreality.gui;

import com.github.ricardovaldivia.finalreality.controller.Controller;
import com.github.ricardovaldivia.finalreality.model.character.player.IPlayerCharacter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class FinalReality extends Application {

  private static final String RESOURCE_PATH = "src/main/resources/";
  private final Controller controller = new Controller();
  private String characterClass;
  private String currentWeapon;
  private final String[] enemiesName = new String[]{"VOLDEMORT", "GRINDELWALD", "ISMA", "THANOS", "DAVY JONES", "" +
      "DARTH VADER", "LOKI", "MALFOY", "JOKER", "GOLLUM", "CRUELA DE VIL", "PRINCIPE HANS" ,"MR. ELECTRIC"};

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Entry point of the application.
   *
   * @param primaryStage
   *     the primary stage for this application, onto which the application scene can be set.
   *     Applications may create other stages, if needed, but they will not be primary stages.
   */
  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    startingGame(primaryStage);
    primaryStage.show();
  }


  /**
   * Create the initial stage for the gui.
   */

  private void startingGame(Stage stage) throws FileNotFoundException {
    var startRoot = new Group();
    var sizeRoot = new Group();
    var charactersRoot = new Group();
    readImage("configWallpaper.jpg", startRoot);
    stage.setResizable(false);
    stage.setTitle("Final Reality");
    // Title
    var firstTitle = createRectangleText("Settings ", 600, 50, Color.DARKTURQUOISE, startRoot);
    uniformScale(firstTitle,3);

    // Choice size Box
    var sizeArray = new ArrayList<String>();
    sizeArray.add("1");
    sizeArray.add("2");
    sizeArray.add("3");
    sizeArray.add("4");
    createPartySizeChoiceBox(sizeArray, sizeRoot);

    // Choice class box
    var classArray = new ArrayList<String>();
    classArray.add("Black Mage");
    classArray.add("White Mage");
    classArray.add("Thief");
    classArray.add("Knight");
    classArray.add("Engineer");

    // Set Button
    var setSizeButton = createButton("Set Size", 700, 150, sizeRoot);
    setSizeButton.setOnAction(event -> {
      startRoot.getChildren().remove(sizeRoot);
      for (int i = 0; i< controller.getSize() ; i++) {
        controller.createEnemyCharacter(
            enemiesName[
                controller.randomValue(
                    0, enemiesName.length)],
            controller.randomValue(40,121),
            controller.randomValue(70,121),
            controller.randomValue(5, 30),
            controller.randomValue(30,121));
      }

      // Display the enemies information on the screen
      displayEnemiesH(startRoot);
      var enemyParty = createRectangleText("ENEMIES:   ", 400,  120, Color.LIGHTGREEN, startRoot);
      uniformScale(enemyParty, 1.3);
      var playerParty = createRectangleText(" YOUR PARTY:    ", 400,  400, Color.LIGHTGREEN, startRoot);
      uniformScale(playerParty, 1.3);
      var textField = createLabeledTextField(charactersRoot);
      createCharacterClassChoiceBox(classArray, charactersRoot);

      // Creates the characters
      var setCharacter = createButton("Set Character",180, 200, charactersRoot);
      setCharacter.setOnAction(event1 -> {
        if (characterClass != null) {
          var field = (TextField) textField.getChildren().get(1);
          if (characterClass.equals("Black Mage")) {
            controller.createBlackMageCharacter(field.getText(), controller.randomValue(60, 150),
                controller.randomValue(10, 26), 50);
          }
          if (characterClass.equals("White Mage")) {
            controller.createWhiteMageCharacter(field.getText(), controller.randomValue(100, 181),
                controller.randomValue(10, 26), 50);
          }
          if (characterClass.equals("Thief")) {
            controller.createThiefCharacter(field.getText(), controller.randomValue(60, 101),
                controller.randomValue(10, 30));
          }
          if (characterClass.equals("Knight")) {
            controller.createKnightCharacter(field.getText(), controller.randomValue(60, 200),
                controller.randomValue(10, 35));
          }
          if (characterClass.equals("Engineer")) {
            controller.createEngineerCharacter(field.getText(), controller.randomValue(60,150),
                controller.randomValue(10, 30));
          }
          displayPlayerPartyH(startRoot);

          // Start the game
          if (controller.getPlayerParty().size() == controller.getSize()) {
            startRoot.getChildren().remove(charactersRoot);
            var startGame = createButton("START GAME", 25, 20, startRoot);
            uniformScale(startGame, 1.5);
            startGame.setOnAction(event2 -> {
              controller.tryStartGame();
              controller.tryOnTurn();
              checkWhereToGo(stage);
            });
          }
        }
      });
    });
    startRoot.getChildren().add(charactersRoot);
    startRoot.getChildren().add(sizeRoot);
    createScene(stage, startRoot);
  }

  /**
   * Create the stage for the gui when the current player is player's character.
   */
  private void inPlayerGame(Stage stage) throws FileNotFoundException {
    var inPlayerGameRoot = new Group();
    var equipRoot = new Group();
    var objectiveRoot = new Group();

    readImage("inGameWallpaper.jpg", inPlayerGameRoot);
    // Title
    var firstTitle = createRectangleText("In Player Turn ", 600, 50, Color.DARKTURQUOISE, inPlayerGameRoot);
    uniformScale(firstTitle,3);
    // Display the information of the enemies and the party of the player on the screen
    displayEnemiesV(inPlayerGameRoot);
    displayPlayerPartyV(inPlayerGameRoot);

    // Choose the weapon
    var weaponArray = new ArrayList<String>();
    weaponArray.add("AXE");
    weaponArray.add("BOW");
    weaponArray.add("KNIFE");
    weaponArray.add("STAFF");
    weaponArray.add("SWORD");

    createWeaponEquipChoiceBox(weaponArray, equipRoot);
    displayCurrentPlayer(inPlayerGameRoot);
    var setWeaponButton = createButton("Set Weapon", 600,350, equipRoot);
    setWeaponButton.setOnAction(event -> {
      if(controller.isEquipped((IPlayerCharacter) controller.getAttacker())) {
        inPlayerGameRoot.getChildren().remove(equipRoot);
        displayCurrentWeapon(inPlayerGameRoot);
        var stringEnemiesArray = new ArrayList<String>();
        for(var enemy : controller.getEnemies()){
          stringEnemiesArray.add(enemy.toString());
        }
        controller.trySelectAttackTarget();

        // Choose the objective
        createObjectiveChoiceBox(stringEnemiesArray, objectiveRoot);
        var setObjectiveButton = createButton("Set Objective", 580, 420, objectiveRoot);
        setObjectiveButton.setOnAction(event1 -> {
          if(controller.getAttacked() != null){
          displayCurrentEnemy(380, inPlayerGameRoot);
          inPlayerGameRoot.getChildren().remove(objectiveRoot);
          controller.tryEndTurn();

          // Do the attack action
          var attackButton = createButton("Attack", 600, 570, inPlayerGameRoot);
          attackButton.setOnAction(event2 -> {
            controller.attack(controller.getAttacker(), controller.getAttacked());
            checkWhereToGo(stage);
          });
        }
      });
      }
      else{
        createRectangleText("You can't equip that WEAPON    ", 550, 400, Color.CRIMSON, equipRoot);
      }
    });

    inPlayerGameRoot.getChildren().add(equipRoot);
    inPlayerGameRoot.getChildren().add(objectiveRoot);
    createScene(stage, inPlayerGameRoot);
  }


  /**
   * Create the stage for the gui when the current player is an enemy.
   */
  private void inEnemyGame(Stage stage) throws FileNotFoundException {
    var inEnemyGameRoot = new Group();
    var objectiveRoot = new Group();

    readImage("inGameWallpaper.jpg", inEnemyGameRoot);
    var firstTitle = createRectangleText("In Enemy Turn ", 600, 50, Color.DARKTURQUOISE, inEnemyGameRoot);
    uniformScale(firstTitle,3);

    // Display the information of the enemies and the party of the player on the screen
    displayEnemiesV(inEnemyGameRoot);
    displayPlayerPartyV(inEnemyGameRoot);
    displayCurrentPlayer(inEnemyGameRoot);
    controller.trySelectAttackTarget();
    // Do the controller set the objective for the enemy
    var setObjective = createButton("Set Objective", 580,360, objectiveRoot);
    setObjective.setOnAction(event -> {
      controller.tryEndTurn();
      inEnemyGameRoot.getChildren().remove(objectiveRoot);
      displayCurrentEnemy(260, inEnemyGameRoot);

      // Do the attack action
      var attackButton = createButton("Attack", 600, 500, inEnemyGameRoot);
      attackButton.setOnAction(event1 -> {
        controller.attack(controller.getAttacker(), controller.getAttacked());
        checkWhereToGo(stage);
      });
    });

    inEnemyGameRoot.getChildren().add(objectiveRoot);
    createScene(stage, inEnemyGameRoot);
  }


  /**
   * Create the Win stage for the gui.
   */
  private void WinGame(Stage stage) throws FileNotFoundException {
    var inWinRoot = new Group();
    readImage("playerWinWallpaper.jpg", inWinRoot);
    var firstTitle = createRectangleText("You Win  ", 600, 50, Color.DARKTURQUOISE, inWinRoot);
    uniformScale(firstTitle,3);

    createScene(stage, inWinRoot);
  }

  /**
   * Create the Lose stage for the gui.
   */
  private void LoseGame(Stage stage) throws FileNotFoundException {
    var inLoseGame = new Group();
    readImage("enemyWinWallpaper.jpg", inLoseGame);
    var firstTitle = createRectangleText("You Lose  ", 600, 50, Color.DARKTURQUOISE, inLoseGame);
    uniformScale(firstTitle,3);
    createScene(stage, inLoseGame);
  }

  /**
   * Create the waiting stage for the gui.
   */
  private void waitGame(Stage stage) throws FileNotFoundException {
    var waitGameRoot = new Group();

    readImage("waitingWallpaper.jpg", waitGameRoot);
    if(controller.isWaitTurn()){
      var rectangle = createRectangleText("All characters are waiting to play  ",
          580, 260, Color.GOLD, waitGameRoot);
      uniformScale(rectangle, 1.5);
      waitingAnimator(stage);
    }

    createScene(stage, waitGameRoot);
  }


  /**
   * An animation that change the stages when a character enter to
   * the queue, manage by handlers.
   */
  private void waitingAnimator(Stage stage) {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if (controller.isEndingWaitTurn()) {
          controller.tryOnTurn();
          if (controller.isEnemy(controller.getAttacker())){
            try {

              inEnemyGame(stage);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
          }
          else {
            try {
              inPlayerGame(stage);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
          }
        }
      }
    };
    timer.start();
  }

  /**
   * Select the corresponding stage of the gui according with the controller stage.
   */

  private void checkWhereToGo(Stage stage){
    if(controller.isWaitTurn()){
      try {
        waitGame(stage);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    else if(controller.isEndGame()){
      if(controller.getWinner().equals("PLAYER")){
        try {
          WinGame(stage);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
      else{
        try {
          LoseGame(stage);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }

    }
    else{
      if(controller.isEnemy(controller.getAttacker())){
        try {
          inEnemyGame(stage);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
      else{
        try {
          inPlayerGame(stage);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Shows all the information of the current player
   */
  private void displayCurrentPlayer(Group mainRoot){
    var root = new Group();
    Color fill = Color.GOLD;
    double X = 580;
    double Y = 120;
    var stats = controller.getCurrentInfo(controller.getAttacker());
    createRectangleText(
        "Current Player " ,
        X, Y , fill, root);
    createRectangleText(
        "Name" + ": " + stats.get("Name") + "        ",
        X, Y + 25, fill, root);
    createRectangleText(
        "Character Class" +
            ": " + stats.get("Character Class") + "  ",
        X + 10, Y + 50 , fill, root);
    createRectangleText(
        "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
        X + 10, Y + 75 , fill, root);
    createRectangleText(
        "Defense" + ": " + stats.get("defense") + "   ",
        X + 10, Y + 100 , fill, root);
    mainRoot.getChildren().add(root);
  }

  /**
   * Show all the information of the objective selected.
   */
  private void displayCurrentEnemy(
      double Y,
      Group mainRoot){
    var root = new Group();
    var stats = controller.getCurrentInfo(controller.getAttacked());
    createRectangleText(
        "Current Objective " ,
        580, Y , Color.FIREBRICK, root);
    createRectangleText(
        "Name" + ": " + stats.get("Name") + "        ",
        580, Y + 25, Color.FIREBRICK, root);
    createRectangleText(
        "Character Class" +
            ": " + stats.get("Character Class") + "  ",
        (double) 580 + 10, Y + 50 , Color.FIREBRICK, root);
    createRectangleText(
        "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
        (double) 580 + 10, Y + 75 , Color.FIREBRICK, root);
    createRectangleText(
        "Defense" + ": " + stats.get("defense") + "   ",
        (double) 580 + 10, Y + 100 , Color.FIREBRICK, root);
    mainRoot.getChildren().add(root);
  }

  /**
   * Show all the information of the weapon selected.
   */
  private void displayCurrentWeapon(
      Group mainRoot){
    var root = new Group();
    var stats = controller.getCurrentInfo(controller.findWeapon(currentWeapon));
    createRectangleText(
        "Current Weapon  ",
        580, 260, Color.GOLD, root);
    createRectangleText(
        "Name" + ": " + stats.get("Name") + "    ",
        580, (double) 260 + 25, Color.GOLD, root);
    createRectangleText(
        "Attack" +
            ": " + stats.get("PhysicalDamage") + "  ",
        (double) 580 + 10, (double) 260 + 50 , Color.GOLD, root);
    createRectangleText(
        "Weight"+ ":  " + stats.get("Weight")+" ",
        (double) 580 + 10, (double) 260 + 75 , Color.GOLD, root);
    mainRoot.getChildren().add(root);
  }

  /**
   * Shows the information of all the characters on the party, in a horizontal way.
   */

  private void displayPlayerPartyH(
      Group mainRoot){
    var root = new Group();
    var posXIter = 0;
    for (var character : controller.getPlayerParty()){
      var stats = controller.getCurrentInfo(character);
      createRectangleText(
          "Name" + ": " + stats.get("Name") + "   ",
          (double) 400 + (posXIter * 180), 480, Color.LIGHTGREEN, root);
      createRectangleText(
          "Character Class" +
              ": " + stats.get("Character Class") + "  ",
          (double) 400 + 10 + (posXIter * 180), (double) 480 + 25 , Color.LIGHTGREEN, root);
      createRectangleText(
          "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
          (double) 400 + 10 + (posXIter * 180), (double) 480 + 50 , Color.LIGHTGREEN, root);
      createRectangleText(
          "Defense" + ": " + stats.get("defense") + "   ",
          (double) 400 + 10 + (posXIter * 180), (double) 480 + 75 , Color.LIGHTGREEN, root);
      createRectangleText(
          "Mana" + ": " + stats.get("maxMana") + "   ",
          (double) 400 + 10 + (posXIter * 180), (double) 480 + 100 , Color.LIGHTGREEN, root);
      posXIter ++;
    }
    mainRoot.getChildren().add(root);
  }

  /**
   * Shows the information of all the characters on the party, in a vertical way.
   */
  private void displayPlayerPartyV(
      Group mainRoot){
    var root = new Group();
    var posYIter = 0;
    for (var character : controller.getPlayerParty()){
      var stats = controller.getCurrentInfo(character);
      createRectangleText(
          "Name" + ": " + stats.get("Name") + "   ",
          900, (double) 50 + (posYIter * 135), Color.LIGHTGREEN, root);
      createRectangleText(
          "Character Class" +
              ": " + stats.get("Character Class") + "  ",
          (double) 900 + 10 , (double) 50 + 25 + (posYIter * 135), Color.LIGHTGREEN, root);
      createRectangleText(
          "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
          (double) 900 + 10 , (double) 50 + 50 + (posYIter * 135), Color.LIGHTGREEN, root);
      createRectangleText(
          "Defense" + ": " + stats.get("defense") + "   ",
          (double) 900 + 10 , (double) 50 + 75 + (posYIter * 135), Color.LIGHTGREEN, root);
      createRectangleText(
          "Mana" + ": " + stats.get("maxMana") + "   ",
          (double) 900 + 10 , (double) 50 + 100 + (posYIter * 135), Color.LIGHTGREEN, root);
      posYIter ++;
    }
    mainRoot.getChildren().add(root);
  }


  /**
   * Shows the information of all the enemies, in a horizontal way.
   */
  private void displayEnemiesH(
      Group mainRoot){
    var root = new Group();
    var posXIter = 0;
    for (var character : controller.getEnemies()){
      var stats = controller.getCurrentInfo(character);
      createRectangleText(
          "Name" + ": " + stats.get("Name") + "        ",
          (double) 400 + (posXIter * 180), 200, Color.LIGHTCORAL, root);
      createRectangleText(
          "Character Class" +
              ": " + stats.get("Character Class") + "  ",
          (double) 400 + 10 + (posXIter * 180), (double) 200 + 25 , Color.LIGHTCORAL, root);
      createRectangleText(
          "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
          (double) 400 + 10 + (posXIter * 180), (double) 200 + 50 , Color.LIGHTCORAL, root);
      createRectangleText(
          "Defense" + ": " + stats.get("defense") + "   ",
          (double) 400 + 10 + (posXIter * 180), (double) 200 + 75 , Color.LIGHTCORAL, root);
      createRectangleText(
          "Weight" + ": " + stats.get("Weight") + "   ",
          (double) 400 + 10 + (posXIter * 180), (double) 200 + 100 , Color.LIGHTCORAL, root);
      createRectangleText(
          "Attack" + ": " + stats.get("Attack") + "   ",
          (double) 400 + 10 + (posXIter * 180), (double) 200 + 125 , Color.LIGHTCORAL, root);
      posXIter ++;
    }
    mainRoot.getChildren().add(root);
  }

  /**
   * Shows the information of all the enemies, in a vertical way.
   */
  private void displayEnemiesV(
      Group mainRoot){
    var root = new Group();
    var posYIter = 0;
    for (var character : controller.getEnemies()){
      var stats = controller.getCurrentInfo(character);
      createRectangleText(
          "Name" + ": " + stats.get("Name") + "        ",
          200, (double) 50 + (posYIter * 160), Color.LIGHTCORAL, root);
      createRectangleText(
          "Character Class" +
              ": " + stats.get("Character Class") + "  ",
          (double) 200 + 10 , (double) 50 + 25 + (posYIter * 160) , Color.LIGHTCORAL, root);
      createRectangleText(
          "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
          (double) 200 + 10 , (double) 50 + 50 + (posYIter * 160), Color.LIGHTCORAL, root);
      createRectangleText(
          "Defense" + ": " + stats.get("defense") + "   ",
          (double) 200 + 10 , (double) 50 + 75 + (posYIter * 160), Color.LIGHTCORAL, root);
      createRectangleText(
          "Weight" + ": " + stats.get("Weight") + "   ",
          (double) 200 + 10 , (double) 50 + 100 + (posYIter * 160), Color.LIGHTCORAL, root);
      createRectangleText(
          "Attack" + ": " + stats.get("Attack") + "   ",
          (double) 200 + 10 , (double) 50 + 125 + (posYIter * 160), Color.LIGHTCORAL, root);
      posYIter ++;
    }
    mainRoot.getChildren().add(root);
  }

  /**
   * Create a box where the user can choose the size of the parties on the game.
   */
  private void createPartySizeChoiceBox(
      ArrayList<String> setValues,
      Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);
    choice.setLayoutX(600);
    choice.setLayoutY(150);
    var text = "Choose Party Size: ";
    createRectangleText(
        text, (double) 600 - (text.length() * 5.4), 150,
        Color.AQUAMARINE, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, text1) -> controller.setSize(Integer.parseInt(text1)));
    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }

  /**
   * Create a box where the user can choose character class for an specific character.
   */
  private void createCharacterClassChoiceBox(
      ArrayList<String> setValues,
      Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);
    choice.setLayoutX(100);
    choice.setLayoutY(250);
    var text = "Choose Class: ";
    createRectangleText(
        text, (double) 100 - (text.length() * 5.4), 250,
        Color.AQUAMARINE, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, text1) -> characterClass = text1);
    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }

  /**
   * Create a box where the user can choose the weapon that want to equip to the current
   * player character.
   */
  private void createWeaponEquipChoiceBox(
      ArrayList<String> setValues,
      Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);
    choice.setLayoutX(600);
    choice.setLayoutY(300);
    var text = "Choose Weapon:    ";
    createRectangleText(
        text, (double) 600 - (text.length() * 5.4), 300,
        Color.GOLDENROD, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, text1) -> {
          var weapon = controller.findWeapon(text1);
          currentWeapon = text1;
          controller.equip(weapon, (IPlayerCharacter) controller.getAttacker());
        });
    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }


  /**
   * Create a box where the user can choose the enemy that want to attack.
   */
  private void createObjectiveChoiceBox(
      ArrayList<String> setValues,
      Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);
    choice.setLayoutX(580);
    choice.setLayoutY(380);
    var text = "Choose your objective: ";
    createRectangleText(
        text, (double) 580 - (text.length() * 5.4), 380,
        Color.GOLDENROD, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, text1) -> {
          var enemy = controller.findEnemy(text1);
          controller.setAttacked(enemy);
        });
    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }


  /**
   * Create a box where the user can insert the name for his characters.
   */
  private Group createLabeledTextField(
      Group mainRoot) {
    var root = new Group();
    createRectangleText(
        "Name:  ", (double) 70 - ("Name:  ".length() * 5.5), 200, Color.GREENYELLOW, root);
    createTextField(root);
    mainRoot.getChildren().add(root);
    return root;
  }

  /**
   * Create a box where text can be added.
   */
  private void createTextField(
      Group root) {
    var field = new TextField();
    field.setLayoutX(70);
    field.setLayoutY(200);
    root.getChildren().add(field);
  }

  /**
   * Scale any node according to a certain amount that it's given.
   */
  private void uniformScale (Node node, double scale) {
    node.setScaleX(scale);
    node.setScaleY(scale);
  }


  /**
   * Create a rectangle with color and text on a certain position.
   */
  private Group createRectangleText(
      String text,
      double X, double Y, Paint fill, Group mainRoot) {
    var root = new Group();
    createRectangle(
        text.length() * 5 + 18, X-10, Y, fill, root);
    createLabel(text, X, Y + 3, root);
    mainRoot.getChildren().add(root);
    return root;
  }

  /**
   * Create a color rectangle on a certain position.
   */
  private void createRectangle(
      double width,
      double X, double Y, Paint fill, Group root) {
    var rectangle = new Rectangle();
    rectangle.setHeight(24);
    rectangle.setWidth(width);
    rectangle.setX(X);
    rectangle.setY(Y);
    rectangle.setFill(fill);
    root.getChildren().add(rectangle);
  }

  /**
   * Show a image on the stage.
   */
  private void readImage(
      String fileName,
      Group root)
      throws FileNotFoundException {
    var img = new ImageView(
        new Image(
            new FileInputStream(
                RESOURCE_PATH + fileName)));
    img.setLayoutX(0);
    img.setLayoutY(0);
    root.getChildren().add(img);
  }

  /**
   * Create a text an set it on a certain position on the stage.
   */
  private void createLabel (
      String text, double X, double Y, Group root) {
    var label = new Label(text);
    label.setLayoutX(X);
    label.setLayoutY(Y);
    root.getChildren().add(label);
  }

  /**
   * Create and set a scene.
   */
  private void createScene (Stage stage, Group root) {
    var scene = new Scene(root, 1280, 720);
    stage.setScene(scene);
  }

  /**
   * Create a button with text on a certain position on the stage.
   */

  private @NotNull Button createButton(
      String text, double X, double Y, Group root) {
    var button = new Button(text);
    button.setLayoutX(X);
    button.setLayoutY(Y);
    button.setFocusTraversable(false);
    root.getChildren().add(button);
    return button;
  }

}