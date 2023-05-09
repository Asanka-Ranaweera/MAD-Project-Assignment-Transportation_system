package com.example.mad_project_assignment_transportation_system.adapters

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.PassengerRequestModel


class RequestAdapter (private val requestList : ArrayList<PassengerRequestModel> ):
    RecyclerView.Adapter<RequestAdapter.ViewHolder>(){

    private lateinit var mListner : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.p_request_list_item,parent,false)
        return ViewHolder(itemView,mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRequest = requestList[position]
        holder.tvName.text = currentRequest.p_title
    }

    override fun getItemCount(): Int {
        return requestList.size
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