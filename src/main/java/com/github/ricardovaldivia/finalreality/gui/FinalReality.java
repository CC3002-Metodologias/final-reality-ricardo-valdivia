package com.github.ricardovaldivia.finalreality.gui;

import com.github.ricardovaldivia.finalreality.controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
  private int partySize;

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
    configureGame(primaryStage);
    primaryStage.show();
  }

  private void configureGame(Stage stage) throws FileNotFoundException {
    var configRoot = new Group();

    readImage("config_background.jpg", 0, 0, configRoot);
    stage.setTitle("Final reality : Setting Game");
    stage.setResizable(false);

    var text1 = createRectangleText(
        "Initial configurations",
        20, 600, 30,
        Color.DODGERBLUE, configRoot);

    uniformScale(text1, 3);

    // Selecting Party size.
    var party = new ArrayList<String>();
    party.add("1");
    party.add("2");
    party.add("3");
    party.add("4");

    var partyChoice = createPartyChoiceBox(
        "Party Size: ",
        200, 100, party,
        Color.LIGHTGREEN, configRoot);
    var setButton = createButton(
        "Set Size", 270, 100, configRoot);

    var classes = new ArrayList<String>();
    uniformScale(setButton, 1.3);
    uniformScale(partyChoice, 1.3);

    // Start Game.
    var startButton = createButton(
        "Start Game", 640, 500, configRoot);
    startButton.setOnAction(event -> {
      try {
        inGame(stage);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    });

    createScene(stage, configRoot);
  }

  private void inGame(Stage stage) throws FileNotFoundException {
    var inGameRoot = new Group();
    readImage("game_background.jpg", 0, 0, inGameRoot);
    stage.setTitle("Final reality : Playing Game");

    createLabel("paso k", 600, 100, inGameRoot);

    createScene(stage, inGameRoot);
  }

  private Group createClassChoiceBox (
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var choice = new ChoiceBox<>(values);

    choice.setLayoutX(X);
    choice.setLayoutY(Y);
    var text = "Character Class: ";
    var group = createRectangleText(
        text, 24, X - (text.length() * 5.4), Y,
        fill, root);
    Label label = (Label) group.getChildren().get(1);
    SingleSelectionModel<String> model =
        choice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, t1) -> {
          label.setText(text + t1);
        });
    root.getChildren().add(choice);
    mainRoot.getChildren().add(root);
    return  root;
  }

  private Group createPartyChoiceBox (
      String text,
      double X, double Y, ArrayList<String> setValues,
      Color fill, Group mainRoot) {
    var root = new Group();
    createRectangleText(
        text, 24, X - (text.length() * 5.8), Y,
        fill, root);

    ObservableList<String> values = FXCollections.observableArrayList();
    values.addAll(setValues);
    var partyChoice = new ChoiceBox<>(values);
    partyChoice.setLayoutX(X);
    partyChoice.setLayoutY(Y);
    SingleSelectionModel<String> model =
        partyChoice.getSelectionModel();
    model.selectedItemProperty().addListener(
        (observableValue, s, t1) -> {
          partySize = Integer.parseInt(t1);
        });


    root.getChildren().add(partyChoice);
    mainRoot.getChildren().add(root);
    return  root;
  }

  private Group createLabeledTextField(
      String text, double X, double Y, Color fill, Group mainRoot) {
    var root = new Group();
    createRectangleText(
        text, 24, X - (text.length() * 5.5), Y, fill, root);
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
      String text, double height,
      double X, double Y, Paint fill, Group mainRoot) {
    var root = new Group();
    createRectangle(
        text.length() * 5 + 18, height, X-10, Y, fill, root);
    createLabel(text, X, Y, root);
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