package com.example.mad_project_assignment_transportation_system.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R

import com.example.mad_project_assignment_transportation_system.models.BookingModel

class BookingAdapter (private val bookingList : ArrayList<BookingModel> ):
    RecyclerView.Adapter<BookingAdapter.ViewHolder>(){

    private lateinit var mListner : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.booking_list_item,parent,false)
        return ViewHolder(itemView,mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRequest = bookingList[position]
        holder.tvName.text = currentRequest.vno
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }

    class ViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val tvName : TextView = itemView.findViewById(R.id.tvName)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}