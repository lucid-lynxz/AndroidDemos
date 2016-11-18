package lynxz.org.recyclerviewdemo;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by Lynxz on 2016/11/18.
 * description :
 */

public class Demo extends Activity{
    public void test(){
        TextView textView = new TextView(this);
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },1000);
    }
}
