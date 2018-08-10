package xyz.tunlinaung.mmkuunyii.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.google.android.gms.appinvite.AppInviteInvitation
import com.google.android.gms.auth.api.Auth
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_job_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import xyz.tunlinaung.mmkuunyii.KuuNyiiApp
import xyz.tunlinaung.mmkuunyii.R
import xyz.tunlinaung.mmkuunyii.adapters.JobListAdapter
import xyz.tunlinaung.mmkuunyii.components.rvset.SmartRecyclerView
import xyz.tunlinaung.mmkuunyii.data.models.KuuNyiiModel
import xyz.tunlinaung.mmkuunyii.events.FirebaseEvents
import xyz.tunlinaung.mmkuunyii.views.pods.EmptyViewPod
import android.content.DialogInterface



public class JobListActivity : AppCompatActivity() {

    protected val RC_GOOGLE_SIGN_IN = 1236
    protected val RC_INVITE_TO_APP = 1237

    private lateinit var mJobListAdapter: JobListAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, JobListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        setSupportActionBar(toolbar)

        mJobListAdapter = JobListAdapter(applicationContext)
        rvJobList.adapter = mJobListAdapter

        rvJobList.setEmptyView(vpEmptyJob)

        fab.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Information")
                    .setMessage("Post new job is coming soon")
                    .setCancelable(false)
                    .setPositiveButton("ok") { dialog, which -> }.show()
        }

        ivLogout.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Information")
                    .setMessage("Logout is coming soon")
                    .setCancelable(false)
                    .setPositiveButton("ok") { dialog, which -> }.show()
        }

        ivInviteToApp.setOnClickListener { sendInvitation() }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)

        KuuNyiiModel.getInstance().loadJobs()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_INVITE_TO_APP) {
            if (resultCode == Activity.RESULT_OK) {
                // Check how many invitations were sent and log.
                val ids = AppInviteInvitation.getInvitationIds(resultCode, data)
                Log.d(KuuNyiiApp.TAG, "Invitations sent: " + ids.size)
                Snackbar.make(rvJobList, "Invitations sent to " + ids.size + " friends", Snackbar.LENGTH_SHORT).show()
            } else {
                // Sending failed or it was canceled, show failure message to the user
                Log.d(KuuNyiiApp.TAG, "Failed to send invitation.")
                Snackbar.make(rvJobList, "Failed to send invitation.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onJobListLoaded(event: FirebaseEvents.JobListLoadedEvent) {
        Log.d(KuuNyiiApp.TAG, "onJobListLoaded - " + event.job.size)
        tvJobCount.text = "" + event.job.size + "Jobs"
        mJobListAdapter.setNewData(event.job)
    }

    private fun sendInvitation() {
        val intent = AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build()
        startActivityForResult(intent, RC_INVITE_TO_APP)
    }

}
