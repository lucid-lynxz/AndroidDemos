package lynxz.org.recyclerviewdemo

import android.app.Activity
import android.app.Application
import android.text.TextUtils
import android.widget.Toast

/**
 * Created by Lynxz on 2016/11/16.
 * description : 扩展方法
 */

fun CharSequence.isEmpty(): Boolean {
    return TextUtils.isEmpty(this)
}

fun android.support.v4.app.Fragment.showToast(msg: String) {
    activity.showToast(msg)
}

fun android.support.v4.app.Fragment.showToast(msgId: Int) {
    activity.showToast(msgId)
}

fun Activity.showToast(msgId: Int) {
    application.showToast(msgId)
}

fun Activity.showToast(msg: String) {
    application.showToast(msg)
}

fun Application?.showToast(msg: String) {
    this?.let {
        Toast.makeText(this.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}

fun Application?.showToast(msgId: Int) {
    this?.let {
        Toast.makeText(this.applicationContext, msgId, Toast.LENGTH_SHORT).show()
    }
}

inline fun debugConf(code: () -> Unit) {
    if (BuildConfig.DEBUG) {
        code()
    }
}