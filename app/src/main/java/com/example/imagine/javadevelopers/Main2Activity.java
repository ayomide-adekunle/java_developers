package com.example.imagine.javadevelopers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Get the bundle
        Bundle bundle = getIntent().getExtras();



        //Extract the data…
        String img_url = bundle.getString("img_url");
        final String username = bundle.getString("user_name");
        final String user_url = bundle.getString("user_url");


        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(getApplicationContext()).load(img_url).into(imageView);


        TextView txtName = (TextView) findViewById(R.id.username);
        TextView userUrl= (TextView) findViewById(R.id.profileUrl);
        txtName.setText(username);
        userUrl.setText(user_url);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Check out this awesome developer @ "+username+" , "+user_url);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

}
