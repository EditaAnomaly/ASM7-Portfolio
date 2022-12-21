package yoda.challenge.vegia

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.verification.*

class Verification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verification)
        supportActionBar?.hide()

        confirm.setOnClickListener() {
            val sharedPreference = getSharedPreferences("userSettings", Context.MODE_PRIVATE)
            val name = username_field.text
            val editor = sharedPreference.edit()
            editor.putString("user", name.toString())
            editor.commit()
            setContentView(R.layout.activity_main)
        }
    }
}