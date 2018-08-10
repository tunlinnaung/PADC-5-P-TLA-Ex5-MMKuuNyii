package xyz.tunlinaung.mmkuunyii.views.pods

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import xyz.tunlinaung.mmkuunyii.R

class EmptyViewPod : RelativeLayout {

    @BindView(R.id.lbl_empty)
    internal var lblEmpty: TextView? = null

    @BindView(R.id.iv_empty)
    internal var ivEmpty: ImageView? = null

    @BindView(R.id.btn_action_for_empty)
    internal var btnActionForEmpty: Button? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onFinishInflate() {
        super.onFinishInflate()
        ButterKnife.bind(this, this)
    }

    fun setEmptyLabel(emptyLabel: String) {
        lblEmpty!!.text = emptyLabel
    }

    fun setEmptyLabel(emptyLabel: String, textColor: Int) {
        lblEmpty!!.text = emptyLabel
        lblEmpty!!.setTextColor(textColor)
    }

    fun setEmptyAction(emptyAction: String, delegate: EmptyActionDelegate) {
        btnActionForEmpty!!.visibility = View.VISIBLE
        btnActionForEmpty!!.text = emptyAction
        btnActionForEmpty!!.setOnClickListener(delegate)
    }

    fun setEmptyImage(resourceId: Int) {
        ivEmpty!!.setImageResource(resourceId)
    }

    interface EmptyActionDelegate : View.OnClickListener
}