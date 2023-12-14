package com.example.thetakedown

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Check background color and change if different than default
        val sharedPreferencesTheme = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        val backgroundColor = sharedPreferencesTheme.getInt("background_color", -1)
        if (backgroundColor != -1) {
            window?.decorView?.setBackgroundColor(backgroundColor)
        }
        setContentView(R.layout.activity_help)

        val homeBtn: Button = findViewById(R.id.HhomeBtn)

        homeBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}