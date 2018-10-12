package com.roqiali.yukdolan.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.roqiali.yukdolan.data.ApiInterface
import com.roqiali.yukdolan.PlaceAdapter
import com.roqiali.yukdolan.R
import com.roqiali.yukdolan.data.ApiUser
import com.roqiali.yukdolan.data.Data
import com.roqiali.yukdolan.data.PlaceModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var recyPlace: RecyclerView? = null
    private lateinit var place : ArrayList<Data>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyPlace = view.rv_home

        recyPlace?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyPlace?.hasFixedSize()

        val apiPlace = ApiUser.getClient().create(ApiInterface::class.java)

        apiPlace.getPlace().enqueue(object : Callback<PlaceModel> {
            override fun onFailure(call: Call<PlaceModel>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PlaceModel>, response: Response<PlaceModel>) {
                place = response.body()!!.data
                recyPlace?.adapter = PlaceAdapter(place)
            }

        })

/*
        apiPlace.getPlace().observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler())
                .subscribe {
                    recyPlace?.adapter = PlaceAdapter(it)
                }
*/

        return view
    }
}
