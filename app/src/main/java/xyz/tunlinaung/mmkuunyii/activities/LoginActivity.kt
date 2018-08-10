package xyz.tunlinaung.mmkuunyii.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_login.*
import xyz.tunlinaung.mmkuunyii.KuuNyiiApp
import xyz.tunlinaung.mmkuunyii.R
import xyz.tunlinaung.mmkuunyii.data.models.KuuNyiiModel

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    protected val RC_GOOGLE_SIGN_IN = 1236

    protected lateinit var mGoogleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this, this)

//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("636720422561-pcgujnvcklge36frkufbm204agpfh594.apps.googleusercontent.com")
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(applicationContext)
                .enableAutoManage(this /*FragmentActivity*/, this /*OnConnectionFailedListener*/)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build()

        btnSignIn.setOnClickListener { onLogin() }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            processGoogleSignInResult(result)
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    private fun processGoogleSignInResult(signInResult: GoogleSignInResult) {
        if (signInResult.isSuccess) {
            // Google Sign-In was successful, authenticate with Firebase
            val account = signInResult.signInAccount
            KuuNyiiModel.getInstance().authenticateUserWithGoogleAccount(account!!, object : KuuNyiiModel.SignInWithGoogleAccountDelegate {
                override fun onSuccessSignIn(signInAccount: GoogleSignInAccount) {
                    startJobListActivity()
                }

                override fun onFailureSignIn(msg: String) {

                }
            })
        } else {
            // Google Sign-In failed
            Log.e(KuuNyiiApp.TAG, "Google Sign-In failed.")
            Snackbar.make(ivLogin, "Your Google sign-in failed.", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun startJobListActivity() {
        startActivity(JobListActivity.newIntent(applicationContext))
    }

    fun onLogin() {
        if (KuuNyiiModel.getInstance().isUserSignIn()) {
            startJobListActivity()
        } else {
            // Not signed in, launch the Sign In activity
            Snackbar.make(ivLogin, "You need to sign with Google to publish in MM-KuuNyii.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Sign-In") { signInWithGoogle() }.show()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

}
