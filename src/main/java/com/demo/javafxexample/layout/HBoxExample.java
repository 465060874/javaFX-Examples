package com.demo.javafxexample.layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class HBoxExample extends Application {
    @Override
    public void start(Stage stage) {
        layout1(stage);
    }

    public static void main(String args[]) {
        launch(args);
    }

    private void layout1(Stage stage) {
        //creating a text field
        TextField textField = new TextField();

        //Creating the play button
        Button playButton = new Button("Play");

        //Creating the stop button
        Button stopButton = new Button("stop");

        //Instantiating the HBox class
        HBox hbox = new HBox();

        //Setting the space between the nodes of a HBox pane
        hbox.setSpacing(10);

        //Adding all the nodes to the HBox
        hbox.getChildren().addAll(textField, playButton, stopButton);
        hbox.setAlignment( Pos.CENTER);

        //Setting the margin to the nodes
        hbox.setMargin(textField, new Insets(20, 20, 20, 20));
        hbox.setMargin(playButton, new Insets(20, 20, 20, 20));
        hbox.setMargin(stopButton, new Insets(20, 20, 20, 20));

        //Creating a scene object
        Scene scene = new Scene(hbox, 400, 300);

        //Setting title to the Stage
        stage.setTitle("Hbox Example in JavaFX");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
}
