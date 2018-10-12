package com.roqiali.yukdolan.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.roqiali.yukdolan.R
import com.roqiali.yukdolan.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private var nameSp = "NAME"
    private var emailSp = "EMAIL"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val preferences = this.activity!!.getSharedPreferences("YukDolanPref", Context.MODE_PRIVATE)

        nameSp = preferences.getString("nameKey", "")
        emailSp = preferences.getString("emailKey", "")

        mAuth = FirebaseAuth.getInstance()
        view.tvName.text = nameSp
        view.tvEmail.text = emailSp

         view.card_out.setOnClickListener {
            signOut()
        }

        return view
    }

    private fun signOut() {
        mAuth?.signOut()
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity.finish()
    }

}
