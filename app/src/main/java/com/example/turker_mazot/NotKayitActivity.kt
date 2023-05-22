package com.example.turker_mazot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar


class NotKayitActivity : AppCompatActivity() {

    private lateinit var vt: VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_kayit)

        vt = VeritabaniYardimcisi(this)

        val toolbarNotKayit = findViewById(R.id.toolbarNotKayit) as Toolbar
        val buttonKaydet = findViewById(R.id.buttonKaydet) as Button
        val editTextDers =findViewById(R.id.editTextTextDers) as EditText
        val editTextNot1 =findViewById(R.id.editTextTextNot1) as EditText
        val editTextNot2 =findViewById(R.id.editTextTextnot2) as EditText

        toolbarNotKayit.title = "Not Kayıt"
        setSupportActionBar(toolbarNotKayit)


        buttonKaydet.setOnClickListener {

            val ders_adi = editTextDers.text.toString().trim()
            val not1 = editTextNot1.text.toString().trim()
            val not2 = editTextNot2.text.toString().trim()

            if(TextUtils.isEmpty(ders_adi)){
                Snackbar.make(toolbarNotKayit,"Ders adı giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(not1)){
                Snackbar.make(toolbarNotKayit,"1. notu giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(not2)){
                Snackbar.make(toolbarNotKayit,"2. notu giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            NotlarDao().notEkle(vt,ders_adi, not1.toInt(),not2.toInt())

            startActivity(Intent(this@NotKayitActivity,MainActivity::class.java))
            finish()

        }



    }
}