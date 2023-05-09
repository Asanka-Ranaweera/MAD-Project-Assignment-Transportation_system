package com.example.mad_project_assignment_transportation_system.activities

import SessionManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.vehicleDetailsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class add_new_vehicle_page : Fragment() {

    private lateinit var addVehicleRegNumber: EditText
    private lateinit var addVehicleModel: EditText
    private lateinit var addPhoneNumb: EditText
    private lateinit var spinnerType: Spinner
    private lateinit var addNumOfSeats: EditText
    private lateinit var addStartingPoint: EditText
    private lateinit var addDestination: EditText
    private lateinit var addStartTimeMorning: EditText
    private lateinit var addArrivalTimeMorning: EditText
    private lateinit var addStartTimeEvening: EditText
    private lateinit var addArrivalTimeEvening: EditText
    private lateinit var addRoute: EditText
    private lateinit var userEmail: String
    private lateinit var btnRegister: Button

    private lateinit var dbRef: DatabaseReference
    private lateinit var sessionManager : SessionManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        class HomeFragment : Fragment() {
            override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {





                // Inflate the layout for this fragment
                val view = inflater.inflate(R.layout.fragment_own_vehicle_details_page, container, false)

                return view
            }

        }
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_new_vehicle_page, container, false)




        addVehicleRegNumber = view.findViewById(R.id.addVehicleRegNumber)
        addVehicleModel = view.findViewById(R.id.addVehicleModel)
        addPhoneNumb = view.findViewById(R.id.addPhoneNumb)
        addNumOfSeats = view.findViewById(R.id.addNumOfSeats)
        addStartingPoint = view.findViewById(R.id.addStartingPoint)
        addDestination = view.findViewById(R.id.addDestination)
        addStartTimeMorning = view.findViewById(R.id.addStartTimeMorning)
        addArrivalTimeMorning = view.findViewById(R.id.addArrivalTimeMorning)
        addStartTimeEvening = view.findViewById(R.id.addStartTimeEvening)
        addArrivalTimeEvening = view.findViewById(R.id.addArrivalTimeEvening)
        addRoute = view.findViewById(R.id.addRoute)
        btnRegister = view.findViewById(R.id.btnRegister)
        spinnerType = view.findViewById<Spinner>(R.id.spinnerType)

        dbRef = FirebaseDatabase.getInstance().getReference("vehicleDetails")

        btnRegister.setOnClickListener {
            saveVehicleData()
        }

        return view

    }


    private fun saveVehicleData(){
    //getting values
        val vehicleRegNumber =addVehicleRegNumber.text.toString()
        val VehicleModel =addVehicleModel.text.toString()
        val PhoneNumb = addPhoneNumb.text.toString()
        val NumOfSeats =addNumOfSeats.text.toString()
        val StartingPoint =addStartingPoint.text.toString()
        val Destination =addDestination.text.toString()
        val StartTimeMorning =addStartTimeMorning.text.toString()
        val ArrivalTimeMorning =addArrivalTimeMorning.text.toString()
        val StartTimeEvening =addStartTimeEvening.text.toString()
        val ArrivalTimeEvening =addArrivalTimeEvening.text.toString()
        val Route =addRoute.text.toString()




        if(vehicleRegNumber.isEmpty()){
            addVehicleRegNumber.error = "Please Enter Register Number"
        } else if(VehicleModel.isEmpty()){
            addVehicleModel.error = "Please Enter Vehicle Model"
        } else if(PhoneNumb.isEmpty() ){
            addPhoneNumb.error = "Please Enter Phone Number"
        } else if(PhoneNumb.length != 10) {
            addPhoneNumb.error = "Phone Number must be exactly 10 digits"
        } else if(NumOfSeats.isEmpty()){
            addNumOfSeats.error = "Please Enter Seat Amount"
        } else if(StartingPoint.isEmpty()){
            addStartingPoint.error = "Please Enter Starting Point"
        } else if(Destination.isEmpty()){
            addDestination.error = "Please Enter Destination"
        } else if(StartTimeMorning.isEmpty()){
            addStartTimeMorning.error = "Please Enter Start Time"
        } else if(ArrivalTimeMorning.isEmpty()){
            addArrivalTimeMorning.error = "Please Enter Arrival Time"
        } else if(StartTimeEvening.isEmpty()){
            addStartTimeEvening.error = "Please Enter Departure Time "
        } else if(ArrivalTimeEvening.isEmpty()){
            addArrivalTimeEvening.error = "Please Enter Arrival Time"
        } else if(Route.isEmpty()){
            addRoute.error = "Please Enter Route"
        } else {
            val vehicleId = dbRef.push().key!!

        // session Email input
            sessionManager = SessionManager(requireContext())
            userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""



            val vehicles = vehicleDetailsModel(vehicleId, vehicleRegNumber, VehicleModel, PhoneNumb, NumOfSeats, StartingPoint, Destination, StartTimeMorning, ArrivalTimeMorning, StartTimeEvening, ArrivalTimeEvening, Route,spinnerType.selectedItem.toString(),userEmail)
            dbRef.child(vehicleId).setValue(vehicles)
                 .addOnCompleteListener{
                 Toast.makeText(context, "Vehicle Added Successfully", Toast.LENGTH_SHORT).show()
                     addVehicleRegNumber.text.clear()
                     addVehicleModel.text.clear()
                     addPhoneNumb.text.clear()
                     addNumOfSeats.text.clear()
                     addStartingPoint.text.clear()
                     addDestination.text.clear()
                     addStartTimeMorning.text.clear()
                     addArrivalTimeMorning.text.clear()
                     addStartTimeEvening.text.clear()
                     addArrivalTimeEvening.text.clear()
                     addRoute.text.clear()


                 }.addOnFailureListener{ err ->
                Toast.makeText(context, "Error ${err.message}", Toast.LENGTH_SHORT).show()
            }

            }
    }
}