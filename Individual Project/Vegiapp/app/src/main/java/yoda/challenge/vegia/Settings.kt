package yoda.challenge.vegia

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.settings.*
import yoda.challenge.vegia.databinding.ChallengeBinding
import yoda.challenge.vegia.databinding.SettingsBinding

class Settings : Fragment() {

    private lateinit var binding: SettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = SettingsBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val PREF_NAME = "userSettings"
        val PREF_NAME_S = "soundSettings"
        val PREF_NAME_V = "vibrationSettings"

        val appContext = requireContext().applicationContext
        val sharedPreferences = appContext.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        sound_switch.isChecked = sharedPreferences.getBoolean(PREF_NAME_S, true)
        sound_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean(PREF_NAME_S, true)
            } else {
                editor.putBoolean(PREF_NAME_S, false)
            }
            editor.commit()
        }
        vibration_switch.isChecked = sharedPreferences.getBoolean(PREF_NAME_V, true)
        vibration_switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean(PREF_NAME_V, true)
            } else {
                editor.putBoolean(PREF_NAME_V, false)
            }
            editor.apply()
        }
    }
}