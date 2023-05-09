package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mad_project_assignment_transportation_system.R

class UserTypePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_type_page)



        var txtVOwnerBtn = findViewById<TextView>(R.id.txtVOwnerBtn)
        txtVOwnerBtn.setOnClickListener {
            var intent = Intent(this, vOwner_Register_Page::class.java)
            startActivity(intent)
            finish()
        }


        var txtDrivebtn = findViewById<TextView>(R.id.txtDrivebtn)
        txtDrivebtn.setOnClickListener {
            var intent = Intent(this, driver_registration_page::class.java)
            startActivity(intent)
            finish()
        }


        var txtCusbtn = findViewById<TextView>(R.id.txtCusbtn)
        txtCusbtn.setOnClickListener {
            var intent = Intent(this, CustomerRegister_page::class.java)
            startActivity(intent)
            finish()
        }


    }
}