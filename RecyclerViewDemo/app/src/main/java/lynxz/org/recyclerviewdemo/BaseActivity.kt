package lynxz.org.recyclerviewdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Lynxz on 2016/11/16.
 * description :
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    /**
     * 初始化数据
     * */
    abstract fun init()

    /**
     * 设置布局id
     * */
    abstract fun getLayoutId(): Int
}