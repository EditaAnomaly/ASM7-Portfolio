package yoda.challenge.vegia

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import yoda.challenge.vegia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = ""
        val sharedPreference = getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        Log.d("username", (sharedPreference.getString("user", name).toString()))
        if (sharedPreference.getString("user", name) == null || (sharedPreference.getString("user", name) == "")){
            supportActionBar?.hide()
            setContentView(R.layout.verification)
            }
        else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportActionBar?.hide()
            navController = Navigation.findNavController(this, R.id.navHostFragment)
            setupWithNavController(binding.bottomNavigationView, navController)
        }
    }
}