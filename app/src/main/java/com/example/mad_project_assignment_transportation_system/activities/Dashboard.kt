package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.mad_project_assignment_transportation_system.R

class Dashboard : AppCompatActivity() {

    private lateinit var p_request:LinearLayout
    private lateinit var v_ads:LinearLayout
    private lateinit var btn_profile:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        p_request = findViewById(R.id.p_request)
        v_ads = findViewById(R.id.v_ads)
        btn_profile = findViewById(R.id.btn_profile)

        p_request.setOnClickListener{
            val intent = Intent(this, Request::class.java)
            startActivity(intent)
        }

        //vehicle ads by asanka aiya
        v_ads.setOnClickListener{
            val intent = Intent(this, DisplayRequest::class.java)
            startActivity(intent)
        }

        btn_profile.setOnClickListener{
            val intent = Intent(this, PassengerProfile::class.java)
            startActivity(intent)
        }

    }
}