package com.example.vegia

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import kotlin.math.log

class GemMemory : AppCompatActivity() {

    val REQUEST_CODE = 200
    lateinit var picture: ImageView
    var storageRef = Firebase.storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gem_memory)
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

    fun capturePhoto() {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    fun savePhoto() {
        picture.isDrawingCacheEnabled = true
        picture.buildDrawingCache()
        val bitmap = (picture.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var x = 1
        // Create a reference to "challenge_x.jpg"
        val challengeRef = storageRef.child("challenge_"+x+".png")
        // Create a reference to 'images/challenge_x.jpg'
        val challengeImagesRef = storageRef.child("images/challenge_"+x+".jpg")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            picture = findViewById(R.id.picture)
            picture.setImageBitmap(data.extras?.get("data") as Bitmap)
            savePhoto()
        }
    }
}