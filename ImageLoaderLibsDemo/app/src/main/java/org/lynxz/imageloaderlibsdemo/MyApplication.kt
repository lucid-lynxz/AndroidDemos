package org.lynxz.imageloaderlibsdemo

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by lynxz on 26/02/2018.
 * 博客: https://juejin.im/user/5812c2b0570c3500605a15ff
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // fresco框架初始化,多次调用无意义
        Fresco.initialize(this)
    }
}