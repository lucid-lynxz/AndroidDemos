package lynxz.org.recyclerviewdemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import lynxz.org.recyclerviewdemo.R

/**
 * Created by Lynxz on 2016/11/16.
 * description :
 */
class RvAdapter(var cxt: Context, var data: List<String> = mutableListOf(),
                var inflater: LayoutInflater = LayoutInflater.from(cxt)) : RecyclerView.Adapter<RvAdapter.VHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val view = inflater.inflate(R.layout.item, parent, false)
        return VHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.tv.text = data[position]
        // 简单的item单击事件,改写为 OnRecyclerItemClickListener 实现
        //        holder.itemView.setOnClickListener {
        //            Toast.makeText(cxt, "${holder.tv.text}", Toast.LENGTH_SHORT).show()
        //        }
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv: ImageView
        var tv: TextView

        init {
            iv = itemView.findViewById(R.id.iv) as ImageView
            tv = itemView.findViewById(R.id.tv_desc) as TextView
        }
    }
}