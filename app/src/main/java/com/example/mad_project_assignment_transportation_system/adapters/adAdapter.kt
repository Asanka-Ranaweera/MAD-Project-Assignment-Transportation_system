package com.example.mad_project_assignment_transportation_system.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.activities.user



class adAdapter(private val adList: ArrayList<user>) : RecyclerView.Adapter <adAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.ad_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = adList[position]

        holder.ttype.text = currentitem.trasportTypes
        holder.vtype.text = currentitem.vehicleTypes
        holder.route.text = currentitem.routes1
        holder.oname.text = currentitem.onames
        holder.onum.text = currentitem.onums
        holder.experience.text = currentitem.experiences

    }

    override fun getItemCount(): Int {

        return adList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val ttype : TextView = itemView.findViewById(R.id.tvttype)
        val vtype : TextView = itemView.findViewById(R.id.tvvehicle)
        val route : TextView = itemView.findViewById(R.id.tvroute)
        val oname : TextView = itemView.findViewById(R.id.tvoname)
        val onum : TextView = itemView.findViewById(R.id.tvonumber)
        val experience : TextView = itemView.findViewById(R.id.tvexp)

    }
}