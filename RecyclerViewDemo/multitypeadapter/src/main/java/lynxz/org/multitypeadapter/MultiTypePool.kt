package lynxz.org.multitypeadapter

import java.util.*

/**
 * Created by Lynxz on 2016/11/22.
 * description : 管理多种item类型及其view的实现之间的对应关系
 */
class MultiTypePool {

    /**
     * 存储不同item数据实体类对应的type index
     * */
    val data = ArrayList<Class<out BaseItem>>().toMutableList()
    /**
     * 每个 item 的具体实现,包裹 ViewHolder 的创建及数据绑定等
     * */
    val viewImpList = ArrayList<BaseItemViewImpl>().toMutableList()

    fun addMultiType(clazz: Class<out BaseItem>, viewImpl: BaseItemViewImpl) {
        if (!data.contains(clazz)) {
            data.add(clazz)
            viewImpList.add(getTypeIndex(clazz), viewImpl)
        } else {
            viewImpList[getTypeIndex(clazz)] = viewImpl
        }
    }

    fun getTypeIndex(clazz: Class<out BaseItem>) = data.indexOf(clazz)

    fun getItemViewImpl(clazz: Class<out BaseItem>) = getItemViewImpl(data.indexOf(clazz))

    fun getItemViewImpl(viewType: Int) = viewImpList[viewType]

}