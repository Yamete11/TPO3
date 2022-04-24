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
    Label wordLabel, languageLabel, answerLabel, errorLabel;
    Button acceptButton, cancelButton, changeButton;
    ButtonBar buttonBar;
    TextField wordField, languageField;
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
        errorLabel = new Label("");

        wordField = new TextField();
        languageField = new TextField();

        acceptButton = new Button("Accept");
        acceptButton.setOnAction(event -> {
            boolean check = false;

            word = wordField.getText();
            language = languageField.getText();
            clean();
            try {
                message = Client.startConnection(word + "," + language);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert message != null;
            if(!message.equals("nothing")){
                check = true;
            }
            errorLabel.setText("Wrong language");
            if(check){
                answerLabel.setText(message);
                stage.setScene(scene2);
                errorLabel.setText("");
            }
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

        grid.add(errorLabel, 2, 0, 1, 1);
        grid.add(wordLabel,1 , 1, 1, 1);
        grid.add(languageLabel,1, 2, 1, 1);
        grid.add(wordField, 2, 1, 1, 1);
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
