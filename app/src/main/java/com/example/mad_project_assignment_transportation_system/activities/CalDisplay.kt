package com.example.mad_project_assignment_transportation_system.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.R
import com.google.firebase.database.FirebaseDatabase

class CalDisplay : AppCompatActivity() {

//    private lateinit var tvBookingID: TextView
//    private lateinit var tvRperiod: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal_display)

        var et_Ans = findViewById<TextView>(R.id.txtAnswer)
        et_Ans.setText(intent.getDoubleExtra("answer",0.0).toString())

        var btn_cancel = findViewById<Button>(R.id.btn_back)
        btn_cancel.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//        initView()
//        setValuesToViews()

    }

//    private fun initView() {
//        tvBookingID = findViewById(R.id.tvBookingID)
//        tvRperiod = findViewById(R.id.tvRperiod)
//    }
//
//    private fun setValuesToViews(){
//
//        tvBookingID.text = intent.getStringExtra("bookingID")
//        tvRperiod.text = intent.getStringExtra("spinner_t_ways")
//
//    }


}