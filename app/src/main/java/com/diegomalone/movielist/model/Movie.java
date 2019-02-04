package com.diegomalone.movielist.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Movie implements Parcelable {

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String synopsis;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String backgroundPhoto;

    @SerializedName("vote_average")
    private double userRating;

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackgroundPhoto() {
        return backgroundPhoto;
    }

    public void setBackgroundPhoto(String backgroundPhoto) {
        this.backgroundPhoto = backgroundPhoto;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public String getFormattedUserRating() {
        return getDecimalFormat().format(getUserRating());
    }

    private static DecimalFormat getDecimalFormat() {
        Locale enLocale = new Locale("en", "US");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(enLocale);
        decimalFormat.applyPattern("0.0");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);

        return decimalFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Double.compare(movie.userRating, userRating) == 0 &&
                Objects.equals(title, movie.title) &&
                Objects.equals(synopsis, movie.synopsis) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(poster, movie.poster) &&
                Objects.equals(backgroundPhoto, movie.backgroundPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, synopsis, releaseDate, poster, backgroundPhoto, userRating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.synopsis);
        dest.writeString(this.releaseDate);
        dest.writeString(this.poster);
        dest.writeString(this.backgroundPhoto);
        dest.writeDouble(this.userRating);
    }

    protected Movie(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.synopsis = in.readString();
        this.releaseDate = in.readString();
        this.poster = in.readString();
        this.backgroundPhoto = in.readString();
        this.userRating = in.readDouble();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
