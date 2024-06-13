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
package com.demo.javafxexample.apps.bkcourse.tvlistapp.s4.start;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * @author carl
 */
public class TVProgram {

    private final StringProperty programName = new SimpleStringProperty("");
    private final IntegerProperty episodesRecorded = new SimpleIntegerProperty(0);
    private final ObjectProperty<LocalDate> lastRecording = new SimpleObjectProperty<>();

    public TVProgram(String programName, Integer episodesRecorded, LocalDate lastRecording) {
        setProgramName(programName);
        setEpisodesRecorded(episodesRecorded);
        setLastRecording(lastRecording);
    }

    public String getProgramName() {
        return programName.get();
    }

    public StringProperty programNameProperty() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName.set(programName);
    }

    public int getEpisodesRecorded() {
        return episodesRecorded.get();
    }

    public IntegerProperty episodesRecordedProperty() {
        return episodesRecorded;
    }

    public void setEpisodesRecorded(int episodesRecorded) {
        this.episodesRecorded.set(episodesRecorded);
    }

    public LocalDate getLastRecording() {
        return lastRecording.get();
    }

    public ObjectProperty<LocalDate> lastRecordingProperty() {
        return lastRecording;
    }

    public void setLastRecording(LocalDate lastRecording) {
        this.lastRecording.set(lastRecording);
    }
}