package yoda.challenge.vegia

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import yoda.challenge.vegia.databinding.ActivityMainBinding
import android.content.DialogInterface.OnClickListener as DialogInterfaceOnClickListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var name = "Mirriam"
    lateinit var editTextField : EditText
    lateinit var builder : AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreference = getSharedPreferences("userSettings", Context.MODE_PRIVATE)
        name = sharedPreference.getString("user", name).toString()
        if (name == null || name == "") {
            loginAlert()
        }
       else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportActionBar?.hide()
            navController = Navigation.findNavController(this, R.id.navHostFragment)
            setupWithNavController(binding.bottomNavigationView, navController)
       }
    }
    private fun loginAlert(){
        editTextField = EditText(this.applicationContext)
        editTextField.hint = "Enter name"
        editTextField.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        editTextField.setTextColor(ContextCompat.getColor(this.applicationContext, R.color.white))

        builder = AlertDialog.Builder(this).apply {
        setTitle("Welcome")
        setMessage("Please input your username:")
        setView(editTextField)
        setPositiveButton("Confirm") { dialog, _ ->
            alertClick(editTextField.text.toString())
            dialog.dismiss()
        }
        setCancelable(false)
        show()
    }
        }

    private fun alertClick(userInput : String) {
            val sharedPreference = getSharedPreferences("userSettings", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            name = userInput
            editor.putString("user", name)
            editor.apply()

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportActionBar?.hide()
            navController = Navigation.findNavController(this, R.id.navHostFragment)
            setupWithNavController(binding.bottomNavigationView, navController)
        }
    }