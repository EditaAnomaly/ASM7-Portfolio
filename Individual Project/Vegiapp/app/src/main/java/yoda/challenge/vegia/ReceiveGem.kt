package yoda.challenge.vegia
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.receive_gem.*

class ReceiveGem : AppCompatActivity() {
    var x = 1

    //variables for sound effects
    var soundPool: SoundPool? = null
    var ping = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receive_gem)
        supportActionBar?.hide()

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        ping = soundPool!!.load(baseContext, R.raw.space_coin, 1)

        var gem = findViewById<ImageView>(R.id.givegem)
        val gems = listOf(R.drawable.chal1, R.drawable.chal2, R.drawable.chal3, R.drawable.chal4, R.drawable.chal5, R.drawable.chal6, R.drawable.chal7, R.drawable.chal8, R.drawable.chal9, R.drawable.chal10)

        val sharedPreference =  getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        var x = sharedPreference.getInt("number", x)
        gem.setImageResource(gems[x-1])
        x++
        var editor = sharedPreference.edit()
        editor.putInt("number",x)
        editor.commit()

        val isSoundOn = sharedPreference.getBoolean("soundSettings", true)
        val isVibrationOn = sharedPreference.getBoolean("vibrationSettings", true)

        accept_btn.setOnClickListener {
            if (isSoundOn){
                soundPool?.play(ping, 1F, 1F, 0, 0, 1F)
            }
            vibrate(isVibrationOn)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun vibrate(isVibrationOn: Boolean) {
        if (isVibrationOn) {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            200,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    vibrator.vibrate(200)
                }
            }
        }
    }
}


