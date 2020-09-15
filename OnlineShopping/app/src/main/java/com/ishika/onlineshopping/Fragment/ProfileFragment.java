package com.ishika.onlineshopping.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ishika.onlineshopping.Activity.AboutUsActivity;
import com.ishika.onlineshopping.Activity.LoginActivity;
import com.ishika.onlineshopping.Activity.OrderHistoryActivity;
import com.ishika.onlineshopping.Activity.ProfileUpdateActivity;
import com.ishika.onlineshopping.Interface.Api;
import com.ishika.onlineshopping.Model.Register;
import com.ishika.onlineshopping.Model.User;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    CircleImageView pu_userImage;
    TextView tv_fullname;
    Button btn_history, btn_editProfile, btn_feedback,btn_aboutUs, btn_logout;

    User user;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Api api;

    String firstName,lastName,phone,address,email,password,userimagename,usertype;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        api = URL.getInstance().create(Api.class);

        preferences = getContext().getSharedPreferences("loginPreference", Context.MODE_PRIVATE);
        editor = preferences.edit();

        pu_userImage = view.findViewById(R.id.pf_userimg);
        tv_fullname = view.findViewById(R.id.profile_name);

        btn_editProfile = view.findViewById(R.id.btn_editProfile);
        btn_editProfile.setOnClickListener(this);

        btn_history = view.findViewById(R.id.btn_viewHistory);
        btn_history.setOnClickListener(this);

        btn_feedback = view.findViewById(R.id.btn_feedback );
        btn_feedback .setOnClickListener(this);

        btn_aboutUs = view.findViewById(R.id.btn_aboutUs);
        btn_aboutUs.setOnClickListener(this);

        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);


        getProfile();

        firstName=user.getFirstname();
        lastName=user.getLastname();
        phone=user.getPhone();
        address=user.getAddress();
        email=user.getEmail();
        password=user.getPassword();
        userimagename=user.getUserimagename();

        String url= URL.BASE_URL+"user-images/"+userimagename;
        Picasso.with(getContext()).load(url).into(pu_userImage);
        usertype=user.getUsertype();

        tv_fullname.setText(firstName+" "+lastName);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_editProfile:
                Intent intent1 = new Intent(getContext(), ProfileUpdateActivity.class);
                intent1.putExtra("firstName",user.getFirstname());
                intent1.putExtra("lastName",user.getLastname());
                intent1.putExtra("phone",user.getPhone());
                intent1.putExtra("address",user.getAddress());
                intent1.putExtra("email",user.getEmail());
                intent1.putExtra("password",user.getPassword());
                intent1.putExtra("userImageName",user.getUserimagename());
                intent1.putExtra("userType",user.getUsertype());
                intent1.addFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;

            case R.id.btn_viewHistory:
                Intent intent2 = new Intent(getContext(), OrderHistoryActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_feedback:
                Intent feedbackIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"isupradhan@gmail.com"));
                feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                startActivity(feedbackIntent);
                break;

            case R.id.btn_aboutUs:
                Intent intent4 = new Intent(getContext(), AboutUsActivity.class);
                startActivity(intent4);
                break;

            case R.id.btn_logout:
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("TechCenter")
                        .setMessage("Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String token = preferences.getString("token","");
                                Call<Register> logoutCall = api.logout(token);
                                logoutCall.enqueue(new Callback<Register>() {
                                    @Override
                                    public void onResponse(Call<Register> call, Response<Register> response) {
                                        if (!response.isSuccessful()) {
                                            Toasty.error(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                                            Log.d("The error is:",response.message());
                                        } else {
                                            String logoutResponse = response.body().getMessage();
                                            if (logoutResponse.equals("Success")) {
                                                editor.clear().commit();
                                                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                Toasty.success(getActivity().getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                                                getActivity().finish();
                                            } else {
                                                Toasty.error(getActivity().getApplicationContext(), "Failed to logout", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Register> call, Throwable t) {
                                        Toasty.error(getActivity().getApplicationContext(),"Error getting response",Toast.LENGTH_SHORT).show();
                                        Log.d("Logout Error",t.getMessage());
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                break;

        }
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void getProfile() {
        String token = preferences.getString("token","");

        Call<User> listCall = api.getProfile(token);
        StrictMode();
        try {
            Response<User> userResponse = listCall.execute();
            user = userResponse.body();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            Log.e("Error is:", e.getMessage());

            e.printStackTrace();
        }

    }
}
