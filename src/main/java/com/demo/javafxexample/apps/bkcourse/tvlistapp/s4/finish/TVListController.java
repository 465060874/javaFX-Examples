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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.time.Month;

/**
 * Sole JavaFX for TVListApp
 *
 * @author carl
 */
public class TVListController {

    @FXML
    ListView<TVProgram> lvPrograms;

    @FXML
    Label lblStatus;

    @FXML
    public void initialize() {

        lvPrograms.setCellFactory( (lv) -> new TVProgramListCell() );

        lblStatus.setText("");
    }

    public void load() {

        //
        // Sample data
        //

        TVProgram[] programs = {
                new TVProgram("As the TableView Turns", 4, LocalDate.of(2016, Month.JULY, 2)),
                new TVProgram("The Walking Stage", 1, LocalDate.of(2016, Month.JUNE, 28)),
                new TVProgram("The Real Nodes of JavaFX", 12, LocalDate.of(2016, Month.JUNE, 20)),
                new TVProgram("Big Spinner After Dark", 1, LocalDate.of(2016, Month.JUNE, 15))
        };

        lvPrograms.getItems().addAll( programs );

    }

}
