package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mad_project_assignment_transportation_system.R

//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//      val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()



        var getStart_btn = findViewById<Button>(R.id.getStart_btn)
        getStart_btn.setOnClickListener {
            var intent = Intent(this, loginPage1::class.java)
            startActivity(intent)
            finish()
        }









    }
}