package xyz.tunlinaung.mmkuunyii.views.holders

import android.view.View
import kotlinx.android.synthetic.main.item_job.view.*
import xyz.tunlinaung.mmkuunyii.data.models.KuuNyiiModel
import xyz.tunlinaung.mmkuunyii.data.vo.Job

public class JobItemViewHolder(itemView: View?) : BaseViewHolder<Job>(itemView) {

    override fun setData(data: Job, position: Int) {
        itemView.tvJobTitle.text = data.shortDesc
        itemView.tvPostedDate.text = data.postedDate

        itemView.ivApply.setOnClickListener { KuuNyiiModel.getInstance().doApply(data.jobPostId!! - 1) }

        itemView.btnLike.setOnClickListener { KuuNyiiModel.getInstance().addLike(data.jobPostId!! - 1) }
    }

}