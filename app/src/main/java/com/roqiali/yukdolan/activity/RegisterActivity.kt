package com.roqiali.yukdolan.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.roqiali.yukdolan.R
import kotlinx.android.synthetic.main.activity_register.*


/**
 * A login screen that offers login via email/password.
 */
class RegisterActivity : AppCompatActivity() {
    private val TAG = "EmailPassword"
    //private var mAuthTask: UserLoginTask? = null

    private val nameS = "nameKey"
    private val emailS = "emailKey"

    var fbAuth = FirebaseAuth.getInstance()
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = this.getSharedPreferences("YukDolanPref", Context.MODE_PRIVATE)

        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptRegister()
                return@OnEditorActionListener true
            }
            false
        })

        email_sign_up_button.setOnClickListener { attemptRegister() }

        val spannableString = SpannableString("Sudah punya akun? Masuk aja.")
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(applicationContext, R.color.colorPrimaryTextDefaultMaterialLight)), 18, 28, 0)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), 18, 28, 0)
        spannableString.setSpan(BackgroundColorSpan(ContextCompat.getColor(applicationContext, R.color.colorAccent)), 18, 28, 0)
        tvToLogin.text = spannableString

        tvToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun attemptRegister() {
        email.error = null
        password.error = null
        username.error = null

        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()
        val userStr = username.text.toString()

        var cancel = false
        var focusView: View? = null

        if (TextUtils.isEmpty(passwordStr)) {
            password.error = getString(R.string.error_field_required)
            focusView = password
            cancel = true
        }

        if (TextUtils.isEmpty(userStr)) {
            username.error = getString(R.string.error_field_required)
            focusView = username
            cancel = true
        }


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            email.error = getString(R.string.error_invalid_email)
            focusView = email
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        } else {
            showProgress(true)
            signUp(userStr, emailStr, passwordStr)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 6
    }

    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_form.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    private fun signUp(userName: String, email: String, password: String) {
        fbAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = fbAuth.currentUser
                        val profileChangeRequest = UserProfileChangeRequest.Builder()
                                .setDisplayName(userName).build()
                        user!!.updateProfile(profileChangeRequest).addOnCompleteListener { tasks ->
                            if (tasks.isSuccessful) {
                                val newUser = fbAuth.currentUser
                                showUser(newUser!!)
                            } else {
                                showProgress(false)
                            }
                        }
                    } else {
                        showProgress(false)
                    }
                }

    }

    private fun showUser(user: FirebaseUser) {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        val name = user.displayName
        val email = user.email

        val editor = sharedPreferences!!.edit()
        editor.putString(nameS, name)
        editor.putString(emailS, email)
        editor.apply()
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = fbAuth.currentUser
        if (currentUser!=null){
            showUser(currentUser)
        }
    }

    companion object {
        private val REQUEST_READ_CONTACTS = 0
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }
}
