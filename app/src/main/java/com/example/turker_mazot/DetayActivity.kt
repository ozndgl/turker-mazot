package com.example.turker_mazot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar


class DetayActivity : AppCompatActivity() {


    private lateinit var editTextDers:EditText
    private lateinit var editTextNot1:EditText
    private lateinit var editTextNot2:EditText
    private lateinit var toolbarNotDetay:Toolbar


    private lateinit var not:Notlar
    private lateinit var vt: VeritabaniYardimcisi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

         editTextDers = findViewById(R.id.editTextDers) as EditText
         editTextNot1 = findViewById(R.id.editTextNot1) as EditText
         editTextNot2 = findViewById(R.id.editTextNot2) as EditText
         toolbarNotDetay = findViewById(R.id.toolbarNotDetay) as Toolbar

        toolbarNotDetay.title = "Not Detay"
        setSupportActionBar(toolbarNotDetay)

        vt = VeritabaniYardimcisi(this)

         not = intent.getSerializableExtra("nesne") as Notlar




        editTextDers.setText(not.ders_adi)
        editTextNot1.setText((not.not1).toString())
        editTextNot2.setText((not.not2).toString())




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_sil ->{
                val toolbarNotDetay = findViewById(R.id.toolbarNotDetay) as Toolbar
                Snackbar.make(toolbarNotDetay,"Silinsin mi?",Snackbar.LENGTH_SHORT)
                    .setAction("EVET"){

                        NotlarDao().notSil(vt,not.not_id)

                        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                        finish()
                    }.show()
                return true
            }
            R.id.action_duzenle ->{

                val ders_adi = editTextDers.text.toString().trim()
                val not1 = editTextNot1.text.toString().trim()
                val not2 = editTextNot2.text.toString().trim()

                if(TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(toolbarNotDetay,"Ders adı giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if(TextUtils.isEmpty(not1)){
                    Snackbar.make(toolbarNotDetay,"1. notu giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if(TextUtils.isEmpty(not2)){
                    Snackbar.make(toolbarNotDetay,"2. notu giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                NotlarDao().notGüncelle(vt,not.not_id,ders_adi, not1.toInt(),not2.toInt())

                startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                finish()
                return true
            }
            else -> return false
        }
    }
}