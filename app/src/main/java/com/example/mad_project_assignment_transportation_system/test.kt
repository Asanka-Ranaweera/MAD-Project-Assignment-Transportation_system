package com.example.mad_project_assignment_transportation_system

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class test : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var btnasa: Button

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        edtName = findViewById(R.id.edtName)
        btnasa = findViewById(R.id.btnasa)

        dbRef = FirebaseDatabase.getInstance().getReference("asanka")

        btnasa.setOnClickListener {

            saveVehicleData()

        }



    }


    private fun saveVehicleData(){
        //getting values



        val vehicleRegNumber =edtName.text.toString()


        if(vehicleRegNumber.isEmpty()){
            edtName.error = "Please Enter Register Number"
        }
        else{
            val vehicleId = dbRef.push().key!!


            val vehicles = vehicleDetailsModel(vehicleId, vehicleRegNumber)
            dbRef.child(vehicleId).setValue(vehicles)
                .addOnCompleteListener{

                    edtName.text.clear()

                }.addOnFailureListener{ err ->

                }
        }

    }

}