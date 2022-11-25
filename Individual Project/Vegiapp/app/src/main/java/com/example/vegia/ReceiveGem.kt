package com.example.vegia

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ReceiveGem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receive_gem)

        val buttonClick = findViewById<Button>(R.id.accept_btn)
        buttonClick.setOnClickListener {
            val intent = Intent(this, GemMemory::class.java)
            startActivity(intent)
        }
    }
}