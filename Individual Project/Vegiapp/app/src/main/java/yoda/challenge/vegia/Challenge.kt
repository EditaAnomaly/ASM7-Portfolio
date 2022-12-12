package yoda.challenge.vegia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val challengeList = resources.getStringArray(R.array.challenges)
        val tipsList = resources.getStringArray(R.array.tips)
        var tipText = false

        val appContext = requireContext().applicationContext
        val sharedPreference = appContext.getSharedPreferences("challengeNumber", Context.MODE_PRIVATE)
        if (sharedPreference.getInt("number", x) == null || sharedPreference.getInt("number",x) == 11) {
            x = 1
            var editor = sharedPreference.edit()
            editor.putInt("number", x)
            editor.commit()
        } else
            x = sharedPreference.getInt("number", x)

        binding.challengeTitleTxt.setText("Challenge "+x+":")
        binding.challengeTxt.setText(challengeList[x - 1])

        val buttonClick = binding.completeBtn
        buttonClick.setOnClickListener {
            val intent = Intent(requireContext(), GemMemory::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.tip.setOnClickListener {
            if (tipText == false){
                tip_text.setText(tipsList[x - 1])
                tipText = true
            }
            else {
                tip_text.setText("")
                tipText = false
            }
        }
    }
}