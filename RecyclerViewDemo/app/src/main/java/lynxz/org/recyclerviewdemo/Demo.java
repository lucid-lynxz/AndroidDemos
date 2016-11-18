package lynxz.org.recyclerviewdemo;

import android.app.Activity;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Lynxz on 2016/11/18.
 * description :
 */

public class Demo extends Activity {
    public void test() {
        TextView textView = new TextView(this);
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);

        List<String> strs = new ArrayList<>();
        strs.add("hello");
        strs.add("hello1");
        strs.add("hello2");
        strs.add("hello3");

        List<String> data = new ArrayList<>(new TreeSet<>(strs));

        RecyclerView rv = new RecyclerView(this);
        rv.addOnItemTouchListener(new OnRecyclerItemClickListener(rv){
            @Override
            public <T extends RecyclerView.ViewHolder> void onItemClick(T vh) {

            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });
    }


    public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private GestureDetectorCompat mGestureDetector;
        private RecyclerView recyclerView;

        public OnRecyclerItemClickListener(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }

        private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                    onItemClick(vh);
                }
                return true;
            }

            // 长点击事件，本例不需要不处理
            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                    onItemLongClick(vh);
                }
            }
        }

        public abstract <T extends RecyclerView.ViewHolder> void onItemClick(T vh);

        public abstract void onItemLongClick(RecyclerView.ViewHolder vh);
    }


}
