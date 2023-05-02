package com.example.mad_project_assignment_transportation_system

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class add_new_vehicle_page : Fragment() {

    private lateinit var addVehicleRegNumber: EditText
    private lateinit var btnRegister: Button

    private lateinit var dbRef: DatabaseReference


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
        btnRegister = view.findViewById(R.id.btnRegister)
//       btnRegister.setOnClickListener { Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show() }
        dbRef = FirebaseDatabase.getInstance().getReference("VehicleDetails")


        btnRegister.setOnClickListener {

            saveVehicleData()

        }


        return view

    }


    private fun saveVehicleData(){
    //getting values



        val vehicleRegNumber =addVehicleRegNumber.text.toString()


        if(vehicleRegNumber.isEmpty()){
            addVehicleRegNumber.error = "Please Enter Register Number"
        }
else{
        val vehicleId = dbRef.push().key!!


            val vehicles = vehicleDetailsModel(vehicleId, vehicleRegNumber)
        dbRef.child(vehicleId).setValue(vehicles)
            .addOnCompleteListener{
                Toast.makeText(context, "Data Added Successfully", Toast.LENGTH_SHORT).show()
                addVehicleRegNumber.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(context, "Error ${err.message}", Toast.LENGTH_SHORT).show()
            }
}

    }





}