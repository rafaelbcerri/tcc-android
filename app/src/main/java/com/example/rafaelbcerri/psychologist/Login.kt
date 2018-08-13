package com.example.rafaelbcerri.psychologist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        loginButton.setOnClickListener() {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth!!.currentUser
                        val userId = user!!.uid

                        db!!.collection("users").document(userId)
                            .get()
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    val document = task.result
                                    val type = document.get("type")

                                    if (type.toString().equals("patient")) {
                                        val homePatientActivity = Intent(baseContext, HomePatient::class.java)
                                        startActivity(homePatientActivity)
                                    } else {
                                        val homePsychologistActivity = Intent(baseContext, HomePsychologist::class.java)
                                        startActivity(homePsychologistActivity)
                                    }

                                } else {
                                    Toast.makeText(this, "Houve algum erro na requisição", Toast.LENGTH_LONG).show()
                                }
                            }

                    } else {
                        Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                    }

                }
        }

        signUpPacient.setOnClickListener() {
            val singUpPatientActivity = Intent(baseContext, SignUpPatient::class.java)
            startActivity(singUpPatientActivity)
        }

        signUpPsychologist.setOnClickListener() {
            val singUpPsychologistActivity = Intent(baseContext, SignUpPsychologist::class.java)
            startActivity(singUpPsychologistActivity)
        }
    }

}
