package xyz.tunlinaung.mmkuunyii.views.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

abstract class BaseViewHolder<W>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    protected var mData: W? = null

    init {
        //itemView?.setOnClickListener(this)
    }

    abstract fun setData(data: W, position: Int)
}