package com.example.mad_project_assignment_transportation_system.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.adapters.vehicleBookingAdapter

import com.example.mad_project_assignment_transportation_system.models.VehicleOwnerbookingViewModel
import com.example.mad_project_assignment_transportation_system.models.vehicleDetailsModel
import com.google.android.play.core.integrity.i
import com.google.firebase.database.*

class Booking_Vehicle_OwnerSide : AppCompatActivity() {

    private lateinit var recyclBooking: RecyclerView

    private lateinit var bList: ArrayList<vehicleDetailsModel>
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_vehicle_owner_side)



                recyclBooking = findViewById(R.id.recyclBooking)
                recyclBooking.layoutManager = LinearLayoutManager(this)
                 recyclBooking.setHasFixedSize(true)


                bList = arrayListOf<vehicleDetailsModel>()

                getEmployeesData()

            }

    private fun getEmployeesData() {
        recyclBooking.visibility = View.GONE
        dbRef = FirebaseDatabase.getInstance().getReference("vehicleDetails")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bList.clear()
                if (snapshot.exists()) {
                    for (empSnap in snapshot.children) {
                        val bookingData = empSnap.getValue(vehicleDetailsModel::class.java)

                        // count the number of bookings for the current vehicle
                        val vehicleCountSnapshot = snapshot.child("Bookings").child("vno").child(bookingData?.vehicleRegNumber ?: "").getChildrenCount()
                        val vehicleCount = vehicleCountSnapshot.toInt()

                        // create a new vehicleDetailsModel instance with the booking count
                        val updatedBookingData = bookingData?.copy(bookingCount = vehicleCount)

                        // add the updated instance to the bList array
                        if (updatedBookingData != null) {
                            bList.add(updatedBookingData)
                        }
                    }

                    val mAdapter = vehicleBookingAdapter(bList)
                    recyclBooking.adapter = mAdapter
                    recyclBooking.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}
