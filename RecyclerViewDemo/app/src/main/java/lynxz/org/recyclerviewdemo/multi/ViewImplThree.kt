package lynxz.org.recyclerviewdemo.multi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lynxz.org.multitypeadapter.BaseItem
import lynxz.org.multitypeadapter.BaseItemViewImpl
import lynxz.org.recyclerviewdemo.R

/**
 * Created by Lynxz on 2016/11/22.
 * description :
 */
class ViewImplThree : BaseItemViewImpl {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
            = ViewTwoHolder(inflater!!.inflate(R.layout.item_type_three, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: BaseItem) {
    }

    inner class ViewTwoHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}