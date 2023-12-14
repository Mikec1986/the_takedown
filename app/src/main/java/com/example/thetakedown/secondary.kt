package com.example.thetakedown

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView


class secondary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Check background color and change if different than default
        val sharedPreferencesTheme = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        val backgroundColor = sharedPreferencesTheme.getInt("background_color", -1)
        if (backgroundColor != -1) {
            window?.decorView?.setBackgroundColor(backgroundColor)
        }
        setContentView(R.layout.activity_secondary)

        //Buttons and Variables
        val ankletColorS: TextView = findViewById(R.id.nameAnklet)
        val ankletRadio: RadioGroup = findViewById(R.id.ankletRadio)
        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val wrestlerName = sharedPreferences.getString("wrestler_name", "Default Name")
        var selectedOption: String = "ignore"

        //Set the anklet color of the default wrestler
        ankletColorS.text = "$wrestlerName Anklet Color"

        ankletRadio.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.ignore -> {
                    selectedOption = "ignore"
                }

                R.id.redAnklet -> {
                    selectedOption = "red"
                }

                R.id.greenAnklet -> {
                    selectedOption = "green"
                }
            }
        }

            val homeButton: Button = findViewById(R.id.home)

            homeButton.setOnClickListener {
                // Create an Intent to start SecondaryActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            val hsfolkstyleButton: Button = findViewById(R.id.hsfolkstyle)

            hsfolkstyleButton.setOnClickListener {
                // Create an Intent to start SecondaryActivity
                val intent = Intent(this, folkstyle::class.java)
                intent.putExtra(
                    "selected_option",
                    selectedOption
                ) // Pass the selected option to the next activity
                startActivity(intent)
            }

            val CfolkstyleButton: Button = findViewById(R.id.cfolkstyle)

            CfolkstyleButton.setOnClickListener {
                // Create an Intent to start SecondaryActivity
                val intent = Intent(this, collegefolkstyle::class.java)
                intent.putExtra(
                    "selected_option",
                    selectedOption
                )
                startActivity(intent)
            }
        }
    }
