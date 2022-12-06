package yoda.challenge.vegia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        var x = 1
        val challengeNumber = findViewById<TextView>(R.id.challengeTitle_txt)
        val challengeText = findViewById<TextView>(R.id.challenge_txt)
        val challengeList = resources.getStringArray(R.array.challenges)

        val sharedPreference =  getSharedPreferences("challengeNumber", Context.MODE_PRIVATE)
        if (sharedPreference.getInt("number",x) == null || sharedPreference.getInt("number",x) == 11 )
        {
            x = 1
            var editor = sharedPreference.edit()
            editor.putInt("number",x)
            editor.commit()
        }
        else
            x = sharedPreference.getInt("number",x)


        challengeNumber.text = "Challenge "+x+":"
        challengeText.text = challengeList[x-1]

        val buttonClick = findViewById<Button>(R.id.complete_btn)
        buttonClick.setOnClickListener {
            val intent = Intent(this, ReceiveGem::class.java)
            startActivity(intent)
        }
    }
}