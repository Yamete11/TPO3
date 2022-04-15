package com.example.serverclienttpo3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {

    GridPane grid, secondGrid;
    Label wordLabel, languageLabel, answerLabel;
    Button acceptButton, cancelButton, changeButton;
    ButtonBar buttonBar;
    TextField wordField, languageField, answerField;
    Scene scene1, scene2;
    String word, language, message;

    @Override
    public void start(Stage stage) throws Exception {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(40));

        secondGrid = new GridPane();
        secondGrid.setAlignment(Pos.CENTER);
        secondGrid.setHgap(10);
        secondGrid.setVgap(10);
        secondGrid.setPadding(new Insets(40));


        wordLabel = new Label("Word : ");
        languageLabel = new Label("Language : ");

        wordField = new TextField();
        languageField = new TextField();

        acceptButton = new Button("Accept");
        acceptButton.setOnAction(event -> {
            stage.setScene(scene2);
            try {
                Client.startConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            word = wordField.getText();
            language = languageField.getText();
            Client.sendMessage(word + "," + language);
            try {
                message = Client.readMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            answerLabel.setText(message);
        });
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            clean();
        });

        changeButton = new Button("Change Data");
        changeButton.setOnAction(event -> {
            clean();
            stage.setScene(scene1);
        });

        buttonBar = new ButtonBar();
        buttonBar.getButtons().addAll(acceptButton, cancelButton);

        grid.add(wordLabel,0 , 0, 1, 1);
        grid.add(languageLabel,0, 2, 1, 1);
        grid.add(wordField, 2, 0, 1, 1);
        grid.add(languageField, 2, 2, 1, 1);
        grid.add(buttonBar, 2, 4, 1, 1);

        answerLabel = new Label();
        secondGrid.add(answerLabel, 0, 2, 1, 1);
        secondGrid.add(changeButton, 0, 4, 1, 1);
        scene1 = new Scene(grid, 400, 300);
        scene2 = new Scene(secondGrid, 400, 300);

        stage.setTitle("TPO");
        stage.setScene(scene1);
        stage.show();

    }

    public void clean(){
        wordField.clear();
        languageField.clear();
    }
}
