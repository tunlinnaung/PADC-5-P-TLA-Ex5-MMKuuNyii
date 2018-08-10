package xyz.tunlinaung.mmkuunyii.data.models

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.*
import org.greenrobot.eventbus.EventBus
import xyz.tunlinaung.mmkuunyii.KuuNyiiApp
import xyz.tunlinaung.mmkuunyii.data.vo.Apply
import xyz.tunlinaung.mmkuunyii.data.vo.Job
import xyz.tunlinaung.mmkuunyii.data.vo.Like
import xyz.tunlinaung.mmkuunyii.events.FirebaseEvents
import java.util.ArrayList

public class KuuNyiiModel {

    private val PATH_SAMPLE_DATA = "sample_data"
    private val OFFLINE_NEWSFEED = "jobs.json"

    private val MM_JOBS_FEED = "mmKuuNyii"

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mJobsDR: DatabaseReference
    private lateinit var mFirebaseAuth: FirebaseAuth
    private var mFirebaseUser: FirebaseUser? = null

    constructor(context: Context) {
        mDatabaseReference = FirebaseDatabase.getInstance().reference
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mFirebaseAuth.currentUser
    }

    companion object {

        private var INSTANCE: KuuNyiiModel? = null
        fun getInstance(): KuuNyiiModel {
            if (INSTANCE == null) {
                throw RuntimeException("ZeeWaKaModel is being invoked before initializing.")
            }

            //val i = INSTANCE
            return INSTANCE!!
        }
        fun initModel(context: Context) {
            INSTANCE = KuuNyiiModel(context)
        }

    }

    fun isUserSignIn(): Boolean {
        return mFirebaseUser != null
    }

    fun authenticateUserWithGoogleAccount(signInAccount: GoogleSignInAccount, delegate: SignInWithGoogleAccountDelegate) {
        Log.d(KuuNyiiApp.TAG, "signInAccount Id :" + signInAccount.id!!)
        // get google credentials by idtoken
        val credential = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
        // signin with credentails to google
        mFirebaseAuth!!.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    Log.d(KuuNyiiApp.TAG, "signInWithCredential:onComplete:" + task.isSuccessful)

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        Log.d(KuuNyiiApp.TAG, "signInWithCredential", task.exception)
                        delegate.onFailureSignIn(task.exception!!.message!!)
                    } else {
                        Log.d(KuuNyiiApp.TAG, "signInWithCredential - successful")
                        delegate.onSuccessSignIn(signInAccount)
                    }
                }
                .addOnFailureListener { e ->
                    Log.w(KuuNyiiApp.TAG, "OnFailureListener : " + e.message)
                    delegate.onFailureSignIn(e.message!!)
                }
    }

    fun loadJobs() {
        mJobsDR = mDatabaseReference!!.child(MM_JOBS_FEED)
        mJobsDR!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot != null) {
                    val jobList = ArrayList<Job>()
                    for (snapshot in dataSnapshot.children) {
                        val job = snapshot.getValue(Job::class.java)
                        jobList.add(job!!)
                    }
                    val event = FirebaseEvents.JobListLoadedEvent(jobList)
                    EventBus.getDefault().post(event)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun addLike(jobId: Int) {
        val like = Like()
        like.likeId = System.currentTimeMillis() / 1000
        like.userId = mFirebaseUser!!.uid
        like.isLike = true
        like.timestamp = System.currentTimeMillis()
        mJobsDR!!.child("" + jobId).child("like").setValue(like)
    }

    fun doApply(jobId: Int) {
        var apply = Apply()
        apply.applyId = System.currentTimeMillis() / 1000
        apply.userId = mFirebaseUser!!.uid
        apply.appliedDate = System.currentTimeMillis()
        mJobsDR!!.child("" + jobId).child("applyJob").setValue(apply)
    }

    interface SignInWithGoogleAccountDelegate {
        fun onSuccessSignIn(signInAccount: GoogleSignInAccount)

        fun onFailureSignIn(msg: String)
    }

}