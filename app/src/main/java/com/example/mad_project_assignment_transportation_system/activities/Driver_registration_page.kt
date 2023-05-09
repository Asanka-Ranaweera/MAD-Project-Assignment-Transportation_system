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

class driver_registration_page : AppCompatActivity() {


    private lateinit var edtVNameD: EditText
    private lateinit var edtVAgeD: EditText
    private lateinit var edtVphonNoD: EditText

    private lateinit var edtVEmailD: EditText
    private lateinit var edtVPwdD: EditText
    private lateinit var editVConfirmPwdD: EditText
    private lateinit var edtVNationalD: EditText
    private lateinit var rollTypeDriver: TextView

    private lateinit var VDRegisterButton: Button
    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_registration_page)

        edtVNameD = findViewById(R.id.edtVNameD)
        edtVAgeD = findViewById(R.id.edtVAgeD)
        edtVphonNoD = findViewById(R.id.edtVphonNoD)
        edtVEmailD = findViewById(R.id.edtVEmailD)
        edtVPwdD = findViewById(R.id.edtVPwdD)
        editVConfirmPwdD = findViewById(R.id.editVConfirmPwdD)
        edtVNationalD = findViewById(R.id.edtVNationalD)
        rollTypeDriver = findViewById(R.id.rollTypeDriver)
        VDRegisterButton = findViewById(R.id.VDRegisterButton)


        auth = Firebase.auth


        VDRegisterButton.setOnClickListener {
            saveVehicleOwnerDetails()

        }
    }

    private fun saveVehicleOwnerDetails() {
        //getting values
        val Vname = edtVNameD.text.toString()
        val Vage = edtVAgeD.text.toString()
        val VpNumber = edtVphonNoD.text.toString()
        val Vemail = edtVEmailD.text.toString()
        val Vpassword = edtVPwdD.text.toString()
        val VConfirmPassowrd = editVConfirmPwdD.text.toString()
        val VNid = edtVNationalD.text.toString()
        val utype =rollTypeDriver.text.toString()




        if (Vname.isEmpty()) {
            edtVNameD.error = "Please Enter Name"
        } else if (Vage.isEmpty()) {
            edtVAgeD.error = "Please Enter Age"
        } else if (VpNumber.isEmpty()) {
            edtVphonNoD.error = "Please Enter Phone Number"
        } else if (VpNumber.length != 10) {
            edtVphonNoD.error = "Phone Number must be exactly 10 digits"
        } else if (Vpassword.isEmpty()) {
            edtVPwdD.error = "Please Enter Password"
        }
//            else if (!isValidPassword(Vpassword)) {
//                edtVPwd.error = "Please Enter 8 digit password"
//            }
        else if (VConfirmPassowrd.isEmpty()) {
            editVConfirmPwdD.error = "Please Enter Confirm password"
        } else if (VConfirmPassowrd!=Vpassword) {
            editVConfirmPwdD.error = "Confirm password didn't mach"
        }
        else if (VNid.isEmpty()) {
            edtVNationalD.error = "Please Enter NIC number"
        } else if (Vemail.isEmpty()) {
            edtVEmailD.error = "Please Enter Email"

        } else if (!isValidEmail(Vemail)) {
            edtVEmailD.error = "Invalid Email"
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
                                edtVNameD.text.clear()
                                edtVAgeD.text.clear()
                                edtVphonNoD.text.clear()
                                edtVEmailD.text.clear()
                                edtVPwdD.text.clear()
                                editVConfirmPwdD.text.clear()
                                edtVNationalD.text.clear()

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
