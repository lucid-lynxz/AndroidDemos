package lynxz.org.multitypeadapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Lynxz on 2016/11/22.
 * description : item的具体实现
 */
interface BaseItemViewImpl {
    fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: BaseItem)
}