package com.example.thetakedown

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.thetakedown.databinding.ActivityMainBinding
private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Look for Background Color and change if different
        val sharedPreferencesTheme = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        val backgroundColor = sharedPreferencesTheme.getInt("background_color", -1)
        if (backgroundColor != -1) {
            window?.decorView?.setBackgroundColor(backgroundColor)
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Buttons
        val scoringButton: Button = findViewById(R.id.scoring)
        val helpBtn: Button = findViewById(R.id.help)
        val scoringStatsBtn: Button = findViewById(R.id.scoringStats)
        val userPreferences: Button = findViewById(R.id.userSettings)
        val wrestlerNameText: TextView = findViewById(R.id.wrestlerNameText)
        val coachNameText: TextView = findViewById(R.id.coachNameText)
        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val wrestlerName = sharedPreferences.getString("wrestler_name", "")
        val coachName = sharedPreferences.getString("coach_name", "")

        //Display Wrestler Name if name is set
        if (wrestlerName != ""){
            wrestlerNameText.text = "Wrestler: $wrestlerName"
        }

        //Display Coach Name if name is set
        if (coachName != ""){
            coachNameText.text = "Coach $coachName"
        }

        scoringButton.setOnClickListener {
            val intent = Intent(this, secondary::class.java)
            startActivity(intent)
        }

        helpBtn.setOnClickListener{
            val intent = Intent(this, help::class.java)
            startActivity(intent)
        }

        scoringStatsBtn.setOnClickListener{
            val intent = Intent(this, scoringStats::class.java)
            startActivity(intent)
        }

        userPreferences.setOnClickListener{
            val intent = Intent(this, user_preferences::class.java)
            startActivity(intent)
        }
    }
}