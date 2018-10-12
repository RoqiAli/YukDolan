package com.roqiali.yukdolan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.roqiali.yukdolan.data.Data
import kotlinx.android.synthetic.main.item_place.view.*

class PlaceAdapter(val place: ArrayList<Data>, private val mListener: (Data) -> Unit)
    : RecyclerView.Adapter<PlaceAdapter.PlaceHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlaceHolder {
        return PlaceHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_place, p0, false))
    }

    override fun getItemCount(): Int = place.size

    override fun onBindViewHolder(p0: PlaceHolder, p1: Int) = p0.bindItem(place[p1], mListener)

    class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(plc: Data, mListener: (Data) -> Unit) {
            itemView.tvTitle.text = plc.nama_pariwisata
            Glide.with(itemView.context).load(plc.gambar_pariwisata).into(itemView.ivCover)
            itemView.setOnClickListener { mListener(plc) }
        }
    }
}