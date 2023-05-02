package com.example.mad_project_assignment_transportation_system

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class own_vehicle_details_page : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_own_vehicle_details_page, container, false)
        //  val btnDisplay = view.findViewById<Button>(R.id.btnDisplay)
        //  val edtName = view.findViewById<EditText>(R.id.edtName)
        //   btnDisplay.setOnClickListener {
//                Toast.makeText(context,"Hello, ${edtName.text.toString()}",Toast.LENGTH_LONG).show()
//            }
        return view
    }
    }


