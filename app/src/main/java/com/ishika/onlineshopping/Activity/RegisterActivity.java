package com.ishika.onlineshopping.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ishika.onlineshopping.BL.RegisterBL;
import com.ishika.onlineshopping.R;

import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText ra_firstName,ra_lastName,ra_phone,ra_address,ra_email,ra_password;
    private String ra_userimagename,ra_usertype;
    private Button btnRegister;
    private TextView ra_tvLogin;

    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=*])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{7,}" +               //at least 7 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        ra_firstName=findViewById(R.id.ra_firstName);
        ra_lastName=findViewById(R.id.ra_lastName);
        ra_phone=findViewById(R.id.ra_phone);
        ra_address=findViewById(R.id.ra_address);
        ra_email=findViewById(R.id.ra_email);
        ra_password=findViewById(R.id.ra_password);

        ra_userimagename="userimg.jpeg";
        ra_usertype="user";

        btnRegister=findViewById(R.id.ra_btnRegister);
        btnRegister.setOnClickListener(this);

        ra_tvLogin=findViewById(R.id.ra_tvLogin);
        ra_tvLogin.setOnClickListener(this);
    }

    private boolean validateEmail(){
        String emailInput=ra_email.getText().toString().trim();

        if (emailInput.isEmpty()){
            ra_email.setError("Field Can't Be Empty!");
            ra_email.requestFocus();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            ra_email.setError("Please enter a valid email address!");
            ra_email.requestFocus();
            return false;
        }
        else {
            ra_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput=ra_password.getText().toString().trim();

        if (passwordInput.isEmpty()){
            ra_password.setError("Field Can't Be Empty!");
            ra_password.requestFocus();
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            ra_password.setError("Password too weak!"+ "\n"+ "Minimum 7 characters including 1 special character (@#$%^&+=*)");
            ra_password.requestFocus();
            return false;
        }
        else {
            ra_password.setError(null);
            return true;
        }
    }

    private boolean validateFirstName(){
        String firstNameInput=ra_firstName.getText().toString().trim();

        if (firstNameInput.isEmpty()){
            ra_firstName.setError("Field Can't Be Empty!");
            ra_firstName.requestFocus();
            return false;
        }
        else {
            ra_firstName.setError(null);
            return true;
        }
    }

    private boolean validateLastName(){
        String lastNameInput=ra_lastName.getText().toString().trim();

        if (lastNameInput.isEmpty()){
            ra_lastName.setError("Field Can't Be Empty!");
            ra_lastName.requestFocus();
            return false;
        }
        else {
            ra_lastName.setError(null);
            return true;
        }
    }

    private boolean validatePhone(){
        String phoneeInput=ra_phone.getText().toString().trim();

        if (phoneeInput.isEmpty()){
            ra_phone.setError("Field Can't Be Empty!");
            ra_phone.requestFocus();
            return false;
        }
        else {
            ra_phone.setError(null);
            return true;
        }
    }

    private boolean validateAddress(){
        String addressInput=ra_address.getText().toString().trim();

        if (addressInput.isEmpty()){
            ra_address.setError("Field Can't Be Empty!");
            ra_address.requestFocus();
            return false;
        }
        else {
            ra_address.setError(null);
            return true;
        }
    }


    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void addUser(){
        String firstName=ra_firstName.getText().toString();
        String lastName=ra_lastName.getText().toString();
        String phone=ra_phone.getText().toString();
        String address=ra_address.getText().toString();
        String email=ra_email.getText().toString();
        String password=ra_password.getText().toString();
        String userimagename="userimg.jpeg";
        String usertype="user";
        final RegisterBL registerBL=new RegisterBL(firstName,lastName,phone,address,email,password,userimagename,usertype);
        StrictMode();

        if (registerBL.addUser()){
            Toasty.success(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else{
            Toasty.warning(RegisterActivity.this,"Email already in use or registration failure",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ra_btnRegister:
                if (validateFirstName() && validateLastName() &&  validatePhone() &&  validateAddress() && validateEmail() && validatePassword()){
                    addUser();
                }
                break;

            case R.id.ra_tvLogin:
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}