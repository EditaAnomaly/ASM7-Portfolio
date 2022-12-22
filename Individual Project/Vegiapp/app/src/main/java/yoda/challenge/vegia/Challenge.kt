package yoda.challenge.vegia

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.challenge.*
import yoda.challenge.vegia.databinding.ChallengeBinding


class Challenge : Fragment() {

    private lateinit var binding: ChallengeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ChallengeBinding.inflate(layoutInflater)
        return binding.root

        //return inflater.inflate(R.layout.challenge, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var x = 1

        //variables for sound effects
        var soundPool: SoundPool? = null
        var ping = 1

        val challengeList = resources.getStringArray(R.array.challenges)
        val tipsList = resources.getStringArray(R.array.tips)
        var tipText = false

        val appContext = requireContext().applicationContext
        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        ping = soundPool!!.load(appContext, R.raw.space_coin, 1)

        val sharedPreference =
            appContext.getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        x = sharedPreference.getInt("number", x)
        if ( x == null || x == 11 ) {
            x = 1
            val editor = sharedPreference.edit()
            editor.putInt("number", x)
            editor.commit()
        } else
            x = sharedPreference.getInt("number", x)

        binding.challengeTitleTxt.setText("Challenge " + x + ":")
        binding.challengeTxt.setText(challengeList[x - 1])

        val vibrateSetting = appContext.getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        val isVibrationOn = vibrateSetting.getBoolean("vibrationSettings", true)

        binding.completeBtn.setOnClickListener {
            val soundSetting = appContext.getSharedPreferences("userSettings", MODE_PRIVATE)
            val isSoundOn = soundSetting.getBoolean("soundSettings", true)
            if (isSoundOn){
                soundPool?.play(ping, 1F, 1F, 0, 0, 1F)
            }
            vibrate(isVibrationOn)
            val intent = Intent(requireContext(), GemMemory::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.tip.setOnClickListener {
            if (!tipText) {
                vibrate(isVibrationOn)
                tip_text.text = tipsList[x - 1]
                tipText = true
            } else {
                tip_text.text = ""
                tipText = false
            }
        }
    }

    private fun Fragment.vibrate(isVibrationOn: Boolean) {
        if (isVibrationOn) {
            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
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