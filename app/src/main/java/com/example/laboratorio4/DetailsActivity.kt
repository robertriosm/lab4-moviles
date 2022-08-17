package com.example.laboratorio4

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.Exception

class DetailsActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION_CODE = 102
    val permissions = arrayOf("android.permission.CAMERA")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val recibido = getIntent().getSerializableExtra("BKING") as Restaurante

        val title = findViewById<TextView>(R.id.title)
        title.setText(recibido.nombre)

        val direction = findViewById<TextView>(R.id.textView18)
        direction.setText(recibido.dir)

        val horario = findViewById<TextView>(R.id.horario)
        horario.setText(recibido.hor)

        val llamar = findViewById<Button>(R.id.buttonLlamar)
        llamar.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:22000000")))
            } catch (e: ActivityNotFoundException) {
                print(e)
            }
        }

        val visita = findViewById<Button>(R.id.button6)
        visita.setOnClickListener {
            try {
                checkCameraPermission(android.Manifest.permission.CAMERA, "camera", CAMERA_PERMISSION_CODE)
            } catch (e: Exception) {
                print(e.message)
            }
        }



    }

    private fun checkCameraPermission(permission: String, name: String, requestCode: Int) {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(applicationContext, "$name permission granted!", Toast.LENGTH_SHORT).show()
                }
                ActivityCompat.shouldShowRequestPermissionRationale(this, permission) -> showThisDialog(permission, name, requestCode)
                else -> {
                    ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "$name permission refused!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "$name permission granted!", Toast.LENGTH_SHORT).show()
            }
        }

        when (requestCode) {
            CAMERA_PERMISSION_CODE -> innerCheck("camera")
        }
    }

    private fun showThisDialog(permission: String, name: String, requestCode: Int) {
        val b = AlertDialog.Builder(this)

        b.apply {
            setTitle("Permission required")
            setMessage("This function needs camera permission")
            setPositiveButton("OK") {dialog, which -> ActivityCompat.requestPermissions(this@DetailsActivity, arrayOf(permission), requestCode)}
            val dialog = b.create()
            dialog.show()
        }
    }

}