package org.lynxz.imageloaderlibsdemo

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.drawee.view.GenericDraweeView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val imgUrl = "https://www.fresco-cn.org/static/logo.png"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadByFresco(Uri.parse(imgUrl), sdv_demo)
        loadByFresco(Uri.parse("res://org.lynxz.imageloaderlibsdemo/" + R.mipmap.ic_launcher), sdv_demo_local)
    }

    /**
     * 通过fresco加载网络图片
     * */
    private fun loadByFresco(uri: Uri, targetView: GenericDraweeView) {
        targetView.setImageURI(uri)
    }
}
