package com.example.mad_project_assignment_transportation_system.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.R
import com.example.mad_project_assignment_transportation_system.models.VehicleOwnerModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class vOwner_Register_Page : AppCompatActivity() {

    private lateinit var edtVName: EditText
    private lateinit var edtVAge: EditText
    private lateinit var edtVphonNo: EditText

    private lateinit var edtVEmail: EditText
    private lateinit var edtVPwd: EditText
    private lateinit var editVConfirmPwd: EditText
    private lateinit var edtVNational: EditText
    private lateinit var rollType: TextView

    private lateinit var VORegisterButton: Button
    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vowner_register_page)



        edtVName = findViewById(R.id.edtVName)
        edtVAge = findViewById(R.id.edtVAge)
        edtVphonNo = findViewById(R.id.edtVphonNo)
        edtVEmail = findViewById(R.id.edtVEmail)
        edtVPwd = findViewById(R.id.edtVPwd)
        editVConfirmPwd = findViewById(R.id.editVConfirmPwd)
        edtVNational = findViewById(R.id.edtVNational)
        rollType = findViewById(R.id.rollType)
        VORegisterButton = findViewById(R.id.VORegisterButton)


        auth = Firebase.auth


        VORegisterButton.setOnClickListener {
            saveVehicleOwnerDetails()

        }


    }

        private fun saveVehicleOwnerDetails() {
            //getting values
            val Vname = edtVName.text.toString()
            val Vage = edtVAge.text.toString()
            val VpNumber = edtVphonNo.text.toString()
            val Vemail = edtVEmail.text.toString()
            val Vpassword = edtVPwd.text.toString()
            val VConfirmPassowrd = editVConfirmPwd.text.toString()
            val VNid = edtVNational.text.toString()
            val utype =rollType.text.toString()




            if (Vname.isEmpty()) {
                edtVName.error = "Please Enter Name"
            } else if (Vage.isEmpty()) {
                edtVAge.error = "Please Enter Age"
            } else if (VpNumber.isEmpty()) {
                edtVphonNo.error = "Please Enter Phone Number"
            } else if (VpNumber.length != 10) {
                edtVphonNo.error = "Phone Number must be exactly 10 digits"
            } else if (Vpassword.isEmpty()) {
                edtVPwd.error = "Please Enter Password"
            }
//            else if (!isValidPassword(Vpassword)) {
//                edtVPwd.error = "Please Enter 8 digit password"
//            }
            else if (VConfirmPassowrd.isEmpty()) {
                editVConfirmPwd.error = "Please Enter Confirm password"
            } else if (VConfirmPassowrd!=Vpassword) {
                editVConfirmPwd.error = "Confirm password didn't mach"
            }
            else if (VNid.isEmpty()) {
                edtVNational.error = "Please Enter NIC number"
            } else if (Vemail.isEmpty()) {
                edtVEmail.error = "Please Enter Email"

            } else if (!isValidEmail(Vemail)) {
                edtVEmail.error = "Invalid Email"
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
                                    edtVName.text.clear()
                                    edtVAge.text.clear()
                                    edtVphonNo.text.clear()
                                    edtVEmail.text.clear()
                                    edtVPwd.text.clear()
                                    editVConfirmPwd.text.clear()
                                    edtVNational.text.clear()

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
