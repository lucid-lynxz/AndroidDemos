package lynxz.org.recyclerviewdemo

import android.animation.ObjectAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.activity_rv.*
import java.util.*

/**
 * Created by Lynxz on 2016/11/18.
 * description : 拖拽-滑动 item 示例
 */
class DragSwipeActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun init() {
        val data = (1..20).map { "pos $it" }.toMutableList()
        rv_main.layoutManager = GridLayoutManager(this, 3)
        rv_main.adapter = RvAdapter(this, data)

        // 添加滑动/拖拽功能
        // java的匿名内部类对应过来就是object对象表达式了
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            var vh: RecyclerView.ViewHolder? = null

            /**
             * 设置itemView可以移动的方向
             * */
            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
                // 拖拽的标记，这里允许上下左右四个方向
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or
                        ItemTouchHelper.RIGHT
                // 滑动的标记，这里允许左右滑动
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            /**
             *  在某个Item被拖动和移动的时候回调,这里我们用来播放动画
             *  当viewHolder不为空时为选中状态,否则为释放状态
             */
            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (viewHolder != null) {
                    vh = viewHolder
                    touchAnimation(viewHolder.itemView, 0)
                } else {
                    touchAnimation(vh!!.itemView, 1)
                }
            }

            /**
             * 当一个Item被另外的Item替代时回调,也就是数据集的内容顺序改变
             * 返回true, onMoved()才会进行
             * */
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return true
            }

            /**
             *  当onMove返回true的时候回调,刷新列表
             * */
            override fun onMoved(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, fromPos: Int, target: RecyclerView.ViewHolder?, toPos: Int, x: Int, y: Int) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
                // 移动完成后修改列表位置并刷新列表
                Collections.swap(data, viewHolder!!.adapterPosition, target!!.adapterPosition)
                rv_main.adapter.notifyItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
            }

            /**
             * 滑动完成时回调,这里设置为滑动删除,删除相应数据后刷新列表
             * */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                data.removeAt(viewHolder!!.adapterPosition)
                rv_main.adapter.notifyItemRemoved(viewHolder!!.adapterPosition)
            }

            /**
             * Item是否可以滑动
             * */
            override fun isItemViewSwipeEnabled() = true

            /**
             * Item是否可以长按
             * */
            override fun isLongPressDragEnabled() = true

        }).attachToRecyclerView(rv_main)
    }

    /**
     * direction : 指明itemView是上浮还是下沉,当某个itemView被选中时,则其上浮
     * */
    private fun touchAnimation(view: View, direction: Int) {
        logi("direction $direction")
        val animator = if (direction == 0)
            ObjectAnimator.ofFloat(view, "translationZ", 1f, 100f)
        else
            ObjectAnimator.ofFloat(view, "translationZ", 100f, 1f)
        animator.interpolator = DecelerateInterpolator()
        animator.duration = 10
        animator.start()
    }
}