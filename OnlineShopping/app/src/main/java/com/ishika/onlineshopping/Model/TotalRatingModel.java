package com.ishika.onlineshopping.Model;

import java.util.List;

public class TotalRatingModel {
    private int count;
    private List<Rating> ratings;

    public TotalRatingModel(int count, List<Rating> ratings) {
        this.count = count;
        this.ratings = ratings;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
