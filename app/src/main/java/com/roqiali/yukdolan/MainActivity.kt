package com.roqiali.yukdolan

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.roqiali.yukdolan.fragment.HomeFragment
import com.roqiali.yukdolan.fragment.PlaceFragment
import com.roqiali.yukdolan.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var nameSp = "NAME"
    private var emailSp = "EMAIL"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(HomeFragment(), R.id.frame)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_place -> {
                replaceFragment(PlaceFragment(), R.id.frame)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                replaceFragment(ProfileFragment(), R.id.frame)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HomeFragment(), R.id.frame)
        val pref = applicationContext.getSharedPreferences("YukDolanPref", Context.MODE_PRIVATE)
/*        nameSp = pref.getString("nameKey", "")
        emailSp = pref.getString("emailKey", "")

        nama.text = nameSp
        email.text = emailSp*/
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right).func().commit()
    }

    private fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    private fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }
}
