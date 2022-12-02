package com.example.vegia
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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


