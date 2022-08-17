package com.example.laboratorio4

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.Serializable

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent: Intent = getIntent()
        val recibido = intent.getSerializableExtra("bking")

        val title = findViewById<TextView>(R.id.title)
        title.setText(recibido.toString())

        val direction = findViewById<TextView>(R.id.textView18)
        direction.setText(recibido.toString())

        val horario = findViewById<TextView>(R.id.horario)
        horario.setText(recibido.toString())

        val llamar = findViewById<Button>(R.id.buttonLlamar)
        llamar.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:22000000")))
            } catch (e: ActivityNotFoundException) {
                print(e)
            }
        }

    }
}