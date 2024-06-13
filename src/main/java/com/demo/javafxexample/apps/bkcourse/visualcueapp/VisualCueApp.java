package com.demo.javafxexample.apps.bkcourse.visualcueapp;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import java.awt.*;

/**
 * Created by carl_000 on 11/7/2016.
 */
public class VisualCueApp extends Application {

    private CheckMenuItem visualItem = new CheckMenuItem("Visual Cues");
    private CheckMenuItem audibleItem = new CheckMenuItem("Audible Cues");
    private Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        VBox vbox = new VBox();

        MenuBar menubar = new MenuBar();
        Menu appMenu = new Menu("App");
        audibleItem.setSelected(true);
        appMenu.getItems().addAll( visualItem, audibleItem );
        menubar.getMenus().add( appMenu );

        VBox content = new VBox();
        content.setAlignment( Pos.CENTER_LEFT );
        content.setPadding( new Insets(40) );
        content.setSpacing( 10 );

        VBox.setVgrow(content, Priority.ALWAYS);

        Label label = new Label("Just Numbers");
        TextField tf = new TextField();

        TextFormatter<Integer> formatter = new TextFormatter<>(
                new IntegerStringConverter(),
                null,
                (change) ->  {
                        String text = change.getText();
                        for (int i = 0; i < text.length(); i++) {
                            if (!Character.isDigit(text.charAt(i)))
                                return null;
                        }
                        return change;

                    }
                );

        tf.setTextFormatter(formatter);
        tf.addEventFilter(KeyEvent.KEY_PRESSED, keyEventHandler);

        Button b = new Button("Submit");
        b.setOnAction( (evt) -> System.out.println( formatter.getValue() ));

        content.getChildren().addAll( label, tf, b );

        vbox.getChildren().addAll( menubar, content );

        Scene scene = new Scene( vbox );

        primaryStage.setTitle("Visual Cue App");
        primaryStage.setScene( scene );
        primaryStage.setWidth(480);
        primaryStage.setHeight(320);

        primaryStage.show();
    }

    private EventHandler<KeyEvent> keyEventHandler = (evt) -> {

        if( !evt.getCode().isDigitKey() &&
                !evt.getCode().isArrowKey() &&
                !evt.getCode().isFunctionKey() &&
                !evt.getCode().isModifierKey() &&
                !evt.getCode().equals(KeyCode.BACK_SPACE) &&
                !evt.getCode().equals(KeyCode.DELETE) &&
                !evt.getCode().equals(KeyCode.TAB) &&
                !evt.getCode().equals(KeyCode.ENTER) &&
                !evt.isControlDown()) {

            if( audibleItem.isSelected() ) {
                giveAudibleCue();
            }
            if( visualItem.isSelected() ) {
                giveVisualCue();
            }
        }
    };

    private void giveVisualCue() {

        if( stage != null ) {

            final Timeline timeline = new Timeline();

            final KeyValue kv = new KeyValue(stage.opacityProperty(), 0.7f);
            final KeyFrame kf = new KeyFrame(Duration.millis(300), kv);

            timeline.getKeyFrames().add( kf );
            timeline.setOnFinished( (evt) -> stage.setOpacity(1.0f) );

            timeline.play();
        }
    }

    private void giveAudibleCue() {
        if (isLinux()) {
            ubuntuBeep();
        } else {
            Platform.runLater(() -> Toolkit.getDefaultToolkit().beep() );
        }
    }

    private void ubuntuBeep() {
        try {
            AudioClip beepSound = new AudioClip(
                    this.getClass().getResource("/visualcueapp/KDE-Sys-App-Error.wav").toString()
            );
            beepSound.play();
        } catch(Exception ignore) {}
    }

    private boolean isLinux() {
        String osName = System.getProperty("os.name");
        return osName != null && osName.startsWith("Linux");
    }

    public static void main(String[] args) { launch(args); }
}
