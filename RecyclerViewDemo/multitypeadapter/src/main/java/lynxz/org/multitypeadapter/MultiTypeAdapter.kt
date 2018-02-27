package lynxz.org.multitypeadapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Lynxz on 2016/11/22.
 * description : 多种布局的适配器
 */
class MultiTypeAdapter(var mData: MutableList<BaseItem>, var mPool: MultiTypePool = MultiTypePool()) : RecyclerView.Adapter<ViewHolder>() {
    var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        val itemViewImpl = mPool.getItemViewImpl(viewType)
        return itemViewImpl.onCreateViewHolder(inflater!!, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewImpl = mPool.getItemViewImpl(mData[position].javaClass)
        itemViewImpl.onBindViewHolder(holder, mData[position])
    }

    override fun getItemViewType(position: Int) = mPool.getTypeIndex(mData[position].javaClass)

    override fun getItemCount() = mData.size

    fun addMultiTypeItem(clazz: Class<out BaseItem>, viewImpl: BaseItemViewImpl) {
        mPool.addMultiType(clazz, viewImpl)
    }
}
