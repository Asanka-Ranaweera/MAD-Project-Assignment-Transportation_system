package com.example.mad_project_assignment_transportation_system.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.mad_project_assignment_transportation_system.R

import org.w3c.dom.Text

class driverProfileActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_profile)

        val linearLayout = findViewById<View>(R.id.myads)

        // set an OnClickListener to handle the click event
        linearLayout.setOnClickListener {
            // create an Intent to start the new activity
            val intent = Intent(this, AdlistActivity::class.java)
            startActivity(intent)
        }

        // handle the click event for the LinearLayout view
        fun onLinearLayoutClicked(view: View) {
            // create an Intent to start the new activity
            val intent = Intent(this, AdlistActivity::class.java)
            startActivity(intent)
        }

    }


    }
