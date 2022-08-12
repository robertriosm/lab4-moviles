package com.example.laboratorio4

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val button = findViewById<Button>(R.id.button4)
        button.setOnClickListener {
            Toast.makeText(this, "Roberto Francisco Rios Morales!", Toast.LENGTH_LONG).show()
        }

    }

}