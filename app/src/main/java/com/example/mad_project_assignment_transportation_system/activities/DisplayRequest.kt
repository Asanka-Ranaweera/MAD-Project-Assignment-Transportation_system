package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.models.PassengerRequestModel
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.adapters.RequestAdapter
import com.google.firebase.database.*

class DisplayRequest : AppCompatActivity() {

    private lateinit var pRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var requestList : ArrayList<PassengerRequestModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_request)

        pRecyclerView = findViewById(R.id.rvPassengerRequest)
        pRecyclerView.layoutManager = LinearLayoutManager(this)
        pRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        requestList = arrayListOf<PassengerRequestModel>()

        getRequestsData()
    }

    private fun getRequestsData(){
        pRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Passenger_Requests")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                requestList.clear()
                if(snapshot.exists()){
                    for (requestSnap in snapshot.children){
                        val requestData = requestSnap.getValue(PassengerRequestModel::class.java)
                        requestList.add(requestData!!)
                    }
                    val mAdapter = RequestAdapter(requestList)
                    pRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : RequestAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@DisplayRequest,RequestDetails::class.java)

                            //put extras
                            intent.putExtra("requestID",requestList[position].requestID)
                            intent.putExtra("name",requestList[position].name)
                            intent.putExtra("p_title",requestList[position].p_title)
                            intent.putExtra("pno",requestList[position].pno)
                            intent.putExtra("p_post",requestList[position].p_post)
                            startActivity(intent)
                        }

                    })

                    pRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}