package com.example.thetakedown

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.ListPreference
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.recreate

class user_preferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Check background color and change if different than default
        val sharedPreferencesTheme = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        val backgroundColor = sharedPreferencesTheme.getInt("background_color", -1)
        if (backgroundColor != -1) {
            window?.decorView?.setBackgroundColor(backgroundColor)
        }
        setContentView(R.layout.activity_user_preferences)

        //Buttons and variables
        val nameBox: EditText = findViewById(R.id.NameBox)
        val coachBox: EditText = findViewById(R.id.coachBox)
        val saveBtn: Button = findViewById(R.id.saveBtn)
        val homeBtnUP: Button = findViewById(R.id.homeBtnUP)
        val themeRadioGroup: RadioGroup = findViewById(R.id.theme_radio_group)

        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        //theme color choice
        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val newThemeColor = when (checkedId) {
                R.id.white -> "white"
                R.id.teal -> "dark_theme_teal"
                R.id.lightGreen -> "dark_theme_light_green"
                else -> throw IllegalStateException("Unexpected radio button selected")
            }

            //theme color save
            val editor = sharedPreferencesTheme.edit()
            editor.putString("theme_color", newThemeColor)
            editor.apply()


            //Determine color resourceId to compare and apply
            val resourceId = resources.getIdentifier(newThemeColor, "color", packageName)
            if (resourceId != 0) {
                val color = resources.getColor(resourceId)
                val window = window
                window?.decorView?.setBackgroundColor(color)

                val editor = sharedPreferencesTheme.edit()
                editor.putInt("background_color", color)
                editor.apply()
            }
        }

        //Save Wrestler Name and/or coach's name if filled out
        saveBtn.setOnClickListener {
            if (nameBox.text.toString() != "") {
                var wrestlerName = nameBox.text.toString().trim()
                val editor = sharedPreferences.edit()
                editor.putString("wrestler_name", wrestlerName)
                editor.apply()
            }
            if (coachBox.text.toString() != ""){
                var coachName = coachBox.text.toString().trim()
                val editor = sharedPreferences.edit()
                editor.putString("coach_name", coachName)
                editor.apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        homeBtnUP.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}