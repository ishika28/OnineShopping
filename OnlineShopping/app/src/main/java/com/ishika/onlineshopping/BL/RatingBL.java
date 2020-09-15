package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.Rating;
import com.ishika.onlineshopping.Model.Register;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RatingBL {
    Api api = URL.getInstance().create(Api.class);

    public String addRating(Rating rating) {

        Call<Register> addNewRating = api.addRating(rating);
        String message="invalid";

        try {
            Response<Register> addResponse = addNewRating.execute();
            if (addResponse.isSuccessful()) {
                message = addResponse.body().getMessage_success();

                return message;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return message;
    }

    public List<Rating> getMyRating(Rating rating) {
        List<Rating> ratingList = new ArrayList<>();
        Call<List<Rating>> getMyRating = api.getMyRating(rating);
        try {
            Response<List<Rating>> ratingResponse = getMyRating.execute();
            if (ratingResponse.isSuccessful()) {
                ratingList = ratingResponse.body();
                return ratingList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ratingList;
    }

    public String updateRating(String id,Rating rating) {
        String message = "";
        Call<Register> updateCall = api.updateRating(id,rating);
        try {
            Response<Register> updateResponse = updateCall.execute();
            if (updateResponse.isSuccessful()) {
                message = updateResponse.body().getMessage_success();
                return message;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
