package com.example.dongja94.samplenavermovieopensource;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class MovieItem {
    String title;
    String link;
    String image;
    String director;
    String actor;
    float userRating;

    @Override
    public String toString() {
        return title + "(" + director + ")";
    }
}
