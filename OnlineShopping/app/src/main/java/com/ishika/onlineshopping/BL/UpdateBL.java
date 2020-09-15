package com.ishika.onlineshopping.BL;

import android.util.Log;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.User;
import com.ishika.onlineshopping.Model.UserImg;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;

public class UpdateBL {
    MultipartBody.Part image;
    String email;
    User user;
    String imageName;
    Api api = URL.getInstance().create(Api.class);

    public UpdateBL(MultipartBody.Part image) {
        this.image = image;
    }

    public UpdateBL(String email) {
        this.email = email;
    }

    public UpdateBL(User user) {
        this.user = user;
    }

    public UpdateBL(String email, User user) {
        this.email = email;
        this.user = user;
    }

    public String uploadImage(){
        Call<UserImg> itemCall = api.uploadImage(image);
        try {
            Response<UserImg> imgResponse = itemCall.execute();
            imageName = imgResponse.body().getImageFile();
        } catch (IOException e) {
            Log.e("Error is:", e.getMessage());

            e.printStackTrace();
        }
        return imageName;
    }

    public boolean updateUser(){
        Call<Boolean> updateUserCall = api.updateUser(email,user);
        try{
            Response<Boolean> updateResponse = updateUserCall.execute();
            return  updateResponse.body().booleanValue();
        }catch (IOException e){
            Log.e("Error",e.getMessage());
        }
        return false;
    }
}
