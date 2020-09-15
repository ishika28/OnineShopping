package com.ishika.onlineshopping.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ishika.onlineshopping.BL.LoginBL;
import com.ishika.onlineshopping.R;

import es.dmoral.toasty.Toasty;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText loginEmail,loginPassword;
    private Button btnLogin;
    private TextView la_tvSignUp;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        preferences = getSharedPreferences("loginPreference",MODE_PRIVATE);
        editor = preferences.edit();

        if (preferences.getBoolean("loginStatus",false)) {
            String token = preferences.getString("token","");
            if (token != "") {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }

        loginEmail=findViewById(R.id.la_email);
        loginPassword=findViewById(R.id.la_password);

        btnLogin=findViewById(R.id.la_btnLogin);
        btnLogin.setOnClickListener(this);

        la_tvSignUp=findViewById(R.id.la_tvSignUp);
        la_tvSignUp.setOnClickListener(this);


    }

    private boolean validateEmail(){
        String emailInput=loginEmail.getText().toString().trim();

        if (emailInput.isEmpty()){
            loginEmail.setError("Please enter your email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            loginEmail.setError("Please enter a valid email address!");
            return false;
        }
        else {
            loginEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput=loginPassword.getText().toString().trim();

        if (passwordInput.isEmpty()){
            loginPassword.setError("Please enter your password!");
            return false;
        }
        else {
            loginPassword.setError(null);
            return true;
        }
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.la_btnLogin:
                if (validateEmail() && validatePassword()){
                        final LoginBL loginBL=new LoginBL(loginEmail.getText().toString(),loginPassword.getText().toString());
                        StrictMode();

                        if(loginBL.checkUser().equals("Invalid")){
                            Toasty.warning(LoginActivity.this,"Invalid credentials", Toast.LENGTH_SHORT).show();
                            vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
                            vibrator.vibrate(200);
                        } else if (loginBL.checkUser().equals("error")){
                            Toasty.error(LoginActivity.this,"Error during login",Toast.LENGTH_SHORT).show();
                            vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
                            vibrator.vibrate(200);
                        }
                        else {
                            Toasty.success(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();
                            final Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_NEW_TASK);
                            editor.putString("email",loginEmail.getText().toString())
                                    .putBoolean("loginStatus",true)
                                    .putString("token",loginBL.checkUser())
                                    .apply();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(intent);
                                    finish();
                                }
                            }, 2000);

                        }
                    }
                    break;

            case R.id.la_tvSignUp:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
}
}
