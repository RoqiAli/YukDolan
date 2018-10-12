package com.roqiali.yukdolan.activity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.roqiali.yukdolan.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        val nama = intent.getStringExtra("NAMA")
        val alamat = intent.getStringExtra("ALAMAT")
        val deskripsi = intent.getStringExtra("DETAIL")
        val gambar = intent.getStringExtra("GAMBAR")

        tvTitleDetail.text = nama
        tvAddress.text = alamat
        tvDetail.text = deskripsi

        Glide.with(this).load(gambar).into(backdrop)

        setBar()
    }

    private fun setBar() {
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }

                if (scrollRange + verticalOffset == 0) {
                    toolbar_layout.title = null
                    isShow = true
                } else if (isShow) {
                    toolbar_layout.title = null
                    isShow = false
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right)
    }
}