package com.example.mad_project_assignment_transportation_system.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.PassengerRequestModel
import com.google.firebase.database.FirebaseDatabase

class RequestDetails : AppCompatActivity() {

    private lateinit var tvRequestId: TextView
    private lateinit var tvName: TextView
    private lateinit var tvPTitle: TextView
    private lateinit var tvPNo: TextView
    private lateinit var tvPost: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("requestID").toString(),
                intent.getStringExtra("name").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("requestID").toString()
            )
        }
    }

    private fun initView() {
        tvRequestId = findViewById(R.id.tvBookingID)
        tvName = findViewById(R.id.tvName)
        tvPTitle = findViewById(R.id.tvPTitle)
        tvPNo = findViewById(R.id.tvPNo)
        tvPost = findViewById(R.id.tvPost)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){

        tvRequestId.text = intent.getStringExtra("requestID")
        tvName.text = intent.getStringExtra("name")
        tvPTitle.text = intent.getStringExtra("p_title")
        tvPNo.text = intent.getStringExtra("pno")
        tvPost.text = intent.getStringExtra("p_post")

    }

    private fun openUpdateDialog(
        requestID: String,
        name: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etName = mDialogView.findViewById<EditText>(R.id.etName)
        val etPtitle = mDialogView.findViewById<EditText>(R.id.etPtitle)
        val etpno = mDialogView.findViewById<EditText>(R.id.etpno)
        val etpost = mDialogView.findViewById<EditText>(R.id.etpost)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etName.setText(intent.getStringExtra("name").toString())
        etPtitle.setText(intent.getStringExtra("p_title").toString())
        etpno.setText(intent.getStringExtra("pno").toString())
        etpost.setText(intent.getStringExtra("p_post").toString())

        mDialog.setTitle("Updating $name Passenger Request")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateRequestData(
                requestID,
                etName.text.toString(),
                etPtitle.text.toString(),
                etpno.text.toString(),
                etpost.text.toString()
            )

            Toast.makeText(applicationContext, "Passenger Request Post Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvName.text = etName.text.toString()
            tvPTitle.text = etPtitle.text.toString()
            tvPNo.text = etpno.text.toString()
            tvPost.text = etpost.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateRequestData(
        requestID: String,
        name: String,
        title: String,
        pno: String,
        post: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Passenger_Requests").child(requestID)
        val requestInfo = PassengerRequestModel(requestID,name ,title, pno, post)
        dbRef.setValue(requestInfo)
    }

    private fun deleteRecord(
        requestID: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Passenger_Requests").child(requestID)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Passenger Request Post deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this,DisplayRequest::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }


}