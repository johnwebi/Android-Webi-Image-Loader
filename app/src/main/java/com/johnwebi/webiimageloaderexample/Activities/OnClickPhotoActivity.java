package com.johnwebi.webiimageloaderexample.Activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.johnwebi.webiimageloaderexample.R;
import com.johnwebi.webiimageloaderexample.Utils.Utils;

public class OnClickPhotoActivity extends AppCompatActivity {
    String name = "";
    String created_at = "";
    int likes = 0;
    String url = "";
    String profileUrl = "";
    private ImageView photoImage, userProfileAvatar, backBtn;
    private TextView userName, createdAt, likeTv;
    private Button visitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclick_image);

        FillTheWholeScreen();
        BindViews();
        GetExtras();
        SetArticleTexts();
        SetArticleImage();
        OnBackBtnClick();

    }

    private void FillTheWholeScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    private void OnBackBtnClick() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void BindViews() {
        photoImage = findViewById(R.id.photoImageIv);
        backBtn = findViewById(R.id.backBtnIv);
        userProfileAvatar = findViewById(R.id.userProfileAvatarIv);
        userName = findViewById(R.id.userNameTv);
        createdAt = findViewById(R.id.createdAtTv);
        //visitBtn = findViewById(R.id.visitBtn);
        //likeTv = findViewById(R.id.likesTv);

    }

    private void GetExtras()
    {
        name = getIntent().getStringExtra("name");
        created_at = getIntent().getStringExtra("created_at");
        //likes = (int) getIntent().getIntExtra("likes");
        url = getIntent().getStringExtra("url");
        profileUrl = getIntent().getStringExtra("profile_url");
    }

    public void SetArticleTexts() {
        userName.setText(name);
        createdAt.setText( Utils.DateToTimeFormat(created_at));
        //likeTv.setText( likes);
    }

    public void SetArticleImage() {
        //Randomly set placeholder image photo
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(getApplicationContext())
                .load(url)
                .into(photoImage);

        Glide.with(getApplicationContext())
                .load(profileUrl)
                .into(userProfileAvatar);
    }
}