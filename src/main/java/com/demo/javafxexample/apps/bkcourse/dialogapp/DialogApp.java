/*
 * Copyright 2017 Bekwam, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.demo.javafxexample.apps.bkcourse.dialogapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.Optional;

/**
 * App to demonstrate use of JavaFX Dialog class
 *
 * @author carl
 */
public class DialogApp extends Application {

    private final TextField dbURL = new TextField();

    private final ConnectionInfoStringConverter ciConverter =
            new ConnectionInfoStringConverter();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("DB URL");
        dbURL.setPrefWidth(400.0d );
        Button btn = new Button("Set");
        btn.setOnAction( this::showSetDialog );

        VBox vbox = new VBox(label, dbURL, btn );
        vbox.setSpacing( 10.0d );
        vbox.setPadding( new Insets(40.0d) );

        Scene scene = new Scene( vbox );

        primaryStage.setTitle("Dialog App");
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void showSetDialog(ActionEvent evt) {

        ConnectionInfo fromURL = ciConverter.fromString( dbURL.getText() );

        ConnectionDialog dialog = new ConnectionDialog(fromURL);

        Optional<ConnectionInfo> ci = dialog.showAndWait();

        ci.ifPresent( c -> dbURL.setText(
                ciConverter.toString(c)
            )
        );
    }

    class ConnectionInfoStringConverter extends StringConverter<ConnectionInfo> {

        private final String format = "%s@%s:%s";

        @Override
        public String toString(ConnectionInfo c) {
            return String.format( format, c.getUsername(), c.getPassword(), c.getHost() );
        }

        @Override
        public ConnectionInfo fromString(String s) {

            if( s != null && s.contains("@") && s.contains(":") ) {
                String[] toks = s.split("@");
                String username = toks[0];
                String[] secondPart = toks[1].split(":");
                String password = secondPart[0];
                String host = secondPart[1];
                ConnectionInfo ci = new ConnectionInfo(
                        username, password, host
                );
                return ci;
            }

            return null;
        }
    }
}
