package lynxz.org.recyclerviewdemo.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * Created by Lynxz on 2016/11/16.
 * 博客: https://juejin.im/user/5812c2b0570c3500605a15ff
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        afterCreate()
    }

    /**
     * 初始化数据
     * */
    abstract fun afterCreate()

    /**
     * 设置布局id
     * */
    @LayoutRes
    abstract fun getLayoutRes(): Int
}