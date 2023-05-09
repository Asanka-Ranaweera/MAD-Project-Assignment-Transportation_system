package com.example.mad_project_assignment_transportation_system.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R

import com.example.mad_project_assignment_transportation_system.adapters.driver_ad_adapter
import com.example.mad_project_assignment_transportation_system.models.DriverAdModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.text.FieldPosition

class driver_vehicleowner_ad_view_fetch : AppCompatActivity() {
    private lateinit var adRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var adList: ArrayList<DriverAdModel>
    private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_vehicleowner_ad_view_fetch)

        //navigation
        var button = findViewById<Button>(R.id.backbtn3)

        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        adRecyclerView = findViewById(R.id.adView)
        adRecyclerView.layoutManager = LinearLayoutManager(this)
        adRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        adList = arrayListOf<DriverAdModel>()

        getAd_data()
    }

    private fun getAd_data() {
        adRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Driver Ads")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                adList.clear()
                if(snapshot.exists()){
                    for (adSnap in snapshot.children){
                        val adData = adSnap.getValue(DriverAdModel ::class.java)
                        adList.add(adData!!) // !! avoid null data
                    }
                    val mAdapter = driver_ad_adapter(adList)
                    adRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : driver_ad_adapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@driver_vehicleowner_ad_view_fetch,driver_vehicleowner_ad_details::class.java)

                            //put extras
                            intent.putExtra("adId", adList[position].adId)
                            intent.putExtra("transport_type", adList[position].trasportTypes)
                            intent.putExtra("vehicleType", adList[position].vehicleTypes)
                            intent.putExtra("ownerName", adList[position].oNames)
                            intent.putExtra("ownerNum", adList[position].oNums)
                            intent.putExtra("route", adList[position].routes1)
                            intent.putExtra("experience", adList[position].experiences)
                            intent.putExtra("duration", adList[position].durations)
                            startActivity(intent)
                        }

                    })

                    adRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}