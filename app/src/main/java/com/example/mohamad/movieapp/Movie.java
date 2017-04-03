package com.example.mohamad.movieapp;


import java.util.ArrayList;
import java.util.List;

public class Movie {

    private List<Movie> movies = new ArrayList<Movie>();

    private String title;
    private Double vote_average;
    private String release_date;
    private String poster_path;
    private String overview;
    private Integer vote_count;
    private Integer id;
    private Boolean video;

    public Movie(List<Movie> results) {
        this.movies = results;
    }

    public Movie() {

    }

    public Double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(Double voteAverage) {
        this.vote_average = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public Integer getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(Integer voteCount) {
        this.vote_count = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }
}

