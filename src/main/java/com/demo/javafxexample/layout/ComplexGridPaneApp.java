package com.demo.javafxexample.layout;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class ComplexGridPaneApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vbox = new VBox();

        GridPane gp = new GridPane();
        gp.setPadding( new Insets(10) );
        gp.setHgap( 4 );
        gp.setVgap( 10 );

        VBox.setVgrow(gp, Priority.ALWAYS );

        Label lblTitle = new Label("Support Ticket");

        Label lblEmail = new Label("Email");
        TextField tfEmail = new TextField();

        Label lblContract = new Label("Contract");
        TextField tfContract = new TextField();

        Label lblPriority = new Label("Priority");
        ObservableList<String> priorities =
                FXCollections.observableArrayList("Medium", "High", "Low");
        ComboBox<String> cbPriority = new ComboBox<>(priorities);

        Label lblSeverity = new Label("Severity");
        ObservableList<String> severities = FXCollections.observableArrayList("Blocker", "Workaround", "N/A");
        ComboBox<String> cbSeverity = new ComboBox<>(severities);

        Label lblCategory = new Label("Category");
        ObservableList<String> categories = FXCollections.observableArrayList("Bug", "Feature");
        ComboBox<String> cbCategory = new ComboBox<>(categories);

        Label lblProblem = new Label("Problem");
        TextField tfProblem = new TextField();

        Label lblDescription = new Label("Description");
        TextArea taDescription = new TextArea();

        gp.add( lblTitle,       1, 0);  // empty item at 0,0

        gp.add( lblEmail,       0, 1);
        gp.add(tfEmail,         1, 1);
        gp.add( lblContract,    4, 1 );
        gp.add( tfContract,     5, 1 );

        gp.add( lblPriority,    0, 2);
        gp.add( cbPriority,     1, 2);
        gp.add( lblSeverity,    2, 2);
        gp.add( cbSeverity,     3, 2);
        gp.add( lblCategory,    4, 2);
        gp.add( cbCategory,     5, 2);

        gp.add( lblProblem,     0, 3); gp.add( tfProblem,     1, 3);
        gp.add( lblDescription, 0, 4); gp.add( taDescription, 1, 4);

        GridPane.setColumnSpan( lblTitle, 5 );
        GridPane.setColumnSpan( tfEmail, 3 );
        GridPane.setColumnSpan( tfProblem, 5 );
        GridPane.setColumnSpan( taDescription, 5 );

        Separator sep = new Separator(); // hr

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding( new Insets(10) );

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        buttonBar.setButtonData(saveButton, ButtonBar.ButtonData.OK_DONE);
        buttonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);

        buttonBar.getButtons().addAll(saveButton, cancelButton);

        vbox.getChildren().addAll( gp, sep, buttonBar );

        Scene scene = new Scene(vbox);

        primaryStage.setTitle("Grid Pane App");
        primaryStage.setScene(scene);
        primaryStage.setWidth( 736 );
        primaryStage.setHeight( 414  );
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
