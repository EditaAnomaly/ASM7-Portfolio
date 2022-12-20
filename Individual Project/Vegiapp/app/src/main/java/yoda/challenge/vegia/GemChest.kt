package yoda.challenge.vegia

import android.content.Context
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.gem_chest.*
import java.io.File

class GemChest : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gem_chest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var soundPool: SoundPool? = null
        var gemPing = 1
        var x = 0

        val appContext = requireContext().applicationContext
        val sharedPreference =
            appContext.getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        x = sharedPreference.getInt("number", x) - 1
        Log.d("X", sharedPreference.getInt("number", x).toString())

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        gemPing = soundPool!!.load(appContext,R.raw.gem, 1)

        val soundSetting = appContext.getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        val isSoundOn = soundSetting.getBoolean("soundSettings", true)
        Log.d("Z", isSoundOn.toString())

        val vibrateSetting = appContext.getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        val isVibrationOn = vibrateSetting.getBoolean("vibrationSettings", true)

        when (x) {
            1 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
            }
            2 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
            }
            3 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
                gem3_iv.setImageResource(R.drawable.chal3)
                gemClick(gem3_iv, 3, isSoundOn,soundPool, gemPing)
            }
            4 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
                gem3_iv.setImageResource(R.drawable.chal3)
                gemClick(gem3_iv, 3, isSoundOn,soundPool, gemPing)
                gem4_iv.setImageResource(R.drawable.chal4)
                gemClick(gem4_iv, 4, isSoundOn,soundPool, gemPing)
            }
            5 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
                gem3_iv.setImageResource(R.drawable.chal3)
                gemClick(gem3_iv, 3, isSoundOn,soundPool, gemPing)
                gem4_iv.setImageResource(R.drawable.chal4)
                gemClick(gem4_iv, 4, isSoundOn,soundPool, gemPing)
                gem5_iv.setImageResource(R.drawable.chal5)
                gemClick(gem5_iv, 5, isSoundOn,soundPool, gemPing)
            }
            6 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
                gem3_iv.setImageResource(R.drawable.chal3)
                gemClick(gem3_iv, 3, isSoundOn,soundPool, gemPing)
                gem4_iv.setImageResource(R.drawable.chal4)
                gemClick(gem4_iv, 4, isSoundOn,soundPool, gemPing)
                gem5_iv.setImageResource(R.drawable.chal5)
                gemClick(gem5_iv, 5, isSoundOn,soundPool, gemPing)
                gem6_iv.setImageResource(R.drawable.chal6)
                gemClick(gem6_iv, 6, isSoundOn,soundPool, gemPing)
            }
            7 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
                gem3_iv.setImageResource(R.drawable.chal3)
                gemClick(gem3_iv, 3, isSoundOn,soundPool, gemPing)
                gem4_iv.setImageResource(R.drawable.chal4)
                gemClick(gem4_iv, 4, isSoundOn,soundPool, gemPing)
                gem5_iv.setImageResource(R.drawable.chal5)
                gemClick(gem5_iv, 5, isSoundOn,soundPool, gemPing)
                gem6_iv.setImageResource(R.drawable.chal6)
                gemClick(gem6_iv, 6, isSoundOn,soundPool, gemPing)
                gem7_iv.setImageResource(R.drawable.chal7)
                gemClick(gem7_iv, 7, isSoundOn,soundPool, gemPing)
            }
            8 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
                gem3_iv.setImageResource(R.drawable.chal3)
                gemClick(gem3_iv, 3, isSoundOn,soundPool, gemPing)
                gem4_iv.setImageResource(R.drawable.chal4)
                gemClick(gem4_iv, 4, isSoundOn,soundPool, gemPing)
                gem5_iv.setImageResource(R.drawable.chal5)
                gemClick(gem5_iv, 5, isSoundOn,soundPool, gemPing)
                gem6_iv.setImageResource(R.drawable.chal6)
                gemClick(gem6_iv, 6, isSoundOn,soundPool, gemPing)
                gem7_iv.setImageResource(R.drawable.chal7)
                gemClick(gem7_iv, 7, isSoundOn,soundPool, gemPing)
                gem8_iv.setImageResource(R.drawable.chal8)
                gemClick(gem8_iv, 8, isSoundOn,soundPool, gemPing)
            }
            9 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
                gem3_iv.setImageResource(R.drawable.chal3)
                gemClick(gem3_iv, 3, isSoundOn,soundPool, gemPing)
                gem4_iv.setImageResource(R.drawable.chal4)
                gemClick(gem4_iv, 4, isSoundOn,soundPool, gemPing)
                gem5_iv.setImageResource(R.drawable.chal5)
                gemClick(gem5_iv, 5, isSoundOn,soundPool, gemPing)
                gem6_iv.setImageResource(R.drawable.chal6)
                gemClick(gem6_iv, 6, isSoundOn,soundPool, gemPing)
                gem7_iv.setImageResource(R.drawable.chal7)
                gemClick(gem7_iv, 7, isSoundOn,soundPool, gemPing)
                gem8_iv.setImageResource(R.drawable.chal8)
                gemClick(gem8_iv, 8, isSoundOn,soundPool, gemPing)
                gem9_iv.setImageResource(R.drawable.chal9)
                gemClick(gem9_iv, 9, isSoundOn,soundPool, gemPing)
            }
            10 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, 1, isSoundOn,soundPool, gemPing)
                gem2_iv.setImageResource(R.drawable.chal2)
                gemClick(gem2_iv, 2, isSoundOn,soundPool, gemPing)
                gem3_iv.setImageResource(R.drawable.chal3)
                gemClick(gem3_iv, 3, isSoundOn,soundPool, gemPing)
                gem4_iv.setImageResource(R.drawable.chal4)
                gemClick(gem4_iv, 4, isSoundOn,soundPool, gemPing)
                gem5_iv.setImageResource(R.drawable.chal5)
                gemClick(gem5_iv, 5, isSoundOn,soundPool, gemPing)
                gem6_iv.setImageResource(R.drawable.chal6)
                gemClick(gem6_iv, 6, isSoundOn,soundPool, gemPing)
                gem7_iv.setImageResource(R.drawable.chal7)
                gemClick(gem7_iv, 7, isSoundOn,soundPool, gemPing)
                gem8_iv.setImageResource(R.drawable.chal8)
                gemClick(gem8_iv, 8, isSoundOn,soundPool, gemPing)
                gem9_iv.setImageResource(R.drawable.chal9)
                gemClick(gem9_iv, 9, isSoundOn,soundPool, gemPing)
                gem10_iv.setImageResource(R.drawable.chal10)
            }
        }
        gem_memory.setOnClickListener{
            gem_memory.visibility = View.INVISIBLE
            vibrate(isVibrationOn)
        }
    }

    private fun gemClick(imageView: ImageView, x: Int, isSoundOn: Boolean, soundPool: SoundPool, gemPing: Int) {
        imageView.setOnClickListener {

            if (isSoundOn){
                soundPool?.play(gemPing, 1F, 1F, 0, 0, 1F)
            }
            val memoryLocation = gem_memory
            val imageRef =
                FirebaseStorage.getInstance().reference.child("challenge_" + x + ".png")
            val localFile = File.createTempFile("TempImage", "jpg")
            imageRef.getFile(localFile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                memoryLocation.setImageBitmap(bitmap)
                gem_memory.visibility = View.VISIBLE
            }.addOnFailureListener {
                Log.d("UFAIL", "failed to retrieve image")
            }
        }
    }
    private fun Fragment.vibrate(isVibrationOn: Boolean) {
    if (isVibrationOn) {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }
    }
}