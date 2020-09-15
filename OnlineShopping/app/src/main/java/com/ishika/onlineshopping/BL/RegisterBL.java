package com.ishika.onlineshopping.BL;

import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.Register;
import com.ishika.onlineshopping.Model.User;
import com.ishika.onlineshopping.URL.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBL {
    private String firstname,lastname,phone,address,email,password,userimagename,usertype;
    boolean isSuccess=false;

    public RegisterBL(String firstname, String lastname, String phone, String address, String email, String password, String userimagename, String usertype) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.userimagename = userimagename;
        this.usertype = usertype;
    }

    public boolean addUser(){
        Api api= URL.getInstance().create(Api.class);
        User user=new User(firstname,lastname,phone,address,email,password,userimagename,usertype);
        Call<Register> userCall=api.addUser(user);

        try {
            Response<Register> regresponse = userCall.execute();
            if (regresponse.body().getMessage_success()!= null) {
                isSuccess=true;
            }
            else{
                isSuccess=false;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }

}
