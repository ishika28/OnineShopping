package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.AuthUser;
import com.ishika.onlineshopping.Model.User;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBL {
    private String email;
    private String password;
    private String message_1;
    String token;
    public static String sp_email;

    public LoginBL(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String checkUser(){
        Api api= URL.getInstance().create(Api.class);
        User user=new User("","","","",email,password,"","");
        Call<AuthUser> usersCall=api.getUser(user);

        try{
            Response<AuthUser> loginresponse=usersCall.execute();
            if(loginresponse.body().getToken()!=null){
                token = loginresponse.body().getToken();
                return token;
            }
            else if (loginresponse.body().getMessage()!=null){
                message_1=loginresponse.body().getMessage();
                return message_1;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "error";
    }
}
