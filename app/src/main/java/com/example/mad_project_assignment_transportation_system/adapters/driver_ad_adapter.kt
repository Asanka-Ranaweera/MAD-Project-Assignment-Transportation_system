package com.example.mad_project_assignment_transportation_system.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R

import com.example.mad_project_assignment_transportation_system.models.DriverAdModel

class driver_ad_adapter ( private val adList: ArrayList<DriverAdModel>) :
     RecyclerView.Adapter<driver_ad_adapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adView = LayoutInflater.from(parent.context). inflate(R.layout.activity_driver_vehicleowner_ad_view,parent,false)
        return ViewHolder(adView, mListener)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentAd = adList[position]
        holder.tvAdName.text = currentAd.adId
    }

    override fun getItemCount(): Int {
        return adList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val tvAdName : TextView = itemView.findViewById(R.id.adview1)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}
