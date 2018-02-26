图片加载库的使用demo

## [fresco](https://github.com/facebook/fresco)
[文档](https://www.fresco-cn.org/docs/index.html)

### 1. 添加依赖
* 在 `/build.gradle` 中设置版本号
```gradle
// project级的build.gradle
buildscript {
    ext{
        fresco_version = '1.8.1'
    }
    ...
}
```

* 在 `app/build.gradle` 中添加依赖
```gradle
dependencies {
    // fresco
    compile "com.facebook.fresco:fresco:$fresco_version"
    
    // 以下fresco库按需添加
    // 在 API < 14 上的机器支持 WebP 时，需要添加
    compile "com.facebook.fresco:animated-base-support:$fresco_version"
   
    // 支持 GIF 动图，需要添加
    compile "com.facebook.fresco:animated-gif:$fresco_version"
    
    // 支持 WebP （静态图+动图），需要添加
    compile "com.facebook.fresco:animated-webp:$fresco_version"
    compile "com.facebook.fresco:webpsupport:$fresco_version"
    
    // 仅支持 WebP 静态图，需要添加
    compile "com.facebook.fresco:webpsupport:$fresco_version"
}
```

### 2. 初始化
```kotlin
// 自定义 application 类
import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // fresco框架初始化,多次调用无意义
        Fresco.initialize(this)
    }
}
```

### 3. 添加Internet权限
```xml
<manifest>
    <uses-permission android:name="android.permission.INTERNET"/>
    <application
        android:name=".MyApplication"/>
    </application>
</manifest>
```

### 4. 在xml布局中添加 SimpleDraweeView
通过 `app:placeholderImage` 来设置占位图
```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <com.facebook.drawee.view.SimpleDraweeView
        android:id="@+id/sdv_demo"
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:placeholderImage="@mipmap/ic_launcher"/>
</LinearLayout>
```

### 5. 加载图片
uri的具体规则参考 [文档](https://www.fresco-cn.org/docs/supported-uris.html)
```kotlin
private fun loadByFresco(uri: Uri, targetView: GenericDraweeView) {
    targetView.setImageURI(uri)
}
```