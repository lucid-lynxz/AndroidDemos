package lynxz.org.recyclerviewdemo.activity

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_rv.*
import lynxz.org.multitypeadapter.BaseItem
import lynxz.org.multitypeadapter.MultiTypeAdapter
import lynxz.org.recyclerviewdemo.R
import lynxz.org.recyclerviewdemo.multi.*
import java.util.*

/**
 * Created by Lynxz on 2016/11/22.
 * description : recyclerView多种item布局的实现
 */
class MultiTypeItemActivity : BaseActivity() {
    override fun getLayoutRes() = R.layout.activity_rv

    override fun afterCreate() {
        var mData = ArrayList<BaseItem>().toMutableList()
        var adapter = MultiTypeAdapter(mData)
        rv_main.layoutManager = LinearLayoutManager(this)

        // item数据类需要继承baseItem
        mData.add(TypeOne("one-1"))
        mData.add(TypeOne("one-2"))
        mData.add(TypeTwo("two-1", 10))
        mData.add(TypeThree("three-1", 10, ""))
        mData.add(TypeOne("one-3"))
        mData.add(TypeTwo("two-2", 12))
        mData.add(TypeOne("one-4"))
        //绑定数据类型及其对应的view实现方法类
        adapter.addMultiTypeItem(TypeOne::class.java, ViewImplOne())
        adapter.addMultiTypeItem(TypeTwo::class.java, ViewImplTwo())
        adapter.addMultiTypeItem(TypeThree::class.java, ViewImplThree())
        // 设置适配器
        rv_main.adapter = adapter
    }

}