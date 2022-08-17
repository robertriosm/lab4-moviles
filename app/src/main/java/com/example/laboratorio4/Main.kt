package com.example.laboratorio4

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val button = findViewById<Button>(R.id.button4)
        button.setOnClickListener {
            Toast.makeText(this, "Roberto Francisco Rios Morales!", Toast.LENGTH_LONG).show()
        }

        val descargar = findViewById<Button>(R.id.descargarBtn)
        descargar.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")))
            } catch (e: ActivityNotFoundException) {
                print(e);
            }
        }

        val ruta = findViewById<ImageButton>(R.id.ruta)
        ruta.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Calzada+Roosevelt+35-68,+Cdad.+de+Guatemala")))
            } catch (e: ActivityNotFoundException) {
                print(e);
            }
        }

        val restaurante = Restaurante("Burger King", "Calzada Roosevelt 35-68", "5:30 – 23:00")

        val detalles = findViewById<Button>(R.id.button5)
        detalles.setOnClickListener {
            try {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("BKING", restaurante)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                print(e)
            }
        }

    }

}

data class Restaurante(val nombre: String,
                       val dir: String,
                       val hor: String) : Serializable {
}