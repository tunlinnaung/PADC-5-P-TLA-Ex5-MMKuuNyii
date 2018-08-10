package xyz.tunlinaung.mmkuunyii.adapters

import android.content.Context
import android.view.ViewGroup
import xyz.tunlinaung.mmkuunyii.R
import xyz.tunlinaung.mmkuunyii.data.vo.Job
import xyz.tunlinaung.mmkuunyii.views.holders.BaseViewHolder
import xyz.tunlinaung.mmkuunyii.views.holders.JobItemViewHolder

public class JobListAdapter(context: Context) : BaseRecyclerAdapter<JobItemViewHolder, Job>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobItemViewHolder {
        val view = mLayoutInflator.inflate(R.layout.item_job, parent, false)
        return JobItemViewHolder(view)
    }

}