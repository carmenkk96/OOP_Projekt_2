package com.example.projekt_2;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class LiidesUueTaotluseInfo {

    public static void display(String nimi, String uusID) {
        Stage stage = new Stage();

        String[] nimiParts = nimi.split(" ");
        String formattedName = "";
        for (String part : nimiParts) {
            formattedName += part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase() + " ";
        }

        Label aitähLabel = new Label("Aitäh, " + formattedName.trim() + "!");
        aitähLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        aitähLabel.setTextFill(Color.web("#444444"));

        Label uusIdLabel = new Label(uusID);
        uusIdLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        uusIdLabel.setTextFill(Color.GREEN);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(30, 30, 30, 30));
        vBox.getChildren().addAll(aitähLabel, new Label(), uusIdLabel);

        Scene scene = new Scene(vBox, 450, 150);
        stage.setScene(scene);
        stage.show();

        // sulgeb akna, kui kasutaja vajutab enterit
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    stage.close();
                    break;
                default:
                    break;
            }
        });
    }
}