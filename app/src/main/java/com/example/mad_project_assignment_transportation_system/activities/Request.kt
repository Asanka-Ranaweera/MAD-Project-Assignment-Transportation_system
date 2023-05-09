package com.example.mad_project_assignment_transportation_system.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.PassengerRequestModel

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Request : AppCompatActivity() {

    private lateinit var btn_submitpost: Button
    private lateinit var et_P_name: EditText
    private lateinit var et_P_title: EditText
    private lateinit var et_P_pno: EditText
    private lateinit var et_P_post: EditText

    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        btn_submitpost = findViewById(R.id.btn_login)
        et_P_name = findViewById(R.id.et_P_name)
        et_P_title = findViewById(R.id.et_P_title)
        et_P_pno = findViewById(R.id.et_P_pno)
        et_P_post = findViewById(R.id.et_P_post)

        dbRef = FirebaseDatabase.getInstance().getReference("Passenger_Requests")

        btn_submitpost.setOnClickListener{
            savePassengerRequest()
        }
    }

    private fun savePassengerRequest(){
        //getting values
        val name = et_P_name.text.toString()
        val p_title = et_P_title.text.toString()
        val pno = et_P_pno.text.toString()
        val p_post = et_P_post.text.toString()

        if(name.isEmpty()){
            et_P_name.error = "Please enter Name"
        }
        if(p_title.isEmpty()){
            et_P_title.error = "Please enter the post title"
        }
        if(pno.isEmpty()){
            et_P_pno.error = "Please enter Telphone number"
        }
        if(p_post.isEmpty()){
            et_P_post.error = "Please enter the post content"
        }

        val requestID = dbRef.push().key!!

        val passengerRequest = PassengerRequestModel(requestID, name, p_title, pno, p_post)

        dbRef.child(requestID).setValue(passengerRequest)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted successfully",Toast.LENGTH_LONG).show()

                et_P_name.text.clear()
                et_P_title.text.clear()
                et_P_pno.text.clear()
                et_P_post.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }
    }
}