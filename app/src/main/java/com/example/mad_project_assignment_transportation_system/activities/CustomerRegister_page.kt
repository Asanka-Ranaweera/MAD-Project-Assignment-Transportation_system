package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.activities.loginPage1
import com.example.mad_project_assignment_transportation_system.models.VehicleOwnerModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CustomerRegister_page : AppCompatActivity() {

    private lateinit var edtVNameC: EditText
    private lateinit var edtVAgeC: EditText
    private lateinit var edtVphonNoC: EditText

    private lateinit var edtVEmailC: EditText
    private lateinit var edtVPwdC: EditText
    private lateinit var editVConfirmPwdC: EditText
    private lateinit var edtVNationalC: EditText
    private lateinit var rollTypeCustomer: TextView

    private lateinit var VCRegisterButton: Button
    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_register_page)

        edtVNameC = findViewById(R.id.edtVNameC)
        edtVAgeC = findViewById(R.id.edtVAgeC)
        edtVphonNoC = findViewById(R.id.edtVphonNoC)
        edtVEmailC = findViewById(R.id.edtVEmailC)
        edtVPwdC = findViewById(R.id.edtVPwdC)
        editVConfirmPwdC = findViewById(R.id.editVConfirmPwdC)
        edtVNationalC = findViewById(R.id.edtVNationalC)
        rollTypeCustomer = findViewById(R.id.rollTypeCustomer)
        VCRegisterButton = findViewById(R.id.VCRegisterButton)


        auth = Firebase.auth


        VCRegisterButton.setOnClickListener {
            saveVehicleOwnerDetails()

        }
    }

    private fun saveVehicleOwnerDetails() {
        //getting values
        val Vname = edtVNameC.text.toString()
        val Vage = edtVAgeC.text.toString()
        val VpNumber = edtVphonNoC.text.toString()
        val Vemail = edtVEmailC.text.toString()
        val Vpassword = edtVPwdC.text.toString()
        val VConfirmPassowrd = editVConfirmPwdC.text.toString()
        val VNid = edtVNationalC.text.toString()
        val utype =rollTypeCustomer.text.toString()




        if (Vname.isEmpty()) {
            edtVNameC.error = "Please Enter Name"
        } else if (Vage.isEmpty()) {
            edtVAgeC.error = "Please Enter Age"
        } else if (VpNumber.isEmpty()) {
            edtVphonNoC.error = "Please Enter Phone Number"
        } else if (VpNumber.length != 10) {
            edtVphonNoC.error = "Phone Number must be exactly 10 digits"
        } else if (Vpassword.isEmpty()) {
            edtVPwdC.error = "Please Enter Password"
        }
//            else if (!isValidPassword(Vpassword)) {
//                edtVPwd.error = "Please Enter 8 digit password"
//            }
        else if (VConfirmPassowrd.isEmpty()) {
            editVConfirmPwdC.error = "Please Enter Confirm password"
        } else if (VConfirmPassowrd!=Vpassword) {
            editVConfirmPwdC.error = "Confirm password didn't mach"
        }
        else if (VNid.isEmpty()) {
            edtVNationalC.error = "Please Enter NIC number"
        } else if (Vemail.isEmpty()) {
            edtVEmailC.error = "Please Enter Email"

        } else if (!isValidEmail(Vemail)) {
            edtVEmailC.error = "Invalid Email"
        }

        auth.createUserWithEmailAndPassword(Vemail, Vpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userId = user!!.uid

                    if (userId != null) {


//                            val userId = dbRef.push().key!!


                        val VOwner = VehicleOwnerModel(userId, Vname, Vage, VpNumber, Vemail, Vpassword, VConfirmPassowrd, VNid, utype)

                        dbRef = FirebaseDatabase.getInstance().getReference("Register")

                        dbRef.child(userId).setValue(VOwner)
                            .addOnCompleteListener {
                                Toast.makeText(this, "Registration Successfully", Toast.LENGTH_SHORT).show()
                                edtVNameC.text.clear()
                                edtVAgeC.text.clear()
                                edtVphonNoC.text.clear()
                                edtVEmailC.text.clear()
                                edtVPwdC.text.clear()
                                editVConfirmPwdC.text.clear()
                                edtVNationalC.text.clear()

                                val intent = Intent(this, loginPage1::class.java)
                                startActivity(intent)
                                finish()

                            }.addOnFailureListener { err ->
                                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
                }else{
                    Toast.makeText(this, "Invalid Email And Password", Toast.LENGTH_SHORT).show()
                }
            }

    }
    private fun isValidEmail(Vemail: String): Boolean {
        return Vemail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(Vemail).matches()
    }
    private fun isValidPassword(Vpassword: String): Boolean {
        val pattern = "^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[^\$@!%?&])[A-Za-z\\d\$@!%?&]{8,}$".toRegex()
        return Vpassword.isNotEmpty() && Vpassword.matches(pattern)
    }
}
