package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.mad_project_assignment_transportation_system.R

class PassengerProfile : AppCompatActivity() {

    private lateinit var btn_bookings: LinearLayout
    private lateinit var btn_myads: LinearLayout
//    private lateinit var btn_feedbacks: LinearLayout
//    private lateinit var btn_notifications: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_profile)

        btn_bookings = findViewById(R.id.btn_bookings)
        btn_myads = findViewById(R.id.btn_myads)
//        btn_feedbacks = findViewById(R.id.btn_feedbacks)
//        btn_notifications = findViewById(R.id.btn_notifications)

        btn_bookings.setOnClickListener{
            val intent = Intent(this, MyBookings::class.java)
            startActivity(intent)
        }

        btn_myads.setOnClickListener{
            val intent = Intent(this, Advertistments::class.java)
            startActivity(intent)
        }

//        btn_feedbacks.setOnClickListener{
//            val intent = Intent(this, MyFeedbacks::class.java)
//            startActivity(intent)
//        }

//        btn_notifications.setOnClickListener{
//            val intent = Intent(this, PassengerProfile::class.java)
//            startActivity(intent)
//        }

    }
}