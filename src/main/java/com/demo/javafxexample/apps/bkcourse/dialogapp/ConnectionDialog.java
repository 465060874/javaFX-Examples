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

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * @author carl
 */
public class ConnectionDialog extends Dialog<ConnectionInfo> {

    private final TextField tfHost = new TextField();
    private final TextField tfUser = new TextField();
    private final TextField tfPassword = new TextField();

    public ConnectionDialog(ConnectionInfo initialData) {

        Label hostLabel = new Label("Host");
        Label userLabel = new Label("User");
        Label passwordLabel = new Label("Password");

        VBox vbox = new VBox(
                hostLabel, tfHost,
                userLabel, tfUser,
                passwordLabel, tfPassword
        );

        vbox.setSpacing( 10.0d );
        vbox.setPadding( new Insets(40.0d) );

        DialogPane dp = getDialogPane();

        setTitle( "Connection Info" );
        setResultConverter( this::formResult );

        ButtonType bt = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dp.getButtonTypes().addAll( bt, ButtonType.CANCEL );
        dp.setContent( vbox );

        init( initialData );
    }

    private ConnectionInfo formResult(ButtonType bt) {
        ConnectionInfo retval = null;
        if( bt.getButtonData() == ButtonBar.ButtonData.OK_DONE ) {
            retval = new ConnectionInfo(
                    tfHost.getText(), tfUser.getText(), tfPassword.getText()
            );
        }
        return retval;
    }

    private void init(ConnectionInfo ci) {
        if (ci != null) {
            tfHost.setText( ci.getHost() );
            tfUser.setText( ci.getUsername() );
            tfPassword.setText( ci.getPassword() );
        }
    }
}
