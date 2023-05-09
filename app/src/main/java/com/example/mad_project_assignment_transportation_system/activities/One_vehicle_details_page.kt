package com.example.mad_project_assignment_transportation_system.activities

import SessionManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.vehicleDetailsModel
import com.google.firebase.database.FirebaseDatabase


class one_vehicle_details_page : AppCompatActivity() {

    private lateinit var vehicleId: TextView
    private lateinit var tRegNum: TextView
    private lateinit var tVModel: TextView
    private lateinit var tVSeats: TextView
    private lateinit var tVPhoneN: TextView
    private lateinit var tVVehicleType: TextView
    private lateinit var tVStartPoint: TextView
    private lateinit var tVDestination: TextView
    private lateinit var tVStartTimeMor: TextView
    private lateinit var tVArrivalTimeMor: TextView
    private lateinit var tVStartTimeEve: TextView
    private lateinit var tVArrivalTimeEve: TextView
    private lateinit var tVRoute: TextView
    private lateinit var btnVehicleUpdate: Button
    private lateinit var btnVehicleDelete: Button
    private lateinit var sessionManager: SessionManager
    private lateinit var btnBack22:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_vehicle_details_page)





        sessionManager = SessionManager(this)
        initView()
        setValuesToView()



        //Update page navigation
        btnVehicleUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("vehicleId").toString(),
                intent.getStringExtra("vehicleRegNumber").toString()

            )
        }
        //delete button navigation
        btnVehicleDelete.setOnClickListener {
            deleteVehicleRecord(
                intent.getStringExtra("vehicleId").toString(),
            )
        }


        btnBack22.setOnClickListener {
            var intent = Intent(this,Vehicle_OwnerPage1 ::class.java)
            startActivity(intent)
            finish()
        }

    }

    //delete vehicle details

    private fun deleteVehicleRecord(
        vehicleId: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("vehicleDetails").child(vehicleId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Vehicle Details Deleted", Toast.LENGTH_LONG).show()



            val intent = Intent(this, vehicle_details_page::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }



    private fun initView() {

        tRegNum = findViewById(R.id.tRegNum)
        tVModel = findViewById(R.id.tVModel)
        tVPhoneN = findViewById(R.id.tVPhoneN)
        tVVehicleType = findViewById(R.id.tVVehicleType)
        tVSeats = findViewById(R.id.tVSeats)
        tVStartPoint = findViewById(R.id.tVStartPoint)
        tVDestination = findViewById(R.id.tVDestination)
        tVStartTimeMor = findViewById(R.id.tVStartTimeMor)
        tVArrivalTimeMor = findViewById(R.id.tVArrivalTimeMor)
        tVStartTimeEve = findViewById(R.id.tVStartTimeEve)
        tVArrivalTimeEve = findViewById(R.id.tVArrivalTimeEve)
        tVRoute = findViewById(R.id.tVRoute)
        btnVehicleUpdate = findViewById(R.id.btnVehicleUpdate)
        btnVehicleDelete = findViewById(R.id.btnVehicleDelete)
        btnBack22 = findViewById<ImageButton>(R.id.btnBack22)

    }

    private fun setValuesToView(){

        tRegNum.text = intent.getStringExtra("vehicleRegNumber")
        tVModel.text = intent.getStringExtra("VehicleModel")
        tVPhoneN.text = intent.getStringExtra("PhoneNumb")
        tVVehicleType.text = intent.getStringExtra("spinnerType")
        tVSeats.text = intent.getStringExtra("NumOfSeats")
        tVStartPoint.text = intent.getStringExtra("StartingPoint")
        tVDestination.text = intent.getStringExtra("Destination")
        tVStartTimeMor.text = intent.getStringExtra("StartTimeMorning")
        tVArrivalTimeMor.text = intent.getStringExtra("ArrivalTimeMorning")
        tVStartTimeEve.text = intent.getStringExtra("StartTimeEvening")
        tVArrivalTimeEve.text = intent.getStringExtra("ArrivalTimeEvening")
        tVRoute.text = intent.getStringExtra("Route")

    }

    //update Details

    private fun openUpdateDialog(
        vehicleId: String,
        vehicleRegNumber: String

    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_vehicle_details_page, null)

        mDialog.setView(mDialogView)

        val etVNum = mDialogView.findViewById<EditText>(R.id.etVNum)
        val etVModel = mDialogView.findViewById<EditText>(R.id.etVModel)
        val etVSeat = mDialogView.findViewById<EditText>(R.id.etVSeat)
        val etVPNo = mDialogView.findViewById<EditText>(R.id.etVPNo)
        val etVVehicleType = mDialogView.findViewById<EditText>(R.id.etVVehicleType)
        val etVStartP = mDialogView.findViewById<EditText>(R.id.etVStartP)
        val etVDestination = mDialogView.findViewById<EditText>(R.id.etVDestination)
        val etVStimeM = mDialogView.findViewById<EditText>(R.id.etVStimeM)
        val etVAtimeM = mDialogView.findViewById<EditText>(R.id.etVAtimeM)
        val etVStimeE = mDialogView.findViewById<EditText>(R.id.etVStimeE)
        val etVAtimeE = mDialogView.findViewById<EditText>(R.id.etVAtimeE)
        val etVRoute = mDialogView.findViewById<EditText>(R.id.etVRoute)

        val btnUpdateVData = mDialogView.findViewById<Button>(R.id.btnUpdateVData)


        etVNum.setText(intent.getStringExtra("vehicleRegNumber").toString())
        etVModel.setText(intent.getStringExtra("VehicleModel").toString())
        etVSeat.setText(intent.getStringExtra("NumOfSeats").toString())
        etVPNo.setText(intent.getStringExtra("PhoneNumb").toString())
        etVVehicleType.setText(intent.getStringExtra("spinnerType").toString())
        etVStartP.setText(intent.getStringExtra("StartingPoint").toString())
        etVDestination.setText(intent.getStringExtra("Destination").toString())
        etVStimeM.setText(intent.getStringExtra("StartTimeMorning").toString())
        etVAtimeM.setText(intent.getStringExtra("ArrivalTimeMorning").toString())
        etVStimeE.setText(intent.getStringExtra("StartTimeEvening").toString())
        etVAtimeE.setText(intent.getStringExtra("ArrivalTimeEvening").toString())
        etVRoute.setText(intent.getStringExtra("Route").toString())

        //header message
        mDialog.setTitle("Updating $vehicleRegNumber Details")

        val alertDialog = mDialog.create()
        alertDialog.show()

        //update button
        btnUpdateVData.setOnClickListener {
            updateVehicleData(
                vehicleId,
                etVNum.text.toString(),
                etVModel.text.toString(),
                etVSeat.text.toString(),
                etVPNo.text.toString(),
                etVVehicleType.text.toString(),
                etVStartP.text.toString(),
                etVDestination.text.toString(),
                etVStimeM.text.toString(),
                etVAtimeM.text.toString(),
                etVStimeE.text.toString(),
                etVAtimeE.text.toString(),
                etVRoute.text.toString()

            )

            Toast.makeText(applicationContext, "Vehicle Details Updated", Toast.LENGTH_LONG).show()

            //setting updated data to textView

            tRegNum.text = etVNum.text.toString()
            tVModel.text = etVModel.text.toString()
            tVPhoneN.text = etVPNo.text.toString()
            tVVehicleType.text = etVVehicleType.text.toString()
            tVSeats.text = etVSeat.text.toString()
            tVStartPoint.text = etVStartP.text.toString()
            tVDestination.text = etVDestination.text.toString()
            tVStartTimeMor.text = etVStimeM.text.toString()
            tVArrivalTimeMor.text = etVAtimeM.text.toString()
            tVStartTimeEve.text = etVStimeE.text.toString()
            tVArrivalTimeEve.text = etVAtimeE.text.toString()
            tVRoute.text = etVRoute.text.toString()

            alertDialog.dismiss()

        }

    }
    private fun updateVehicleData(
        vehicleId: String,
        vehicleRegNumber: String,
        VehicleModel: String,
        PhoneNumb: String,
        spinnerType: String,
        NumOfSeats: String,
        StartingPoint: String,
        Destination: String,
        StartTimeMorning: String,
        ArrivalTimeMorning: String,
        StartTimeEvening: String,
        ArrivalTimeEvening: String,
        Route: String

    ){

        val dbRef = FirebaseDatabase.getInstance().getReference("vehicleDetails").child(vehicleId)
        val vehicleInfo = vehicleDetailsModel(vehicleId, vehicleRegNumber, VehicleModel, PhoneNumb,spinnerType, NumOfSeats, StartingPoint, Destination, StartTimeMorning, ArrivalTimeMorning, StartTimeEvening, ArrivalTimeEvening, Route,sessionManager.getEmail())
        dbRef.setValue(vehicleInfo)



    }


}
