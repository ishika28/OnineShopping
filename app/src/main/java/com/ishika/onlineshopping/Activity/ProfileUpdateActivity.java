package com.ishika.onlineshopping.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ishika.onlineshopping.BL.UpdateBL;
import com.ishika.onlineshopping.Model.User;
import com.ishika.onlineshopping.R;
import com.ishika.onlineshopping.URL.URL;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    CircleImageView pu_userImage;
    EditText pu_firstName,pu_lastName,pu_phone,pu_address,pu_email,pu_password;
    Button btn_camera,btn_upload,btn_update;

    String path="";
    String imageName;
    Bitmap image;

    private static final int PICK_IMAGE=1;
    private static final int CAMERA_REQUEST=2;

    ActionBar actionBar;
    Bundle bundle;

    SharedPreferences preferences;

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
        setContentView(R.layout.activity_profile_update);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Product Details");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3d5afe")));
        actionBar.setDisplayHomeAsUpEnabled(true);
        bundle = getIntent().getExtras();

        preferences = ProfileUpdateActivity.this.getSharedPreferences("loginPreference", Context.MODE_PRIVATE);
        String email = preferences.getString("email", "");

        pu_userImage=findViewById(R.id.pu_userImage);

        pu_firstName=findViewById(R.id.pu_firstName);
        pu_lastName=findViewById(R.id.pu_lastName);
        pu_phone=findViewById(R.id.pu_phone);
        pu_address=findViewById(R.id.pu_address);
        pu_email=findViewById(R.id.pu_email);
        pu_password=findViewById(R.id.pu_password);

        btn_camera=findViewById(R.id.pu_camera);
        btn_camera.setOnClickListener(this);

        btn_upload=findViewById(R.id.pu_upload);
        btn_upload.setOnClickListener(this);

        btn_update=findViewById(R.id.pu_update);
        btn_update.setOnClickListener(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {

            String url= URL.BASE_URL+"user-images/"+bundle.getString("userImageName");
            Picasso.with(getApplicationContext()).load(url).into(pu_userImage);
            pu_firstName.setText(bundle.getString("firstName"));
            pu_lastName.setText(bundle.getString("lastName"));
            pu_phone.setText(bundle.getString("phone"));
            pu_address.setText(bundle.getString("address"));
            pu_email.setText(email);
            pu_password.setText(bundle.getString("password"));
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(pu_firstName.getText().toString())) {
            pu_firstName.setError("Enter First Name");
            pu_firstName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pu_lastName.getText().toString())) {
            pu_lastName.setError("Enter Last Name");
            pu_lastName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pu_phone.getText().toString())) {
            pu_phone.setError("Enter Phone Number");
            pu_phone.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pu_address.getText().toString())) {
            pu_address.setError("Enter Address");
            pu_address.requestFocus();
            return false;
        }

        String passwordInput=pu_password.getText().toString().trim();

        if (passwordInput.isEmpty()){
            pu_password.setError("Field Can't Be Empty!");
            pu_password.requestFocus();
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            pu_password.setError("Password too weak!"+ "\n"+ "Minimum 7 characters including 1 special character (@#$%^&+=*)");
            pu_password.requestFocus();
            return false;
        }
        else {
            pu_password.setError(null);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    path = getImageFilePath(imageUri);
                    Log.e("Path :", path);
                    Bitmap selectedImage = BitmapFactory.decodeFile(path);
                    pu_userImage.setImageBitmap(selectedImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                onCaptureImage(data);
            }
        }
    }

    private void onCaptureImage(Intent data) {
        image = (Bitmap) data.getExtras().get("data");
        pu_userImage.setImageBitmap(image);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,100, bytes);
        String capturedPath = MediaStore.Images.Media.insertImage(getContentResolver(), image, "Title", null);
        Log.d("Captured path","----"+capturedPath);
        Uri uri = Uri.parse(capturedPath);

        path=getImageFilePath(uri);
        Log.e("Path of captured image","---"+path);
    }


    private String getImageFilePath(Uri uri) {
        String path = null;
        String image_id = null;

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            image_id = cursor.getString(0);
            image_id = image_id.substring(image_id.lastIndexOf(":") + 1);
            cursor.close();
        }

        Cursor mCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
                MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
            path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
            mCursor.close();
        }
        return path;
    }

    private void SaveImage() {
        if (path.isEmpty()) {
            imageName="defaultImage.png";
            return;
        }
        File file = new File(path);
        Log.e("Alright", path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part img = MultipartBody.Part.createFormData("profileImage", file.getName(), requestBody);
        UpdateBL registerAndUpdateLogic = new UpdateBL(img);
        StrictMode();
        imageName= registerAndUpdateLogic.uploadImage();
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void updateUser(){
        String fname=pu_firstName.getText().toString();
        String lname=pu_lastName.getText().toString();
        String phone=pu_phone.getText().toString();
        String address=pu_address.getText().toString();
        String email=pu_email.getText().toString();
        String password=pu_password.getText().toString();

        String user_type="user";

        final User user=new User(fname,lname,phone,address,email,password,imageName,user_type);

        UpdateBL updateLogic = new UpdateBL(email,user);
        StrictMode();
        if (updateLogic.updateUser()){
            Toasty.success(this, "Updated Successfully", Toasty.LENGTH_SHORT).show();
            Intent intent=new Intent(ProfileUpdateActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            Toasty.error(this,"Failed to update",Toasty.LENGTH_SHORT).show();
        }
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pu_camera:
                new MaterialDialog.Builder(ProfileUpdateActivity.this)
                        .title("Choose an action")
                        .items(R.array.itemId)
                        .items(R.array.uploadImage)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                                switch (position) {
                                    case 0:
                                        Intent imagePicker = new Intent(Intent.ACTION_PICK);
                                        imagePicker.setType("image/*");
                                        startActivityForResult(imagePicker, 1);
                                        break;

                                    case 1:
                                        Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(capture, 2);
                                        break;

                                    default:
                                        pu_userImage.setImageResource(R.drawable.userimg);
                                        break;

                                }
                            }
                        }).show();
                break;

            case R.id.pu_update:
                if (validate()) {
                    SaveImage();
                    updateUser();
                }
                finish();
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }
}
