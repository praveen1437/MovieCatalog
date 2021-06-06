package com.praveen.ratingdataservice.model;

import java.util.List;

public class UserRatings {
    List<Rating> ratings;

    public UserRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public UserRatings() {
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
