package com.example.mad_project_assignment_transportation_system.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.mad_project_assignment_transportation_system.R

class vehicle_details_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details_page)




        val imgHome: ImageView = findViewById(R.id.btnYourBus)
        val imgUser: ImageView = findViewById(R.id.btnAddNew)
        val fragmentHome = own_vehicle_details_page()
        val fragmentUser = add_new_vehicle_page()


        imgUser.setOnClickListener {
            imgHome.setImageResource(R.drawable.ic_unselected_bus11)
            imgUser.setImageResource(R.drawable.ic_add_bus)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragmentUser)
                commit()
            }
        }

        imgHome.setOnClickListener {
            imgHome.setImageResource(R.drawable.ic_bus1)
            imgUser.setImageResource(R.drawable.ic_baseline_add_box_24)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragmentHome)
                commit()
            }
        }


    }
}