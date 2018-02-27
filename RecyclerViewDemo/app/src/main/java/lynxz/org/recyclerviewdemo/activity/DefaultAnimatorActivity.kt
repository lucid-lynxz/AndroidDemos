package lynxz.org.recyclerviewdemo.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_rv.*
import lynxz.org.recyclerviewdemo.R
import lynxz.org.recyclerviewdemo.adapter.RvAdapter

/**
 * 测试 RecyclerView 提供的 DefaultItemAnimator 效果
 * */
class DefaultAnimatorActivity : BaseActivity() {
    override fun getLayoutRes() = R.layout.activity_rv

    override fun afterCreate() {
        ll_controller.visibility = View.VISIBLE
        rv_main.layoutManager = LinearLayoutManager(this)
        var data = (1..3).map { "pos $it" }.toMutableList()
        rv_main.adapter = RvAdapter(this, data)
        rv_main.itemAnimator = DefaultItemAnimator()

        // 测试添加或删除item的效果
        btn_add.setOnClickListener {
            var addPos = if (data.size > 2) 2 else 0
            data.add(addPos, "I'm new added")
            // 调用 insert 方法才会有动画效果
            rv_main.adapter.notifyItemRangeInserted(addPos, 1)
        }

        // 删除item
        btn_remove.setOnClickListener {
            // 不能删除不存在的item
            if (data.size > 0) {
                var removePos = data.size / 2
                data.removeAt(removePos)
                // 调用 Removed 方法才会有动画效果
                rv_main.adapter.notifyItemRemoved(removePos)
            }
        }

        // 切换布局方式
        btn_change_layout.setOnClickListener {
            rv_main.layoutManager =
                    // 判断的是GridLayoutManager,因为它是LinearLayoutManager的子类
                    if (rv_main.layoutManager is GridLayoutManager)
                        LinearLayoutManager(this)
                    else
                        GridLayoutManager(this, 3)
        }
    }
}