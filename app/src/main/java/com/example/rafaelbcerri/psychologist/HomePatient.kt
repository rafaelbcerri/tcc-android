package com.example.rafaelbcerri.psychologist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_patient.*


class HomePatient : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_patient)

        fabSearch.setOnClickListener() {
            val searchActivity = Intent(baseContext, Search::class.java)
            startActivity(searchActivity)
        }
    }

}
