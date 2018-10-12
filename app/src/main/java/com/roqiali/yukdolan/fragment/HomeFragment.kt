package com.roqiali.yukdolan.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.roqiali.yukdolan.PlaceAdapter
import com.roqiali.yukdolan.R
import com.roqiali.yukdolan.activity.DetailActivity
import com.roqiali.yukdolan.data.ApiInterface
import com.roqiali.yukdolan.data.ApiUser
import com.roqiali.yukdolan.data.Data
import com.roqiali.yukdolan.data.PlaceModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var recyPlace: RecyclerView? = null
    private lateinit var place: ArrayList<Data>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyPlace = view.rv_home

        recyPlace?.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        recyPlace?.hasFixedSize()

        val apiPlace = ApiUser.getClient().create(ApiInterface::class.java)

        apiPlace.getPlace().enqueue(object : Callback<PlaceModel> {
            override fun onFailure(call: Call<PlaceModel>, t: Throwable) {
                Toast.makeText(activity, "SOMETHING WRONG", Toast.LENGTH_LONG).show()// created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PlaceModel>, response: Response<PlaceModel>) {
                pb.visibility = View.GONE
                recyPlace?.visibility = View.VISIBLE
                place = response.body()!!.data
                recyPlace?.adapter = PlaceAdapter(place) { partItem: Data -> partItemClicked(partItem) }
            }

        })

        return view
    }

    private fun partItemClicked(partItem: Data) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("NAMA", partItem.nama_pariwisata)
        intent.putExtra("ALAMAT", partItem.alamat_pariwisata)
        intent.putExtra("DETAIL", partItem.detail_pariwisata)
        intent.putExtra("GAMBAR", partItem.gambar_pariwisata)
        startActivity(intent)
        (context as Activity).overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back)
    }
}
