package com.example.mad_project_assignment_transportation_system

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class loginPage1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page1)

        ////////////////////////////////////////////////////
        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            var intent = Intent(this,Vehicle_OwnerPage1::class.java)
            startActivity(intent)
            finish()
        }


    }
}