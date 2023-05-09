package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.adapters.adAdapter
import com.google.firebase.database.*


// Declaring AdlistActivity class which extends AppCompatActivity
class AdlistActivity : AppCompatActivity() {

    // Initializing lateinit variables for database reference, recycler view and user data list
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<user>

    // Overriding onCreate method of AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting the layout file for the activity
        setContentView(R.layout.activity_adlist)

        // Assigning the RecyclerView from layout file to the userRecyclerView variable and setting its layout manager
        userRecyclerView = findViewById(R.id.adView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        // Initializing the userArrayList to an empty ArrayList
        userArrayList = arrayListOf<user>()

        // Calling the method to get the data from the Firebase database
        getUserData()

    }

    // Method to get data from Firebase database and populate the userArrayList and userRecyclerView
    private fun getUserData() {

        // Getting the reference to the "Driver Ads" node in Firebase database
        dbref = FirebaseDatabase.getInstance().getReference("Driver Ads")

        // Adding a ValueEventListener to the database reference to listen for changes in the data
        dbref.addValueEventListener(object : ValueEventListener {


            // Overriding onDataChange method of ValueEventListener to handle changes in the data
            override fun onDataChange(snapshot: DataSnapshot) {

                // Checking if the snapshot exists
                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(user::class.java)
                        userArrayList.add(user!!)

                    }

                    // Setting the adapter for userRecyclerView to display the data in the userArrayList
                    userRecyclerView.adapter = adAdapter(userArrayList)


                }

            }

            // Overriding onCancelled method of ValueEventListener to handle errors in data retrieval
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }
}