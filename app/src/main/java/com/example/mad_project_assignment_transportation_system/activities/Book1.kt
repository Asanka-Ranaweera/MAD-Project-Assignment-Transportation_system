package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.R

import com.example.mad_project_assignment_transportation_system.models.Calculator
import com.google.firebase.database.FirebaseDatabase

class Book1 : AppCompatActivity() {

    private lateinit var tvBookingID: TextView
    private lateinit var tvVNo: TextView
    private lateinit var tvName: TextView
    private lateinit var tvPNo: TextView
    private lateinit var tvPpoint: TextView
    private lateinit var tvDpoint: TextView
    private lateinit var tvTdays: TextView
    private lateinit var tvTways: TextView
    private lateinit var tvRperiod: TextView
    private lateinit var tvMessage: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book1)

        initView()
        setValuesToViews()

//        btnUpdate.setOnClickListener {
//            openUpdateDialog(
//                intent.getStringExtra("requestID").toString(),
//                intent.getStringExtra("name").toString(),
//                intent.getStringExtra("requestID").toString(),
//                intent.getStringExtra("name").toString(),
//                intent.getStringExtra("requestID").toString(),
//                intent.getStringExtra("name").toString(),
//                intent.getStringExtra("requestID").toString(),
//                intent.getStringExtra("name").toString(),
//                intent.getStringExtra("requestID").toString(),
//                intent.getStringExtra("name").toString()
//
//            )
//        }
//
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("bookingID").toString()
            )
        }
    }

    private fun initView() {
        tvBookingID = findViewById(R.id.tvBookingID)
        tvVNo = findViewById(R.id.tvVNo)
        tvName = findViewById(R.id.tvName)
        tvPNo = findViewById(R.id.tvPNo)
        tvPpoint = findViewById(R.id.tvPpoint)
        tvDpoint = findViewById(R.id.tvDpoint)
        tvTdays = findViewById(R.id.tvTdays)
        tvTways = findViewById(R.id.tvTways)
        tvRperiod = findViewById(R.id.tvRperiod)
        tvMessage = findViewById(R.id.tvMessage)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){

        tvBookingID.text = intent.getStringExtra("bookingID")
        tvVNo.text = intent.getStringExtra("vno")
        tvName.text = intent.getStringExtra("Name")
        tvPNo.text = intent.getStringExtra("pno")
        tvPpoint.text = intent.getStringExtra("p_point")
        tvDpoint.text = intent.getStringExtra("d_point")
        tvTdays.text = intent.getStringExtra("d_point")
        tvTways.text = intent.getStringExtra("spinner_t_days")
        tvRperiod.text = intent.getStringExtra("spinner_t_ways")
        tvMessage.text = intent.getStringExtra("spinner_r_period")

    }

//    private fun openUpdateDialog(
//        requestID: String,
//        name: String
//    ) {
//        val mDialog = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        val mDialogView = inflater.inflate(R.layout.update_dialog, null)
//
//        mDialog.setView(mDialogView)
//
//        val etName = mDialogView.findViewById<EditText>(R.id.etName)
//        val etPtitle = mDialogView.findViewById<EditText>(R.id.etPtitle)
//        val etpno = mDialogView.findViewById<EditText>(R.id.etpno)
//        val etpost = mDialogView.findViewById<EditText>(R.id.etpost)
//        val etName = mDialogView.findViewById<EditText>(R.id.etName)
//        val etPtitle = mDialogView.findViewById<EditText>(R.id.etPtitle)
//        val etpno = mDialogView.findViewById<EditText>(R.id.etpno)
//        val etpost = mDialogView.findViewById<EditText>(R.id.etpost)
//        val etpost = mDialogView.findViewById<EditText>(R.id.etpost)
//
//        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)
//
//        etName.setText(intent.getStringExtra("name").toString())
//        etPtitle.setText(intent.getStringExtra("p_title").toString())
//        etpno.setText(intent.getStringExtra("pno").toString())
//        etpost.setText(intent.getStringExtra("p_post").toString())
//        etName.setText(intent.getStringExtra("name").toString())
//        etPtitle.setText(intent.getStringExtra("p_title").toString())
//        etpno.setText(intent.getStringExtra("pno").toString())
//        etpost.setText(intent.getStringExtra("p_post").toString())
//
//        mDialog.setTitle("Updating $name Passenger Request")
//
//        val alertDialog = mDialog.create()
//        alertDialog.show()
//
//        btnUpdateData.setOnClickListener {
//            updateRequestData(
//                requestID,
//                etName.text.toString(),
//                etPtitle.text.toString(),
//                etpno.text.toString(),
//                etpost.text.toString() ,
//                requestID,
//                etName.text.toString(),
//                etPtitle.text.toString(),
//                etpno.text.toString(),
//                etpost.text.toString()
//            )
//
//            Toast.makeText(applicationContext, "Passenger Request Post Data Updated", Toast.LENGTH_LONG).show()
//
//            //we are setting updated data to our textviews
//            tvName.text = etName.text.toString()
//            tvPTitle.text = etPtitle.text.toString()
//            tvPNo.text = etpno.text.toString()
//            tvPost.text = etpost.text.toString()
//
//            alertDialog.dismiss()
//        }
//    }

    //    private fun updateRequestData(
//        requestID: String,
//        name: String,
//        title: String,
//        pno: String,
//        post: String
//    ) {
//        val dbRef = FirebaseDatabase.getInstance().getReference("Passenger_Requests").child(requestID)
//        val requestInfo = PassengerRequestModel(requestID,name ,title, pno, post)
//        dbRef.setValue(requestInfo)
//    }
//
    private fun deleteRecord(
        bookingID: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Bookings").child(bookingID)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Passenger Booking deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this,MyBookings::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}