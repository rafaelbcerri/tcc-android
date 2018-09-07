package com.example.rafaelbcerri.psychologist

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.*
import android.widget.Toast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class Search : AppCompatActivity() {

    class Psychologist(private var name:String = "", private var age: String = "", private var price: String = "") {


        fun getName(): String { return name  }
        fun getAge(): String { return age }
        fun getPrice(): String { return price  }


    }

    class CustomAdapterPsychologist(private var c: Context, private var psychologists: ArrayList<Psychologist>) : BaseAdapter() {
        override fun getCount(): Int   {  return psychologists.size  }
        override fun getItem(i: Int): Any? { return psychologists[i]; }
        override fun getItemId(i: Int): Long { return i.toLong()}

        override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
            var view = view
            if (view == null) {
                //inflate layout resource if its null
                view = LayoutInflater.from(c).inflate(R.layout.psychologist_row, viewGroup, false)
            }

            //get current quote
            val psychologist = this.getItem(i) as Psychologist

            //reference textviews and imageviews from our layout
            val ageTxt = view!!.findViewById<TextView>(R.id.age) as TextView
            val nameTxt = view.findViewById<TextView>(R.id.name) as TextView
            val priceTxt = view.findViewById<TextView>(R.id.price) as TextView

            //BIND data to TextView and ImageVoew
            nameTxt.text = psychologist.getName()
            ageTxt.text = psychologist.getAge()
            priceTxt.text = psychologist.getPrice()

            return view
        }
    }

    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Busca"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        db = FirebaseFirestore.getInstance()

        db!!.collection("users")
            .whereEqualTo("type", "psychologist")
            .get()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val myListView = findViewById<ListView>(R.id.myListView)
                    val psyList = ArrayList<Psychologist>()
                    for (document in task.result) {

//
                        psyList.add(document.toObject(Psychologist::class.java))

                    }

                    val adapter = CustomAdapterPsychologist(this, psyList)
                    myListView.adapter = adapter
                } else {
                    Toast.makeText(this, "Houve algum erro na requisição", Toast.LENGTH_LONG).show()
                }
            }


//        cardOne.setOnClickListener() {
//            var perfilPsychologistActivity = Intent(baseContext, PerfilPsychologist::class.java)
//            startActivity(perfilPsychologistActivity)
//        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}




