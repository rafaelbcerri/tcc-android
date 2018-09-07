package com.example.rafaelbcerri.psychologist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up_patient.*

class SignUpPatient : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_patient)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        signUpButton.setOnClickListener() {

            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth!!.currentUser
                        val userId = user!!.uid
                        val userToSave = HashMap<String, Any>()
                        userToSave.put("name", name)
                        userToSave.put("email", email)
                        userToSave.put("type", "patient")

                        db!!.collection("users").document(userId)
                            .set(userToSave)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    val homePatientActivity = Intent(baseContext, HomePatient::class.java)
                                    startActivity(homePatientActivity)
                                } else {
                                    Toast.makeText(this, "Houve algum erro para criar o usuário", Toast.LENGTH_LONG).show()
                                }
                            }


                    }
                }


        }

        loginButton.setOnClickListener() {
            val loginActivity = Intent(baseContext, Login::class.java)
            startActivity(loginActivity)
        }

    }
}
