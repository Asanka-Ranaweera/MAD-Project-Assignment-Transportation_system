package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.adapters.cusAdapter
import com.example.mad_project_assignment_transportation_system.models.customerReqViewmodel
import com.google.firebase.database.*

class CustomerRequest_HomeView : AppCompatActivity() {

    private lateinit var recycView: RecyclerView

    private lateinit var cList: ArrayList<customerReqViewmodel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_request_home_view)

        recycView = findViewById(R.id.recycView)
        recycView.layoutManager = LinearLayoutManager(this)
        recycView.setHasFixedSize(true)

        cList = arrayListOf<customerReqViewmodel>()

        getEmployeesData()

    }

    private fun getEmployeesData() {

        recycView.visibility = View.GONE


        dbRef = FirebaseDatabase.getInstance().getReference("Passenger_Requests")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(customerReqViewmodel::class.java)
                        cList.add(empData!!)
                    }
                    val mAdapter = cusAdapter(cList)
                    recycView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : cusAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@CustomerRequest_HomeView, CustomerRequestAll::class.java)

                            //put extras
                            intent.putExtra("name", cList[position].name)
                            intent.putExtra("p_post", cList[position].p_post)
                            intent.putExtra("p_title", cList[position].p_title)
                            intent.putExtra("pno", cList[position].pno)
                            startActivity(intent)
                        }

                    })

                    recycView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}