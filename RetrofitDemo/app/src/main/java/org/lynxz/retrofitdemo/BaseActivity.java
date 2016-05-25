package org.lynxz.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
