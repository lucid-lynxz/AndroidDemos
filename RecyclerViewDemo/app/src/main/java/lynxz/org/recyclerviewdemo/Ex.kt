package cn.soundbus.sdx

import android.app.Activity
import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import lynxz.org.recyclerviewdemo.BuildConfig
import java.text.DecimalFormat
import java.text.SimpleDateFormat


/**
 * Created by lynxz on 13/01/2017.
 * update 2018.11.21 v1.0
 * 扩展函数
 */
fun CharSequence.isEmpty(): Boolean {
    return TextUtils.isEmpty(this)
}

fun Context.showToast(msg: String) {
    if (msg.isNotBlank()) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

fun Context.showToast(msgId: Int) {
    Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show()
}

fun Context.getStringRes(@StringRes strId: Int): String {
    return resources.getString(strId)
}

fun Fragment.showToast(msg: String) {
    if (msg.isNotBlank()) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.showToast(msgId: Int) {
    Toast.makeText(activity, msgId, Toast.LENGTH_SHORT).show()
}

fun Fragment.getStringRes(@StringRes strResId: Int): String? {
    return activity?.resources?.getString(strResId)
}

fun Context.isPermissionGranted(permission: String): Boolean {
    return PermissionChecker.checkSelfPermission(this, permission) == PermissionChecker.PERMISSION_GRANTED
}

fun Activity.hideKeyboard() {
    hideKeyBoard(currentFocus)
}

/**
 * 强制隐藏输入法键盘
 */
fun Context.hideKeyBoard(currentFocusView: View?) {
    currentFocusView?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                ?: return
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

/**
 * 保留N位小数,并返回字符串
 * */
fun Double.limit(limit: Int = 2): String {
    val pattern = "0.${"#".repeat(limit)}"
    return DecimalFormat(pattern).format(this)
}

/**
 * double类型向上保留转换为整数,如 2.1 -> 3  2.0->2
 * */
fun Double.toIntUp(): Int {
    val remainder = if (this % 1 > 0) 1 else 0
    return this.toInt() + remainder
}

inline fun debugConf(code: () -> Unit) {
    if (BuildConfig.DEBUG) {
        code()
    }
}

fun String?.convertDateFormat(oriFormat: String = "yyyy-MM-dd'T'HH:mm:ss", newformat: String = "yyyy.MM/dd HH:mm"): String {
    return try {
        val oriSdf = SimpleDateFormat(oriFormat)
        val date = oriSdf.parse(this)
        SimpleDateFormat(newformat).format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun Long?.convertDateFormat(format: String = "yyyy.MM/dd HH:mm"): String {
    if (this == null) return ""
    return try {
        val targetTs = if ("$this".length <= 10) this * 1000 else this
        SimpleDateFormat(format).format(targetTs)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

///**
// * 类型转换
// * */
//inline fun <T, reified R> T.convertToObj(doOnError: (T) -> Unit): R? {
//    val jsonStr = when (this) {
//        is HttpException -> { // retrofit2.HttpException
//            this.response().errorBody()?.string()
//        }
//        is String -> this
//        is CharSequence -> this.toString()
//        is R -> return this
//        else -> null
//    }
//    return try {
//        mGson.fromJson<R>(jsonStr, R::class.java)
//    } catch (e: Exception) {
//        e.printStackTrace()
//        doOnError(this)
//        null
//    }
//}