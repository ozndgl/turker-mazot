package com.example.turker_mazot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.view.View


class MainActivity : AppCompatActivity() {

    private lateinit var notlarListe:ArrayList<Notlar>
    private lateinit var adapter: NotlarAdapter


    private lateinit var vt: VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database  = FirebaseDatabase.getInstance()
        val refKisiler = database.getReference("kisiler")
        val kisi = Kisiler("Ahmet",32)
        refKisiler.push().setValue(kisi)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        val rv = findViewById(R.id.rv) as RecyclerView


        toolbar.title = "Notlar uygulaması"
        toolbar.subtitle = "ortalama : 0"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        vt= VeritabaniYardimcisi(this)

        notlarListe = NotlarDao().tumNotlar(vt)

        adapter = NotlarAdapter(this,notlarListe)

        rv.adapter = adapter

        var toplam = 0

        for(n in notlarListe){
            toplam = toplam + (n.not1+n.not2)/2
        }

        if(toplam != 0){
            toolbar.subtitle = "ortalama : ${toplam/notlarListe.size }"
        }

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener{

            startActivity(Intent(this@MainActivity,NotKayitActivity::class.java))

        }







    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}