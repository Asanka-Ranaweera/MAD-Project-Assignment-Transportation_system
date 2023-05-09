package com.example.mad_project_assignment_transportation_system.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mad_project_assignment_transportation_system.R


class CustomerRequestAll : AppCompatActivity() {

    private lateinit var cDname: TextView
    private lateinit var cDTitle: TextView
    private lateinit var cDDescription: TextView
    private lateinit var cDPno: TextView
    private lateinit var cccName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_request_all)


    initView()
    setValuesToViews()

}

private fun initView() {
    cDname = findViewById(R.id.cDname)
    cDTitle = findViewById(R.id.cDTitle)
    cDDescription = findViewById(R.id.cDDescription)
    cDPno = findViewById(R.id.cDPno)
    cccName = findViewById(R.id.cccName)


}

private fun setValuesToViews() {


    cccName.text = intent.getStringExtra("name")
    cDname.text = intent.getStringExtra("name")
    cDTitle.text = intent.getStringExtra("p_title")
    cDDescription.text = intent.getStringExtra("p_post")
    cDPno.text = intent.getStringExtra("pno")

}
}