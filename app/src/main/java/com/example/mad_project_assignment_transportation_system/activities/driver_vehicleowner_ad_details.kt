package com.example.mad_project_assignment_transportation_system.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mad_project_assignment_transportation_system.R

import com.example.mad_project_assignment_transportation_system.models.DriverAdModel
import com.google.firebase.database.FirebaseDatabase

class driver_vehicleowner_ad_details : AppCompatActivity() {

    //initialize varialbles
    private lateinit var adId: TextView
    private lateinit var trasportType: TextView
    private lateinit var vehicleType: TextView
    private lateinit var oName: TextView
    private lateinit var oNum: TextView
    private lateinit var route: TextView
    private lateinit var experience: TextView
    private lateinit var duration: TextView
    private lateinit var btnupdate: Button
    private lateinit var btndel: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_vehicleowner_ad_details)

        //navigation
        var button = findViewById<Button>(R.id.backbtn2)

        button.setOnClickListener{
            val intent = Intent(this, driver_vehicleowner_ad_view_fetch::class.java)
            startActivity(intent)
        }


        initView()
        setValuesToViews()

        btnupdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("adId").toString(),
                intent.getStringExtra("oNames").toString()

            )
        }

        btndel.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("adId").toString()
            )
        }

    }

    private fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Driver Ads").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Advertisement deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, driver_vehicleowner_ad_view_fetch::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{
            error ->
            Toast.makeText(this, "Advertisement not deleted ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(adId: String, oname: String) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.driver_ad_details_update,null)

        mDialog.setView(mDialogView)

        //initialize views
        val adtrasportType = mDialogView.findViewById<TextView>(R.id.adtrasportType)
        val advehicleType = mDialogView.findViewById<TextView>(R.id.advehicleType)
        val adoName = mDialogView.findViewById<TextView>(R.id.adoName)
        val adoNum = mDialogView.findViewById<TextView>(R.id.adoNum)
        val adroute = mDialogView.findViewById<TextView>(R.id.adroute)
        val adexperience = mDialogView.findViewById<TextView>(R.id.adexperience)
        val adduration = mDialogView.findViewById<TextView>(R.id.adduration)

        val btnupdateData = mDialogView.findViewById<TextView>(R.id.btnupdateData)

        //setting data
        adtrasportType.setText(intent.getStringExtra("transport_type").toString())
        advehicleType.setText(intent.getStringExtra("vehicleType").toString())
        adoName.setText(intent.getStringExtra("ownerName").toString())
        adoNum.setText(intent.getStringExtra("ownerNum").toString())
        adroute.setText(intent.getStringExtra("route").toString())
        adexperience.setText(intent.getStringExtra("experience").toString())
        adduration.setText(intent.getStringExtra("duration").toString())

        mDialog.setTitle("Updating $oname Record ")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnupdateData.setOnClickListener{
            updateAdData(
                //get updated data
                adId,
                adtrasportType.text.toString(),
                advehicleType.text.toString(),
                adoName.text.toString(),
                adoNum.text.toString(),
                adroute.text.toString(),
                adexperience.text.toString(),
                adduration.text.toString()
            )

            Toast.makeText(applicationContext, "Advertisement Data Updated", Toast.LENGTH_LONG).show()

            //setting updated data to the text views
            trasportType.text =  adtrasportType.text.toString()
            vehicleType.text = advehicleType.text.toString()
            oName.text = adoName.text.toString()
            oNum.text = adoNum.text.toString()
            route.text =  adroute.text.toString()
            experience.text = adexperience.text.toString()
            duration.text =  adduration.text.toString()

            alertDialog.dismiss()

        }

    }

    private fun updateAdData(
        adId: String,
        transportType: String,
        vehicleType: String,
        oName: String,
        oNum: String,
        route: String,
        experience: String,
        duration: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Driver Ads").child(adId)
        val adInfo = DriverAdModel(adId,transportType,vehicleType,oName,oNum,route,experience,duration)
        dbRef.setValue(adInfo)
    }


    private fun initView() {
        adId = findViewById(R.id.tvadId)
        trasportType = findViewById(R.id.adtrasportType)
        vehicleType = findViewById(R.id.vehicleType)
        oName = findViewById(R.id.oName)
        oNum = findViewById(R.id.oNum)
        route = findViewById(R.id.route)
        experience = findViewById(R.id.experience)
        duration = findViewById(R.id.duration)

        btnupdate = findViewById(R.id.btnupdateData)
        btndel = findViewById(R.id.btndel)
    }

    private fun setValuesToViews() {
        adId.text = intent.getStringExtra("adId")
        trasportType.text = intent.getStringExtra("trasportType")
        vehicleType.text = intent.getStringExtra("vehicleType")
        oName.text = intent.getStringExtra("ownerName")
        oNum.text = intent.getStringExtra("ownerNum")
        route.text = intent.getStringExtra("route")
        experience.text = intent.getStringExtra("experience")
        duration.text = intent.getStringExtra("duration")

    }
}