package lynxz.org.recyclerviewdemo.activity

import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import lynxz.org.recyclerviewdemo.R

class MainActivity : BaseActivity() {
    override fun getLayoutRes() = R.layout.activity_main

    override fun afterCreate() {
        //跳转到默认的动画示例页面
        btn_default.setOnClickListener {
            startActivity(Intent(this@MainActivity, DefaultAnimatorActivity::class.java))
        }

        // 启用拖拽改变顺序和滑动删除功能示例页面
        btn_drag_swipe.setOnClickListener {
            startActivity(Intent(this@MainActivity, DragSwipeActivity::class.java))
        }

        btn_multi_type.setOnClickListener {
            startActivity(Intent(this@MainActivity, MultiTypeItemActivity::class.java))
        }
        btn_group_decoration.setOnClickListener {
            startActivity(Intent(this@MainActivity, GroupTitleRvActivity::class.java))
        }
    }
}