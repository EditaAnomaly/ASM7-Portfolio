package com.example.vegia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.complete_btn)
        buttonClick.setOnClickListener {
            val intent = Intent(this, ReceiveGem::class.java)
            startActivity(intent)
        }
    }
}