package lynxz.org.recyclerviewdemo

import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_main

    override fun init() {
        //跳转到默认的动画示例页面
        btn_default.setOnClickListener {
            startActivity(Intent(this@MainActivity, DefaultAnimatorActivity::class.java))
        }

        // 启用拖拽改变顺序和滑动删除功能示例页面
        btn_drag_swipe.setOnClickListener {
            startActivity(Intent(this@MainActivity, DragSwipeActivity::class.java))
        }
    }
}