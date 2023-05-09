package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.adapters.BookingAdapter
import com.example.mad_project_assignment_transportation_system.models.BookingModel
import com.google.firebase.database.*

class MyBookings : AppCompatActivity() {

    private lateinit var btn_newbooking: TextView

    private lateinit var rvBooking: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var bookingList : ArrayList<BookingModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bookings)

        rvBooking = findViewById(R.id.rvPassengerRequest)
        rvBooking.layoutManager = LinearLayoutManager(this)
        rvBooking.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        bookingList = arrayListOf<BookingModel>()

        getBookingData()

        btn_newbooking = findViewById(R.id.btn_newbooking)

        btn_newbooking.setOnClickListener{
            val intent = Intent(this, BookingForm_1::class.java)
            startActivity(intent)
        }
    }

    private fun getBookingData(){
        rvBooking.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Bookings")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bookingList.clear()
                if(snapshot.exists()){
                    for (requestSnap in snapshot.children){
                        val bookingData = requestSnap.getValue(BookingModel::class.java)
                        bookingList.add(bookingData!!)
                    }
                    val mAdapter = BookingAdapter(bookingList)
                    rvBooking.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : BookingAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@MyBookings,BookingCal::class.java)

                            //put extras
                            intent.putExtra("bookingID",bookingList[position].bookingID)
                            intent.putExtra("name",bookingList[position].Name)
                            intent.putExtra("pno",bookingList[position].pno)
                            intent.putExtra("p_point",bookingList[position].p_point)
                            intent.putExtra("d_point",bookingList[position].d_point)
                            intent.putExtra("vno",bookingList[position].vno)
                            intent.putExtra("message",bookingList[position].message)
                            intent.putExtra("spinner_t_days",bookingList[position].spinner_t_days)
                            intent.putExtra("spinner_t_ways",bookingList[position].spinner_t_ways)
                            intent.putExtra("spinner_r_period",bookingList[position].spinner_r_period)

                            startActivity(intent)
                        }

                    })

                    rvBooking.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}