package lynxz.org.recyclerviewdemo

import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Created by Lynxz on 2016/11/18.
 * description : http://blog.csdn.net/liaoinstan/article/details/51200600
 */
abstract class OnRecyclerItemClickListener(private val recyclerView: RecyclerView) : RecyclerView.OnItemTouchListener {
    private val mGestureDetector: GestureDetectorCompat

    init {
        mGestureDetector = GestureDetectorCompat(recyclerView.context, ItemTouchHelperGestureListener())
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        mGestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        mGestureDetector.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

    private inner class ItemTouchHelperGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val child = recyclerView.findChildViewUnder(e.x, e.y)
            if (child != null) {
                val vh = recyclerView.getChildViewHolder(child)
                onItemClick(vh)
            }
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            val child = recyclerView.findChildViewUnder(e.x, e.y)
            if (child != null) {
                val vh = recyclerView.getChildViewHolder(child)
                onItemLongClick(vh)
            }
        }
    }

    abstract fun <T : RecyclerView.ViewHolder> onItemClick(vh: T)
    abstract fun <T : RecyclerView.ViewHolder> onItemLongClick(vh: T)
}