package com.example.rafaelbcerri.psychologist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up_psychologist.*

class SignUpPsychologist : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_psychologist)

        loginButton.setOnClickListener() {
            val loginActivity = Intent(baseContext, Login::class.java)
            startActivity(loginActivity)
        }


        signUpButton.setOnClickListener() {
            val editPsychologistActivity = Intent(baseContext, EditPsychologist::class.java)
            startActivity(editPsychologistActivity)
        }


    }
}
