package com.zosimadis.ilias.materialdesign.Pojo;

import java.util.Date;

/**
 * Created by ilias on 5/8/2015.
 */
public class Movie {
    private int id;
    private String lunguage;
    private String title;
    private Date releaseDate;
    private int vote;
    private String overview;
    private String urlImage;

    public Movie() {

    }

    public Movie(int id, String lunguage, String title, Date releaseDate, int vote, String overview, String urlImage) {

        this.id = id;
        this.lunguage = lunguage;
        this.title = title;
        this.releaseDate = releaseDate;
        this.vote = vote;
        this.overview = overview;
        this.urlImage = urlImage;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLunguage(String lunguage) {
        this.lunguage = lunguage;
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

    public int getId() {
        return id;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getVote() {
        return vote;
    }

    public String getLunguage() {
        return lunguage;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
