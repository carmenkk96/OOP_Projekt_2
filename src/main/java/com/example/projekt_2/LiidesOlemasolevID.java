package com.example.projekt_2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class LiidesOlemasolevID {
    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Veateade");
        window.setMinWidth(400);
        window.setMinHeight(150);
        window.setMaxWidth(600);
        window.setMaxHeight(200);

        Label label = new Label();
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        label.setTextFill(Color.RED);
        label.setText("Sisestatud ID on juba andmebaasis!\nPalun tee uus taotlus!");
        Button close = new Button("Sulge");
        close.setOnAction(e -> window.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);

        Scene scene = new Scene(layout, 450, 150);
        window.setScene(scene);
        window.show();
    }
}
