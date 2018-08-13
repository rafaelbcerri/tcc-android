package com.example.rafaelbcerri.psychologist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search.*

class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Busca"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        cardOne.setOnClickListener() {
            var perfilPsychologistActivity = Intent(baseContext, PerfilPsychologist::class.java)
            startActivity(perfilPsychologistActivity)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
