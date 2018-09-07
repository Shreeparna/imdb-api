package model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ImdbMovie {
    int id;

    @SerializedName("Title")
    String movie_title;

    @SerializedName("Year")
    int year;

    @SerializedName("Released")
    Date released;

    @SerializedName("Runtime")
    String runtime;

    @SerializedName("Genre")
    String genre;

    @SerializedName("Director")
    String director;

    @SerializedName("Language")
    String language;

    @SerializedName("Country")
    String country;

    @SerializedName("imdbRating")
    double imdbRating;

    @SerializedName("imdbID")
    String imdbId;

    @SerializedName("Type")
    String type;

    @SerializedName("Production")
    String production;

    public ImdbMovie(int id, String movie_title, int year, Date released, String runtime, String genre, String director, String language, String country, double imdbRating, String imdbId, String type, String production) {
        this.id = id;
        this.movie_title = movie_title;
        this.year = year;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.language = language;
        this.country = country;
        this.imdbRating = imdbRating;
        this.imdbId = imdbId;
        this.type = type;
        this.production = production;
    }

    public ImdbMovie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public String toString(){
        return this.movie_title+" - "+this.released;
    }
}
