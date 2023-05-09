package com.example.mad_project_assignment_transportation_system.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.DriverAdModel

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class driver_vehicleowner_ad : AppCompatActivity() {

    //initialize varialbles
    private lateinit var trasportType: EditText
    private lateinit var vehicleType: EditText
    private lateinit var oName: EditText
    private lateinit var oNum: EditText
    private lateinit var route: EditText
    private lateinit var experience: EditText
    private lateinit var duration: EditText
    private lateinit var save: Button

    //db
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_vehicleowner_ad)

        var button = findViewById<Button>(R.id.backbtn)
        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //initialization
        trasportType = findViewById(R.id.transtypetxt)
        vehicleType = findViewById(R.id.vehicletxt)
        oName = findViewById(R.id.editTextTextPersonName9)
        oNum = findViewById(R.id.editTextTextPersonName3)
        route = findViewById(R.id.editTextTextPersonName6)
        experience = findViewById(R.id.editTextTextPersonName5)
        duration = findViewById(R.id.durationtxt)
        save = findViewById(R.id.button8)

        dbRef = FirebaseDatabase.getInstance().getReference("Driver Ads")

        save.setOnClickListener{
            saveAdData()
        }



        //navigate to driver_ad_pay
//        var button8 = findViewById<Button>(R.id.button8)
//
//        button8.setOnClickListener{
//            val intent2 = Intent(this,driver_ad_pay::class.java)
//            startActivity(intent2)
//        }

        //spinner transportType
//        val spinner: Spinner = findViewById(R.id.spinner2)

        //spinner vType
//        val spinner1: Spinner = findViewById(R.id.spinner3)


    }

    private fun saveAdData() {
        //getting values
        val trasportTypes = trasportType.text.toString()
        val vehicleTypes = vehicleType.text.toString()
        val oNames = oName.text.toString()
        val oNums = oNum.text.toString()
        val routes1 = route.text.toString()
        val experiences = experience.text.toString()
        val durations = duration.text.toString()

        //validations
        if(trasportTypes.isEmpty()){
            trasportType.error="Please enter transport type"
        }

        if(vehicleTypes.isEmpty()){
            vehicleType.error="Please enter transport type"
        }

        if(oNames.isEmpty()){
            oName.error="Please enter transport type"
        }

        if(oNums.isEmpty()){
            oNum.error="Please enter transport type"
        }

        if(routes1.isEmpty()){
            route.error="Please enter transport type"
        }

        if(experiences.isEmpty()){
            experience.error="Please enter transport type"
        }

        if(durations.isEmpty()){
            duration.error="Please enter transport type"
        }

        val adId = dbRef.push().key!!

        val driverad = DriverAdModel(adId,trasportTypes,vehicleTypes,oNames,oNums,routes1,experiences,durations)

        dbRef.child(adId).setValue((driverad))
            .addOnCompleteListener{
                Toast.makeText(this, "Advertisement is posted" , Toast.LENGTH_LONG).show()
            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}" , Toast.LENGTH_LONG).show()
            }
    }


}