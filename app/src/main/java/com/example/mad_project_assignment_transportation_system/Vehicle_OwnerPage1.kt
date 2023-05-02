package com.example.mad_project_assignment_transportation_system

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class Vehicle_OwnerPage1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_owner_page1)


    //for "Ride" navigation

        var btnRide = findViewById<ImageButton>(R.id.btnRide)
        btnRide.setOnClickListener {
            var intent = Intent(this,vehicle_details_page::class.java)
            startActivity(intent)
            finish()
        }
        var txtRide = findViewById<TextView>(R.id.txtRide)
        txtRide.setOnClickListener {
            var intent = Intent(this,vehicle_details_page::class.java)
            startActivity(intent)
            finish()
        }

        // Booking navigation

        var btnBooking = findViewById<ImageButton>(R.id.btnBooking)
        btnBooking.setOnClickListener {
            var intent = Intent(this,view_bookinglist_page::class.java)
            startActivity(intent)
            finish()
        }

        var txtBooking = findViewById<TextView>(R.id.txtBooking)
        txtBooking.setOnClickListener {
            var intent = Intent(this,view_bookinglist_page::class.java)
            startActivity(intent)
            finish()
        }

        //Charges navigation

        var btnCharges = findViewById<ImageButton>(R.id.btnCharges)
        btnCharges.setOnClickListener {
            var intent = Intent(this,view_bookinglist_page::class.java)
            startActivity(intent)
            finish()
        }

        var txtCharges = findViewById<TextView>(R.id.txtCharges)
        txtCharges.setOnClickListener {
            var intent = Intent(this,Register_Charge_Page::class.java)
            startActivity(intent)
            finish()
        }


    }
}