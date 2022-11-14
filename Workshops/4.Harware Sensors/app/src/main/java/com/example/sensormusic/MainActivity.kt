package com.example.sensormusic

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.SoundPool
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

//variables for shake sensor
private var sensorManager: SensorManager? = null
private var sensor: Sensor? = null
private var proximitySensor = 0f
private var accelerationSensor = 0f
private var currentAcceleration = 0f
private var lastAcceleration = 0f

//variables for sound effects
private var soundPool: SoundPool? = null
private var sound01 = 1
private var sound02 = 2
//private var sound03 = 3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPool = SoundPool.Builder().setMaxStreams(6).build()
        sound01 = soundPool!!.load(baseContext, R.raw.rain, 1)
        sound02 = soundPool!!.load(baseContext, R.raw.thunder, 1)
        //sound03 = soundPool!!.load(baseContext, R.raw.kingdom_of_bards, 1)

        accelerationSensor = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorlistener: SensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
            override fun onSensorChanged(event: SensorEvent) {
                sensor = event.sensor
               if (sensor!!.type == Sensor.TYPE_ACCELEROMETER ){

                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                   lastAcceleration = currentAcceleration
                currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
                val delta: Float = currentAcceleration - lastAcceleration
                accelerationSensor = accelerationSensor * 0.9f + delta
                if (accelerationSensor > 15 ) {
                    playSoundPrimary()
                    }
                }
                else if (sensor!!.type == Sensor.TYPE_PROXIMITY) {
                   if (proximitySensor <= 1f) {
                       playSoundSecondary()
                   }
                   }

            }
        }
        sensorManager!!.registerListener(sensorlistener, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(sensorlistener, sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL)
    }

        private fun playSoundPrimary() {
            val prefs = getSharedPreferences("userSettings", MODE_PRIVATE)
            val soundSettings = prefs.getBoolean("soundSettings", true)

            if (soundSettings){
                soundPool?.play(sound01, 1F, 1F, 0, 0, 1F)
            }
        }

    private fun playSoundSecondary() {
        val prefs = getSharedPreferences("userSettings", MODE_PRIVATE)
        val soundSettings = prefs.getBoolean("soundSettings", true)

        if (soundSettings){
            soundPool?.play(sound02, 1F, 1F, 0, 0, 1F)
        }
    }
}

//        override fun onResume() {
//            sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
//                Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
//            )
//            super.onResume()
//        }
//        override fun onPause() {
//            sensorManager!!.unregisterListener(sensorListener)
//            super.onPause()
//        }

//}