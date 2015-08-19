package com.zosimadis.ilias.materialdesign.Pojo;

import java.util.Date;

/**
 * Created by ilias on 5/8/2015.
 */
public class Movie {
    private long id;
    private String language;
    private String title;
    private Date releaseDate;
    private int vote;
    private String overview;
    private String urlImage;
    private int popularity;

    public Movie() {

    }

    public Movie(long id, String lunguage, String title, Date releaseDate, int vote, String overview, String urlImage, int popularity) {

        this.id = id;
        this.language = lunguage;
        this.popularity = popularity;
        this.title = title;
        this.releaseDate = releaseDate;
        this.vote = vote;
        this.overview = overview;
        this.urlImage = urlImage;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public long getId() {
        return id;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getVote() {
        return vote;
    }

    public String getLanguage() {
        return language;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getUrlImage() {
        return urlImage;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "LANG: " + language +
                "Title: " + title +
                "Overview: " + overview +
                "Release: " + releaseDate +
                "Vote: " + vote +
                "Popularity+ " + popularity;
    }
}
