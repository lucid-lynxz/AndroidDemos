package lynxz.org.recyclerviewdemo

import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Lynxz on 2016/11/16.
 * description :
 */
class RvAdapter(var cxt: Context, var data: List<String> = listOf<String>(), var inflater: LayoutInflater = LayoutInflater.from(cxt)) : RecyclerView.Adapter<RvAdapter.VHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VHolder {
        var view = inflater.inflate(R.layout.item, parent, false)
        var animator = ObjectAnimator.ofFloat(view, "translationZ", 0f, 0f)
        animator.interpolator = DecelerateInterpolator()
        animator.duration = 10
        animator.start()
        return VHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.tv.text = data[position]
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