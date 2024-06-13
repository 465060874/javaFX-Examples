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
package com.demo.javafxexample.apps.bkcourse.tvlistapp.s5.start;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

/**
 * Custom ListCell for visualizating TVProgram domain objects
 *
 * @author carl
 */
public class TVProgramListCell extends ListCell<TVProgram> {

    private Label programNameLabel = new Label("");
    private Label lastRecordingLabel = new Label("");

    public TVProgramListCell() {

        programNameLabel.getStyleClass().add( "tvlist-program-name" );
        lastRecordingLabel.getStyleClass().add( "tvlist-last-recording" );

        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        VBox vbox = new VBox();
        vbox.getStyleClass().add( "tvlist-cell-container" );
        vbox.getChildren().addAll( programNameLabel );
        setGraphic( vbox );
    }

    @Override
    public void updateItem(TVProgram item, boolean empty) {

        super.updateItem(item,empty);

        if( item != null && !empty ) {

            if( this.getStyle().contains("tvlist-list-cell-empty") ) {
                this.getStyleClass().remove("tvlist-list-cell-empty");
            }

            if( !this.getStyleClass().contains("tvlist-list-cell" ) ) {
                this.getStyleClass().add( "tvlist-list-cell" );
            }

            programNameLabel.setText( formatProgramName(item) );
            lastRecordingLabel.setText( formatLastRecording(item) );
        } else {

            if( !this.getStyle().contains("tvlist-list-cell-empty") ) {
                this.getStyleClass().add("tvlist-list-cell-empty");
            }

            if( this.getStyleClass().contains("tvlist-list-cell" ) ) {
                this.getStyleClass().remove( "tvlist-list-cell" );
            }

        }
    }

    private String formatProgramName(TVProgram item ) {
        String format = "%s (%d)";
        return String.format(format, item.getProgramName(), item.getEpisodesRecorded());
    }

    private String formatLastRecording(TVProgram item) {
        return "Recordings added " + item.getLastRecording().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }
}