package com.example.rafaelbcerri.psychologist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_edit_psychologist.*

class EditPsychologist : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_psychologist)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val user = mAuth!!.currentUser
        val userId = user!!.uid

        db!!.collection("users").document(userId)
            .get()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    val name = document.get("name")

                    nome.text = name.toString();
                } else {
                    Toast.makeText(this, "Houve algum erro na requisição", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_done, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.mybutton) {

            val valor = valor.text.toString()
            val idade = idade.text.toString()
            val resumo = resumo.text.toString()

            val dataToSave = HashMap<String, Any>()
            dataToSave.put("price", valor)
            dataToSave.put("age", idade)
            dataToSave.put("resumo", resumo)

            val user = mAuth!!.currentUser
            val userId = user!!.uid

            db!!.collection("users").document(userId)
                .update(dataToSave)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val homePsychologistActivity = Intent(baseContext, HomePsychologist::class.java)
                        startActivity(homePsychologistActivity)
                    } else {
                        Toast.makeText(this, "Houve algum erro para criar o usuário", Toast.LENGTH_LONG).show()
                    }
                }
        }

        return super.onOptionsItemSelected(item)
    }

}
