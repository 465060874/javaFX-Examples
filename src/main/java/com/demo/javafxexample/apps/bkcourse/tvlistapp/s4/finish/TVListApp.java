/*
 * Copyright 2016 Bekwam, Inc
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
package com.demo.javafxexample.apps.bkcourse.tvlistapp.s4.finish;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for TVListApp
 *
 * @author carl
 */
public class TVListApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader( TVListApp.class.getResource("/s4/finish/TVList.fxml") );

        Parent p = fxmlLoader.load();

        TVListController c = fxmlLoader.getController();

        Scene scene = new Scene(p, 1024, 768);
        scene.getStylesheets().add("s4/finish/tvlist.css");

        primaryStage.setTitle( "TVListApp" );
        primaryStage.setScene( scene );
        primaryStage.setOnShown( (evt) -> c.load() );
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
