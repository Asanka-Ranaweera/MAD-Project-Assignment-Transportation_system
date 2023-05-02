package com.example.mad_project_assignment_transportation_system

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()



        var btnStart = findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener {
            var intent = Intent(this,loginPage1::class.java)
            startActivity(intent)
            finish()
        }









    }
}