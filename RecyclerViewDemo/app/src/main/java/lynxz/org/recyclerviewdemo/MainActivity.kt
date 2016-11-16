package lynxz.org.recyclerviewdemo

import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_main

    override fun init() {
        rv_main.layoutManager = GridLayoutManager(this, 3)
        rv_main.adapter = RvAdapter(this)
    }
}
