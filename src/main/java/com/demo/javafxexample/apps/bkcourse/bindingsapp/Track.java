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
package com.demo.javafxexample.apps.bkcourse.bindingsapp;

/**
 * @author carl
 */
public class Track {

    private String artist;
    private String album;
    private String track;
    private Integer trackNo;
    private Rating rating;
    private Boolean downloaded;

    public Track(String artist,
                 String album,
                 String track,
                 Integer trackNo,
                 Rating rating,
                 Boolean downloaded) {
        this.artist = artist;
        this.album = album;
        this.track = track;
        this.trackNo = trackNo;
        this.rating = rating;
        this.downloaded = downloaded;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public Integer getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(Integer trackNo) {
        this.trackNo = trackNo;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Boolean getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Boolean downloaded) {
        this.downloaded = downloaded;
    }


    @Override
    public String toString() {
        return "Track{" +
                "artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", track='" + track + '\'' +
                ", trackNo=" + trackNo +
                ", rating=" + rating +
                ", downloaded=" + downloaded +
                '}';
    }
}
