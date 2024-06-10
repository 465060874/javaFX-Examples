package com.demo.javafxexample.layout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class VBoxExample extends Application {
    @Override
    public void start(Stage stage) {
        //creating a text field
        TextField textField = new TextField();

        //Creating the play button
        Button playButton = new Button("Play");

        //Creating the stop button
        Button stopButton = new Button("stop");

        //Instantiating the VBox class
        VBox box = new VBox();

        //Setting the space between the nodes of a HBox pane
        box.setSpacing(10);

        //Adding all the nodes to the VBox
        box.getChildren().addAll(textField, playButton, stopButton);

        //Setting the margin to the nodes
        box.setMargin(textField, new Insets(20, 20, 20, 20));
        box.setMargin(playButton, new Insets(20, 20, 20, 20));
        box.setMargin(stopButton, new Insets(20, 20, 20, 20));

        //Creating a scene object
        Scene scene = new Scene(box, 400, 300);

        //Setting title to the Stage
        stage.setTitle("Vbox Example in JavaFX");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
