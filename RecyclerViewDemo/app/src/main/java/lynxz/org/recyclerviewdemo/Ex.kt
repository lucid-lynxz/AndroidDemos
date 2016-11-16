package lynxz.org.recyclerviewdemo

import android.app.Activity
import android.app.Fragment
import android.util.Log
import android.widget.Toast

/**
 * Created by Lynxz on 2016/11/16.
 * description : 扩展方法
 */

// toast提示快捷方法
fun Activity.toast(msg: String, duration: Int) {
    Toast.makeText(this, msg, duration).show()
}

fun Activity.toast(msg: Int, duration: Int) {
    Toast.makeText(this, msg, duration).show()
}

fun Activity.toast(msg: String) {
    this.toast(msg, Toast.LENGTH_SHORT)
}

fun Activity.toast(msg: Int) {
    this.toast(msg, Toast.LENGTH_SHORT)
}


// 日志打印
var debugMode = true

fun Activity.logi(msg: String) {
    logi(this.localClassName, msg)
}

fun Activity.logi(tag: String, msg: String) {
    if (debugMode) Log.i(tag, msg)
}

fun Fragment.logi(tag: String, msg: String) {
    activity.logi(tag, msg)
}

fun Fragment.logi(msg: String) {
    logi(activity.localClassName, msg)
}