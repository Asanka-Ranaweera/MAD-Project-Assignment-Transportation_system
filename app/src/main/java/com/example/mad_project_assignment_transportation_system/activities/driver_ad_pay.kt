package com.example.mad_project_assignment_transportation_system.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mad_project_assignment_transportation_system.R


class driver_ad_pay : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_ad_pay)

        //val button: Button=findViewById<Button>(R.id.button2)

        //spinner duration
//        val duration = arrayOf("1 week","2 weeks","1 month")
        //val spinner2 = findViewById<Spinner>(R.id.spinner)

//        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,duration)
//
//        spinner2.adapter = arrayAdapter
//        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Toast.makeText(applicationContext,"Your total payment is "+ duration,Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }

//        button.setOnClickListener{
//            when(spinner2.selectedItem){
//                "1 month" -> {TextView.text= "Your Total is 3000"}
//                "3 months"->{TextView.text ="Your Total is 5000"}
//            }
//        }
    }
}