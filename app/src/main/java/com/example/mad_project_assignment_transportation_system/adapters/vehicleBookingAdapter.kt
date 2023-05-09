package com.example.mad_project_assignment_transportation_system.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.vehicleDetailsModel
import java.text.FieldPosition


class vehicleBookingAdapter (private val veList: ArrayList<vehicleDetailsModel>) :
    RecyclerView.Adapter<vehicleBookingAdapter.ViewHolder>(){

    //fetch all details









    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_booking_owner_side_one_view, parent, false)
        return ViewHolder(itemView)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val currentVehicle = veList[position]
        holder.vNumb.text = currentVehicle.vehicleRegNumber
        holder.count.text = "Booking Count: ${currentVehicle.bookingCount}"
    }

    override fun getItemCount(): Int {
        return veList.size
    }

    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vNumb : TextView = itemView.findViewById(R.id.vNumb)
        val count: TextView = itemView.findViewById(R.id.countBooking)

        }

    }

