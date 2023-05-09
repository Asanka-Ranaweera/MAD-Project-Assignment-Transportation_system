package com.example.mad_project_assignment_transportation_system.activities

import SessionManager
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.adapters.vehicleAdapter
import com.example.mad_project_assignment_transportation_system.models.vehicleDetailsModel
import com.google.firebase.database.*


class own_vehicle_details_page : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicleList: ArrayList<vehicleDetailsModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var sessionManager : SessionManager
    private lateinit var btnBackVDash: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_own_vehicle_details_page, container, false)





        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvVehicleList)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)




        vehicleList = arrayListOf<vehicleDetailsModel>()

        //session
        sessionManager = SessionManager(requireContext())


        getVehicleDetails()



        return view

    }

    private fun getVehicleDetails(){
        recyclerView.visibility = View.GONE



        dbRef = FirebaseDatabase.getInstance().getReference("vehicleDetails")

        val userEmail = sessionManager.getSession().get(SessionManager.KEY_EMAIL)

        dbRef.orderByChild("userEmail").equalTo(userEmail).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                vehicleList.clear()
                if(snapshot.exists()){
                    for(vehicleSnape in snapshot.children){
                        val vehiData =vehicleSnape.getValue(vehicleDetailsModel::class.java)
                        vehicleList.add(vehiData!!)
                    }
                    val mAdapter = vehicleAdapter(vehicleList)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : vehicleAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                          val intent = Intent(this@own_vehicle_details_page.context, one_vehicle_details_page::class.java )


                            intent.putExtra("vehicleId", vehicleList[position].vehicleId)         //add extra details of vehicle
                            intent.putExtra("vehicleRegNumber", vehicleList[position].vehicleRegNumber)
                            intent.putExtra("VehicleModel", vehicleList[position].VehicleModel)
                            intent.putExtra("PhoneNumb", vehicleList[position].PhoneNumb)
                            intent.putExtra("NumOfSeats", vehicleList[position].NumOfSeats)
                            intent.putExtra("spinnerType", vehicleList[position].spinnerType)
                            intent.putExtra("StartingPoint", vehicleList[position].StartingPoint)
                            intent.putExtra("Destination", vehicleList[position].Destination)
                            intent.putExtra("StartTimeMorning", vehicleList[position].StartTimeMorning)
                            intent.putExtra("ArrivalTimeMorning", vehicleList[position].ArrivalTimeMorning)
                            intent.putExtra("StartTimeEvening", vehicleList[position].StartTimeEvening)
                            intent.putExtra("ArrivalTimeEvening", vehicleList[position].ArrivalTimeEvening)
                            intent.putExtra("Route", vehicleList[position].Route)

                            startActivity(intent)

                        }

                    })

                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}


