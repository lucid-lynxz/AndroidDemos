package lynxz.org.recyclerviewdemo.multi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import lynxz.org.multitypeadapter.BaseItem
import lynxz.org.multitypeadapter.BaseItemViewImpl
import lynxz.org.recyclerviewdemo.R

/**
 * Created by Lynxz on 2016/11/22.
 * description :
 */
class ViewImplTwo : BaseItemViewImpl {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
            = ViewTwoHolder(inflater!!.inflate(R.layout.item_type_two, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: BaseItem) {
        if (holder is ViewTwoHolder && item is TypeTwo) {
            holder.tvName!!.text = "年纪是 ${item.age}"
        }
    }

    inner class ViewTwoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView? = null

        init {
            tvName = itemView.findViewById(R.id.tv_name) as TextView
        }

    }
}