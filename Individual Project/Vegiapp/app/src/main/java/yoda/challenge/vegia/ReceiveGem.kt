package yoda.challenge.vegia
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ReceiveGem : AppCompatActivity() {
    var x = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receive_gem)
        supportActionBar?.hide()
        var gem = findViewById<ImageView>(R.id.givegem)
        val gems = listOf(R.drawable.chal1, R.drawable.chal2, R.drawable.chal3, R.drawable.chal4, R.drawable.chal5, R.drawable.chal6, R.drawable.chal7, R.drawable.chal8, R.drawable.chal9, R.drawable.chal10)

        val sharedPreference =  getSharedPreferences("challengeNumber", Context.MODE_PRIVATE)
        var x = sharedPreference.getInt("number", x)
        gem.setImageResource(gems[x-1])
        x++
        var editor = sharedPreference.edit()
        editor.putInt("number",x)
        editor.commit()

        val buttonClick = findViewById<Button>(R.id.accept_btn)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}


