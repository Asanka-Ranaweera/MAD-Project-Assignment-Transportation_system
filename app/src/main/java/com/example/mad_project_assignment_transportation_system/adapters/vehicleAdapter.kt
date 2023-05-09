package com.example.mad_project_assignment_transportation_system.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.vehicleDetailsModel
import java.text.FieldPosition

class vehicleAdapter (private val vehicleList: ArrayList<vehicleDetailsModel>) :
    RecyclerView.Adapter<vehicleAdapter.ViewHolder>(){

    //fetch all details
    private lateinit var mListner: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    //click one item
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListner = clickListener
    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_vehicle_list_view, parent, false)
        return ViewHolder(itemView, mListner)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val currentVehicle = vehicleList[position]
        holder.tvVehicleNo.text = currentVehicle.vehicleRegNumber
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

    class ViewHolder( itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvVehicleNo : TextView = itemView.findViewById(R.id.tvVehicleNo)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}