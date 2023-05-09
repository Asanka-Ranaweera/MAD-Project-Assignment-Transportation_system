package com.example.mad_project_assignment_transportation_system.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mad_project_assignment_transportation_system.R

import com.example.mad_project_assignment_transportation_system.models.BookingModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class BookingForm_1 : AppCompatActivity() {

    private lateinit var spinner_vno: Spinner
    private lateinit var et_Name: EditText
    private lateinit var et_pno: EditText
    private lateinit var et_p_point: EditText
    private lateinit var et_d_point: EditText
    private lateinit var et_message: EditText
    private lateinit var btn_cancel: Button
    private lateinit var btn_submit: Button
    private lateinit var spinner_t_days: Spinner
    private lateinit var spinner_t_ways: Spinner
    private lateinit var spinner_r_period: Spinner

    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(com.example.mad_project_assignment_transportation_system.R.layout.activity_booking_form1)

        btn_submit = findViewById(R.id.btn_submit)
        spinner_vno = findViewById<View>(R.id.spinner_vno) as Spinner
        et_Name = findViewById(R.id.et_Name)
        et_pno = findViewById(R.id.et_pno)
        et_p_point = findViewById(R.id.et_p_point)
        et_d_point = findViewById(R.id.et_d_point)
        et_message = findViewById(R.id.et_message)
        spinner_t_days = findViewById<View>(R.id.spinner_t_days) as Spinner
        spinner_t_ways = findViewById<View>(R.id.spinner_t_ways) as Spinner
        spinner_r_period = findViewById<View>(R.id.spinner_r_period) as Spinner

        dbRef = FirebaseDatabase.getInstance().getReference("Bookings")

        btn_submit.setOnClickListener{
            saveBooking()

        }
    }

    private fun saveBooking(){
        //getting values
        val vno = spinner_vno.selectedItem.toString()
        val Name = et_Name.text.toString()
        val pno = et_pno.text.toString()
        val p_point = et_p_point.text.toString()
        val d_point = et_d_point.text.toString()
        val message = et_message.text.toString()
        val spinner_t_days = spinner_t_days.selectedItem.toString()
        val spinner_t_ways = spinner_t_ways.selectedItem.toString()
        val spinner_r_period = spinner_r_period.selectedItem.toString()

        if(Name.isEmpty()){
            et_Name.error = "Please enter Name"
        }
        if(pno.isEmpty()){
            et_pno.error = "Please enter Telephone number"
        }
        if(pno.length > 10){
            et_pno.error = "Please enter Telephone number"
        }
        if(p_point.isEmpty()){
            et_p_point.error = "Please enter the pick up point"
        }
        if(d_point.isEmpty()){
            et_d_point.error = "Please enter the drop point"
        }

        val bookingID = dbRef.push().key!!

        val Booking = BookingModel(bookingID, vno, Name, pno, p_point, d_point,message,spinner_t_days,spinner_t_ways,spinner_r_period)

        dbRef.child(bookingID).setValue(Booking)
            .addOnCompleteListener{
                Toast.makeText(this, "Booking Data inserted successfully", Toast.LENGTH_LONG).show()

                //spinner_vno.selectedItem.clear()
                et_Name.text.clear()
                et_pno.text.clear()
                et_p_point.text.clear()
                et_d_point.text.clear()
                et_message.text.clear()

                //val intent = Intent(this, MyBookings::class.java)
                //startActivity(intent)
                //finish()


            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }


}