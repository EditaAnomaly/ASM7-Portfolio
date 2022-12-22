package yoda.challenge.vegia

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.AudioManager
import android.media.SoundPool
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.gem_memory.*
import java.io.ByteArrayOutputStream
import java.io.Console
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class GemMemory : AppCompatActivity() {

    val REQUEST_CODE = 200
    lateinit var picture: ImageView
    var storageRef = Firebase.storage.reference
    //lateinit var currentPhotoPath: String
    var x = 1
    var name = "Grogu"

    //variables for sound effects
    var soundPool: SoundPool? = null
    var ping = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gem_memory)
        supportActionBar?.hide()

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        ping = soundPool!!.load(baseContext, R.raw.space_coin, 1)

        val challengeNumber = findViewById<TextView>(R.id.challengeNumber)
        val sharedPreference =  getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        name =sharedPreference.getString("user", name).toString()
        x = sharedPreference.getInt("number", x)
        challengeNumber.text = "Challenge "+x+" completed!"

        val isSoundOn = sharedPreference.getBoolean("soundSettings", true)
        val isVibrationOn = sharedPreference.getBoolean("vibrationSettings", true)

        continue_btn.setOnClickListener {
            if (isSoundOn){
                soundPool?.play(ping, 1F, 1F, 0, 0, 1F)
            }
            vibrate(isVibrationOn)
            val intent = Intent(this, ReceiveGem::class.java)
            startActivity(intent)
        }

        if (ContextCompat.checkSelfPermission(this@GemMemory,
                Manifest.permission.ACCESS_FINE_LOCATION) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@GemMemory,
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this@GemMemory,
                    arrayOf(Manifest.permission.CAMERA), 1)
            } else {
                ActivityCompat.requestPermissions(this@GemMemory,
                    arrayOf(Manifest.permission.CAMERA), 1)
            }
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@GemMemory,
                            Manifest.permission.CAMERA) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        capturePhoto()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun capturePhoto() {
        var takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_CODE)
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//                // Ensure that there's a camera activity to handle the intent
//                takePictureIntent.resolveActivity(packageManager)?.also {
//                    // Create the File where the photo should go
//                    val photoFile: File? = try {
//                        createImageFile()
//                    } catch (ex: IOException) {
//                        // Error occurred while creating the File
//                        Log.d("NOFILE","Error occurred while creating the File")
//                        null
//                    }
//                    // Continue only if the File was successfully created
//                    photoFile?.also {
//                        val photoURI: Uri = FileProvider.getUriForFile(
//                            this,
//                            "yoda.challenge.vegia.fileprovider",
//                            it
//                        )
//                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                        startActivityForResult(takePictureIntent, REQUEST_CODE)
//                    }
//                }
//            }
    }

    private fun savePhoto() {
        picture.isDrawingCacheEnabled = true
        picture.buildDrawingCache()
        val bitmap = (picture.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        // Create a reference to "challenge_x.jpg"
        val challengeRef = storageRef.child(name+"/challenge_"+x+".png")
        // Create a reference to 'images/challenge_x.jpg'
        val challengeImagesRef = storageRef.child(name+"/challenge_"+x+".jpg")
        // While the file names are the same, the references point to different files
        challengeRef.name == challengeImagesRef.name // true
        challengeRef.path == challengeImagesRef.path // false

        var uploadTask = challengeRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            Log.d("UFailed", "Image Upload Failed")
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
            Log.d("Success", "Image Uploaded Successfully")
        }
    }

//    @Throws(IOException::class)
//    fun createImageFile(): File {
//        // Create an image file name
//        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return File.createTempFile(
//            "JPEG_${timeStamp}_", /* prefix */
//            ".jpg", /* suffix */
//            storageDir /* directory */
//        ).apply {
//            // Save a file: path for use with ACTION_VIEW intents
//            currentPhotoPath = absolutePath
//        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null) {
                picture = findViewById(R.id.picture)
                picture.setImageBitmap(data.extras?.get("data") as Bitmap)
                savePhoto()
            }
        }
}