package org.lynxz.bitmapshaderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private String picUrl = "http://img5.duitang.com/uploads/item/201508/18/20150818125940_jwGKT.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivOnline = (ImageView) findViewById(R.id.civ_online);
        Glide.with(this)
                .load(picUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(ivOnline);
    }
}
