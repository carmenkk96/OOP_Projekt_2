package com.example.projekt_2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class KasutajaliidesPeaklass extends Application implements EventHandler<ActionEvent> {
    Button button;
    Button button2;
    TextField nimeväli;
    TextField tootenimeväli;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primarystage) throws Exception {
        primarystage.setTitle("CRF ID Generator");


        button = new Button();
        button.setText("Esita taotlus");
        button.setStyle("-fx-border-color: green;");
        button.setOnAction(this);

        button2 = new Button();
        button2.setText("Tühista");
        button2.setStyle("-fx-border-color: red;");
        button2.setOnAction(this);

        Label nimelabel = new Label("Taotleja nimi: ");
        nimeväli = new TextField();

        Label tootenimelabel = new Label("Toote nimetus: ");
        tootenimeväli = new TextField();

        nimeväli.setPrefWidth(200);
        tootenimeväli.setPrefWidth(200);

        //Esimene aken
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label kellaLabel = new Label();
        DateTimeFormatter kuupäevaVormindaja = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        kellaLabel.setText(LocalDateTime.now().format(kuupäevaVormindaja));

        GridPane.setConstraints(kellaLabel, 0, 0);
        gridPane.getChildren().add(kellaLabel);

        gridPane.add(nimelabel, 0, 1);
        gridPane.add(nimeväli, 1, 1);
        gridPane.add(tootenimelabel, 0, 2);
        gridPane.add(tootenimeväli, 1, 2);
        gridPane.add(button, 0, 3);
        gridPane.add(button2, 1, 3);


        Timeline kellaajaUuendaja = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            kellaLabel.setText(LocalDateTime.now().format(kuupäevaVormindaja));
        }));
        kellaajaUuendaja.setCycleCount(Timeline.INDEFINITE);
        kellaajaUuendaja.play();

        //kui kasutaja vajutab Enter-klahvi tekstiväljal, toimub sama toiming nagu nuppude vajutamisel
        nimeväli.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                button.fire();
            }
        });
        tootenimeväli.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                button.fire();
            }
        });

        Scene scene = new Scene(gridPane, 400, 150);
        primarystage.setScene(scene);
        primarystage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //kui vajutab esita taotlus nuppu või enterit, siis see osa käivitub
        try {
            if (actionEvent.getSource() == button) {
                String nimi = nimeväli.getText();
                String tootenimi = tootenimeväli.getText();
                String[] nimetükid = nimi.split(" ");
                for (String nimetükk : nimetükid) {
                    if (!nimetükk.matches("^[a-zA-Z]*$")) {
                        throw new ViganeSisendErind();
                    }
                }
                if (nimetükid.length < 2 || tootenimi == null) {
                    throw new ViganeSisendErind();
                }
                ID uusId = new ID(nimi, tootenimi);
                //System.out.println(uusId);
                if (uusId.toString().contains("on juba andmebaasis")) {
                    throw new OlemasolevErind();
                } else {
                    LiidesUueTaotluseInfo.display(nimi, uusId.toString());
                }
            }
            //kui vajutab tühista nuppu, siis see osa käivitub
            else if (actionEvent.getSource() == button2) {
                System.out.println("Taotlus tühistatud.");
            }
        } catch (OlemasolevErind e) {
            LiidesOlemasolevID.display();
            System.out.println(e.getMessage());
        } catch (ViganeSisendErind e) {
            LiidesVeateade.display();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Stage primaryStage = (Stage) button.getScene().getWindow();
            primaryStage.close();
        }
    }
}
