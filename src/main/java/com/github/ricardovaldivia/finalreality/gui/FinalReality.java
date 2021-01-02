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

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
  private int partySize;
  private String characterClass;
  private String currentWeapon;
  private String[] enemiesName = new String[]{"VOLDEMORT", "GRINDELWALD", "ISMA", "THANOS", "DAVY JONES", "" +
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


  private void startingGame(Stage stage) throws FileNotFoundException {
    var startRoot = new Group();
    var sizeRoot = new Group();
    var charactersRoot = new Group();
    readImage("configWallpaper.jpg", 0,0, startRoot);
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
    createPartySizeChoiceBox(600, 150, sizeArray, Color.AQUAMARINE, sizeRoot);

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
      List<Integer> range = IntStream.rangeClosed(
          0, controller.getSize()-1).boxed().collect(Collectors.toList());
      for (var enemy : range){
        controller.createEnemyCharacter(
            enemiesName[
                controller.randomValue(
                    0, enemiesName.length)],
            controller.randomValue(40,121),
            controller.randomValue(50,111),
            controller.randomValue(5, 30),
            controller.randomValue(30,121));
      }
      displayEnemiesH(400,200, Color.LIGHTCORAL, startRoot);
      var enemyParty = createRectangleText("ENEMIES:   ", 400,  120, Color.LIGHTGREEN, startRoot);
      uniformScale(enemyParty, 1.3);
      var playerParty = createRectangleText(" YOUR PARTY:    ", 400,  400, Color.LIGHTGREEN, startRoot);
      uniformScale(playerParty, 1.3);
      var textField = createLabeledTextField("Name:  ", 70, 200, Color.GREENYELLOW, charactersRoot);
      createCharacterClassChoiceBox(100, 250, classArray, Color.AQUAMARINE, charactersRoot);
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
            controller.createKnightCharacter(field.getText(), controller.randomValue(60,150),
                controller.randomValue(10, 30));
          }
          displayPlayerPartyH(400, 480, Color.LIGHTGREEN, startRoot);
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

  private void inPlayerGame(Stage stage) throws FileNotFoundException {
    var inPlayerGameRoot = new Group();
    var equipRoot = new Group();
    var objectiveRoot = new Group();


    readImage("inGameWallpaper.jpg", 0,0, inPlayerGameRoot);
    // Title
    var firstTitle = createRectangleText("In Player Turn ", 600, 50, Color.DARKTURQUOISE, inPlayerGameRoot);
    uniformScale(firstTitle,3);
    displayEnemiesV(200,50, Color.LIGHTCORAL, inPlayerGameRoot);
    displayPlayerPartyV(900, 50, Color.LIGHTGREEN, inPlayerGameRoot);
    var weaponArray = new ArrayList<String>();
    weaponArray.add("AXE");
    weaponArray.add("BOW");
    weaponArray.add("KNIFE");
    weaponArray.add("STAFF");
    weaponArray.add("SWORD");
    createWeaponEquipChoiceBox(600, 300, weaponArray, Color.GOLDENROD, equipRoot);
    displayCurrentPlayer(580,130,Color.GOLD, inPlayerGameRoot);
    var setWeaponButton = createButton("Set Weapon", 600,350, equipRoot);
    setWeaponButton.setOnAction(event -> {
      if(controller.isEquipped((IPlayerCharacter) controller.getAttacker())) {
        inPlayerGameRoot.getChildren().remove(equipRoot);
        displayCurrentWeapon(580, 260,Color.GOLD, inPlayerGameRoot);
        var stringEnemiesArray = new ArrayList<String>();
        for(var enemy : controller.getEnemies()){
          stringEnemiesArray.add(enemy.toString());
        }
        controller.trySelectAttackTarget();
        createObjectiveChoiceBox(580,380, stringEnemiesArray,Color.GOLDENROD, objectiveRoot);
        var setObjectiveButton = createButton("Set Objective", 580, 420, objectiveRoot);
        setObjectiveButton.setOnAction(event1 -> {
          displayCurrentEnemy(580, 380, Color.FIREBRICK, inPlayerGameRoot);
          inPlayerGameRoot.getChildren().remove(objectiveRoot);
          controller.tryEndTurn();
          var attackButton = createButton("Attack", 600, 570, inPlayerGameRoot);
          attackButton.setOnAction(event2 -> {
            controller.attack(controller.getAttacker(), controller.getAttacked());
            checkWhereToGo(stage);
          });
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


  private void inEnemyGame(Stage stage) throws FileNotFoundException {
    var inEnemyGameRoot = new Group();
    var objectiveRoot = new Group();

    readImage("inGameWallpaper.jpg", 0,0, inEnemyGameRoot);
    var firstTitle = createRectangleText("In Enemy Turn ", 600, 50, Color.DARKTURQUOISE, inEnemyGameRoot);
    uniformScale(firstTitle,3);

    displayEnemiesV(200,50, Color.LIGHTCORAL, inEnemyGameRoot);
    displayPlayerPartyV(900, 50, Color.LIGHTGREEN, inEnemyGameRoot);
    displayCurrentPlayer(580,130,Color.GOLD, inEnemyGameRoot);
    controller.trySelectAttackTarget();
    var setObjective = createButton("Set Objective", 580,360, objectiveRoot);
    setObjective.setOnAction(event -> {
      controller.tryEndTurn();
      inEnemyGameRoot.getChildren().remove(objectiveRoot);
      displayCurrentEnemy(580,260,Color.FIREBRICK,inEnemyGameRoot);
      var attackButton = createButton("Attack", 600, 500, inEnemyGameRoot);
      attackButton.setOnAction(event1 -> {
        controller.attack(controller.getAttacker(), controller.getAttacked());
        checkWhereToGo(stage);
      });
    });

    inEnemyGameRoot.getChildren().add(objectiveRoot);
    createScene(stage, inEnemyGameRoot);
  }

  private void WinGame(Stage stage) throws FileNotFoundException {
    var inWinRoot = new Group();
    readImage("playerWinWallpaper.jpg", 0, 0 ,inWinRoot);
    var firstTitle = createRectangleText("You Win  ", 600, 50, Color.DARKTURQUOISE, inWinRoot);
    uniformScale(firstTitle,3);

    createScene(stage, inWinRoot);
  }

  private void LoseGame(Stage stage) throws FileNotFoundException {
    var inLoseGame = new Group();
    readImage("enemyWinWallpaper.jpg", 0, 0 ,inLoseGame);
    var firstTitle = createRectangleText("You Lose  ", 600, 50, Color.DARKTURQUOISE, inLoseGame);
    uniformScale(firstTitle,3);
    createScene(stage, inLoseGame);
  }

  private void waitGame(Stage stage) throws FileNotFoundException {
    var waitGameRoot = new Group();

    readImage("waitingWallpaper.jpg", 0, 0, waitGameRoot);
    if(controller.isWaitTurn()){
      createRectangleText("All characters are waiting to play   ",
          600, 300, Color.GOLD, waitGameRoot);
      waitingAnimator(stage);
    }

    createScene(stage, waitGameRoot);
  }


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
  private void displayCurrentPlayer(
      double X, double Y,
      Color fill, Group mainRoot){
    var root = new Group();
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

  private void displayCurrentEnemy(
      double X, double Y,
      Color fill, Group mainRoot){
    var root = new Group();
    var stats = controller.getCurrentInfo(controller.getAttacked());
    createRectangleText(
        "Current Objective " ,
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

  private void displayCurrentWeapon(
      double X, double Y,
      Color fill, Group mainRoot){
    var root = new Group();
    var stats = controller.getCurrentInfo(controller.findWeapon(currentWeapon));
    createRectangleText(
        "Current Weapon  ",
        X, Y , fill, root);
    createRectangleText(
        "Name" + ": " + stats.get("Name") + "    ",
        X, Y + 25, fill, root);
    createRectangleText(
        "Attack" +
            ": " + stats.get("PhysicalDamage") + "  ",
        X + 10, Y + 50 , fill, root);
    createRectangleText(
        "Weight"+ ":  " + stats.get("Weight")+" ",
        X + 10, Y + 75 , fill, root);
    mainRoot.getChildren().add(root);
  }


  private void displayPlayerPartyH(
      double X, double Y,
      Color fill, Group mainRoot){
    var root = new Group();
    var posXIter = 0;
    for (var character : controller.getPlayerParty()){
      var stats = controller.getCurrentInfo(character);
      createRectangleText(
          "Name" + ": " + stats.get("Name") + "   ",
          X+ (posXIter * 180), Y , fill, root);
      createRectangleText(
          "Character Class" +
              ": " + stats.get("Character Class") + "  ",
          X + 10 + (posXIter * 180), Y + 25 , fill, root);
      createRectangleText(
          "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
          X + 10 + (posXIter * 180), Y + 50 , fill, root);
      createRectangleText(
          "Defense" + ": " + stats.get("defense") + "   ",
          X + 10 + (posXIter * 180), Y + 75 , fill, root);
      createRectangleText(
          "Mana" + ": " + stats.get("maxMana") + "   ",
          X + 10 + (posXIter * 180), Y + 100 , fill, root);
      posXIter ++;
    }
    mainRoot.getChildren().add(root);
  }
  private void displayPlayerPartyV(
      double X, double Y,
      Color fill, Group mainRoot){
    var root = new Group();
    var posYIter = 0;
    for (var character : controller.getPlayerParty()){
      var stats = controller.getCurrentInfo(character);
      createRectangleText(
          "Name" + ": " + stats.get("Name") + "   ",
          X, Y + (posYIter * 135), fill, root);
      createRectangleText(
          "Character Class" +
              ": " + stats.get("Character Class") + "  ",
          X + 10 , Y + 25 + (posYIter * 135), fill, root);
      createRectangleText(
          "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
          X + 10 , Y + 50 + (posYIter * 135), fill, root);
      createRectangleText(
          "Defense" + ": " + stats.get("defense") + "   ",
          X + 10 , Y + 75 + (posYIter * 135), fill, root);
      createRectangleText(
          "Mana" + ": " + stats.get("maxMana") + "   ",
          X + 10 , Y + 100 + (posYIter * 135), fill, root);
      posYIter ++;
    }
    mainRoot.getChildren().add(root);
  }

  private void displayEnemiesH(
      double X, double Y,
      Color fill, Group mainRoot){
    var root = new Group();
    var posXIter = 0;
    for (var character : controller.getEnemies()){
      var stats = controller.getCurrentInfo(character);
      createRectangleText(
          "Name" + ": " + stats.get("Name") + "        ",
          X+ (posXIter * 180), Y , fill, root);
      createRectangleText(
          "Character Class" +
              ": " + stats.get("Character Class") + "  ",
          X + 10 + (posXIter * 180), Y + 25 , fill, root);
      createRectangleText(
          "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
          X + 10 + (posXIter * 180), Y + 50 , fill, root);
      createRectangleText(
          "Defense" + ": " + stats.get("defense") + "   ",
          X + 10 + (posXIter * 180), Y + 75 , fill, root);
      createRectangleText(
          "Weight" + ": " + stats.get("Weight") + "   ",
          X + 10 + (posXIter * 180), Y + 100 , fill, root);
      createRectangleText(
          "Attack" + ": " + stats.get("Attack") + "   ",
          X + 10 + (posXIter * 180), Y + 125 , fill, root);
      posXIter ++;
    }
    mainRoot.getChildren().add(root);
  }

  private void displayEnemiesV(
      double X, double Y,
      Color fill, Group mainRoot){
    var root = new Group();
    var posYIter = 0;
    for (var character : controller.getEnemies()){
      var stats = controller.getCurrentInfo(character);
      createRectangleText(
          "Name" + ": " + stats.get("Name") + "        ",
          X, Y + (posYIter * 160), fill, root);
      createRectangleText(
          "Character Class" +
              ": " + stats.get("Character Class") + "  ",
          X + 10 , Y + 25 + (posYIter * 160) , fill, root);
      createRectangleText(
          "HP"+ ": " + stats.get("currentHealth") + "/" + stats.get("maxHealth") + "   ",
          X + 10 , Y + 50 + (posYIter * 160), fill, root);
      createRectangleText(
          "Defense" + ": " + stats.get("defense") + "   ",
          X + 10 , Y + 75 + (posYIter * 160), fill, root);
      createRectangleText(
          "Weight" + ": " + stats.get("Weight") + "   ",
          X + 10 , Y + 100 + (posYIter * 160), fill, root);
      createRectangleText(
          "Attack" + ": " + stats.get("Attack") + "   ",
          X + 10 , Y + 125 + (posYIter * 160), fill, root);
      posYIter ++;
    }
    mainRoot.getChildren().add(root);
  }

  private void createPartySizeChoiceBox (
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);
    choice.setLayoutX(X);
    choice.setLayoutY(Y);
    var text = "Choose Party Size: ";
    createRectangleText(
        text, X - (text.length() * 5.4), Y,
        fill, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, text1) -> {
          controller.setSize(Integer.parseInt(text1));
        });
    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }


  private void createCharacterClassChoiceBox (
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);
    choice.setLayoutX(X);
    choice.setLayoutY(Y);
    var text = "Choose Class: ";
    createRectangleText(
        text, X - (text.length() * 5.4), Y,
        fill, root);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, text1) -> {
         characterClass = text1;
        });
    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
  }

  private void createWeaponEquipChoiceBox (
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);
    choice.setLayoutX(X);
    choice.setLayoutY(Y);
    var text = "Choose Weapon:    ";
    createRectangleText(
        text, X - (text.length() * 5.4), Y,
        fill, root);
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

  private void createObjectiveChoiceBox (
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);
    choice.setLayoutX(X);
    choice.setLayoutY(Y);
    var text = "Choose your objective: ";
    createRectangleText(
        text, X - (text.length() * 5.4), Y,
        fill, root);
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


  private Group createLabeledTextField(
      String text, double X, double Y, Color fill, Group mainRoot) {
    var root = new Group();
    createRectangleText(
        text, X - (text.length() * 5.5), Y, fill, root);
    createTextField(X, Y, root);
    mainRoot.getChildren().add(root);
    return root;
  }

  private TextField createTextField (
      double X, double Y, Group root) {
    var field = new TextField();
    field.setLayoutX(X);
    field.setLayoutY(Y);
    root.getChildren().add(field);
    return field;
  }

  private void uniformScale (Node node, double scale) {
    node.setScaleX(scale);
    node.setScaleY(scale);
  }

  private Group createRectangleText(
      String text,
      double X, double Y, Paint fill, Group mainRoot) {
    var root = new Group();
    createRectangle(
        text.length() * 5 + 18, 24, X-10, Y, fill, root);
    createLabel(text, X, Y + 3, root);
    mainRoot.getChildren().add(root);
    return root;
  }

  private Rectangle createRectangle(
      double width, double height,
      double X, double Y, Paint fill, Group root) {
    var rectangle = new Rectangle();
    rectangle.setHeight(height);
    rectangle.setWidth(width);
    rectangle.setX(X);
    rectangle.setY(Y);
    rectangle.setFill(fill);
    root.getChildren().add(rectangle);
    return rectangle;
  }

  private ImageView readImage(
      String fileName, double X,
      double Y, Group root)
      throws FileNotFoundException {
    var img = new ImageView(
        new Image(
            new FileInputStream(
                RESOURCE_PATH + fileName)));
    img.setLayoutX(X);
    img.setLayoutY(Y);
    root.getChildren().add(img);
    return img;
  }

  private Label createLabel (
      String text, double X, double Y, Group root) {
    var label = new Label(text);
    label.setLayoutX(X);
    label.setLayoutY(Y);
    root.getChildren().add(label);
    return label;
  }

  private Scene createScene (Stage stage, Group root) {
    var scene = new Scene(root, 1280, 720);
    stage.setScene(scene);
    return scene;
  }

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