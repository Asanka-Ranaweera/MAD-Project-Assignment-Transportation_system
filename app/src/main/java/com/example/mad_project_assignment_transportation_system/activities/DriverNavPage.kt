package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mad_project_assignment_transportation_system.R

class driverNavPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_nav_page)



        var textPost1 = findViewById<TextView>(R.id.textPost1)
        textPost1.setOnClickListener {
            var intent = Intent(this, driver_vehicleowner_ad::class.java)
            startActivity(intent)
            finish()
        }
        var textPost2 = findViewById<TextView>(R.id.textPost2)
        textPost2.setOnClickListener {
            var intent = Intent(this, driver_vehicleowner_ad_view_fetch::class.java)
            startActivity(intent)
            finish()
        }





    }
}