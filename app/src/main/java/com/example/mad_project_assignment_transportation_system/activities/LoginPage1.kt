package com.example.mad_project_assignment_transportation_system.activities

import SessionManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mad_project_assignment_transportation_system.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class loginPage1 : AppCompatActivity() {

    private lateinit var emailID: EditText
    private lateinit var passwordID: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page1)






        auth = Firebase.auth
        database = FirebaseDatabase.getInstance().reference
        sessionManager = SessionManager(this)

        emailID = findViewById(R.id.emailID)
        passwordID = findViewById(R.id.passwordID)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnLogin.setOnClickListener {
            doLogin()
        }

        btnSignUp.setOnClickListener {
            val intent = Intent(this, UserTypePage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun doLogin() {
        val email = emailID.text.toString()
        val password = passwordID.text.toString()

        if (email.isEmpty()) {
            emailID.error = "Please enter your email address"
            passwordID.requestFocus()
            return
        }

        if (!isValidEmail(email)) {
            emailID.error = "Please enter a valid email address"
            emailID.requestFocus()
            return
        }

        if (password.isEmpty()) {
            passwordID.error = "Please enter your password"
            passwordID.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val userId = user?.uid
                    if(userId != null ){
                        database.child("Register").child(userId).child("utype").get()
                            .addOnSuccessListener {
                                    dataSnapshot ->
                                val Utype = dataSnapshot.value as String?
                                if (Utype == "Vehicle_Owner") {
                                    val intent = Intent(this, Vehicle_OwnerPage1::class.java)
                                    startActivity(intent)
                                    finish()
                                } else if(Utype == "Customer") {
                                    val intent = Intent(this, Dashboard::class.java)
                                    startActivity(intent)
                                    finish()
                                } else if(Utype == "Driver") {
                                    val intent = Intent(this, driverProfileActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                else {
                                    Toast.makeText(this, "Failed to get user Role:", Toast.LENGTH_SHORT).show()
                                }

                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(this, "Failed to get user role: ${exception.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                else {
                    // If login fails, display a message to the user.
                    Toast.makeText(
                        this, "Invalid Email and Password", Toast.LENGTH_SHORT).show()
                }
            }
        sessionManager.saveSession(email, password)
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}





