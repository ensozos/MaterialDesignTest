package com.zosimadis.ilias.materialdesign.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ilias on 5/8/2015.
 */
public class Movie implements Parcelable {
    private long id;
    private Date releaseDate;
    private int vote;
    private int popularity;
    private String language;
    private String title;
    private String overview;
    private String urlImage;


    public Movie() {

    }

    public Movie(Parcel parcel) {
        language = parcel.readString();
        title = parcel.readString();
        overview = parcel.readString();
        urlImage = parcel.readString();
        id = parcel.readLong();
        releaseDate = new Date(parcel.readLong());
        vote = parcel.readInt();
        popularity = parcel.readInt();

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(language);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(urlImage);
        parcel.writeLong(id);
        parcel.writeLong(releaseDate.getTime());
        parcel.writeInt(vote);
        parcel.writeInt(popularity);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
