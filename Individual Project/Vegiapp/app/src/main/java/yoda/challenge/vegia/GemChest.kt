package yoda.challenge.vegia

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
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
//        var resId: IdRes
        var x = 0
        val appContext = requireContext().applicationContext
        val sharedPreference =
            appContext.getSharedPreferences("challengeNumber", Context.MODE_PRIVATE)
        x = sharedPreference.getInt("number", x) - 1
        Log.d("X", sharedPreference.getInt("number", x).toString())
        // TODO: set on click listeners for each gem that opens the images from db, otherwise show toast "Slot is Empty: complete the challenge first"
        when (x) {
            1 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, x)
            }
            2 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, x)
                gem2_iv.setImageResource(R.drawable.chal2)
            }
            3 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gemClick(gem1_iv, x)
                gem2_iv.setImageResource(R.drawable.chal3)
                gem3_iv.setImageResource(R.drawable.chal4)
            }
            4 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gem2_iv.setImageResource(R.drawable.chal2)
                gem3_iv.setImageResource(R.drawable.chal3)
                gem4_iv.setImageResource(R.drawable.chal4)
            }
            5 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gem2_iv.setImageResource(R.drawable.chal2)
                gem3_iv.setImageResource(R.drawable.chal3)
                gem4_iv.setImageResource(R.drawable.chal4)
                gem5_iv.setImageResource(R.drawable.chal5)
            }
            6 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gem2_iv.setImageResource(R.drawable.chal2)
                gem3_iv.setImageResource(R.drawable.chal3)
                gem4_iv.setImageResource(R.drawable.chal4)
                gem5_iv.setImageResource(R.drawable.chal5)
                gem6_iv.setImageResource(R.drawable.chal6)
            }
            7 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gem2_iv.setImageResource(R.drawable.chal2)
                gem3_iv.setImageResource(R.drawable.chal3)
                gem4_iv.setImageResource(R.drawable.chal4)
                gem5_iv.setImageResource(R.drawable.chal5)
                gem6_iv.setImageResource(R.drawable.chal6)
                gem7_iv.setImageResource(R.drawable.chal7)
            }
            8 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gem2_iv.setImageResource(R.drawable.chal2)
                gem3_iv.setImageResource(R.drawable.chal3)
                gem4_iv.setImageResource(R.drawable.chal4)
                gem5_iv.setImageResource(R.drawable.chal5)
                gem6_iv.setImageResource(R.drawable.chal6)
                gem7_iv.setImageResource(R.drawable.chal7)
                gem8_iv.setImageResource(R.drawable.chal8)
            }
            9 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gem2_iv.setImageResource(R.drawable.chal2)
                gem3_iv.setImageResource(R.drawable.chal3)
                gem4_iv.setImageResource(R.drawable.chal4)
                gem5_iv.setImageResource(R.drawable.chal5)
                gem6_iv.setImageResource(R.drawable.chal6)
                gem7_iv.setImageResource(R.drawable.chal7)
                gem8_iv.setImageResource(R.drawable.chal8)
                gem9_iv.setImageResource(R.drawable.chal9)
            }
            10 -> {
                gem1_iv.setImageResource(R.drawable.chal1)
                gem2_iv.setImageResource(R.drawable.chal2)
                gem3_iv.setImageResource(R.drawable.chal3)
                gem4_iv.setImageResource(R.drawable.chal4)
                gem5_iv.setImageResource(R.drawable.chal5)
                gem6_iv.setImageResource(R.drawable.chal6)
                gem7_iv.setImageResource(R.drawable.chal7)
                gem8_iv.setImageResource(R.drawable.chal8)
                gem9_iv.setImageResource(R.drawable.chal9)
                gem10_iv.setImageResource(R.drawable.chal10)
            }
        }
        gem_memory.setOnClickListener{
            gem_memory.visibility = View.INVISIBLE
        }
    }


    fun gemClick(imageView: ImageView, x: Int) {
        imageView.setOnClickListener {
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
}