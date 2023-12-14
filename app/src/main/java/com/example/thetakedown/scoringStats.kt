package com.example.thetakedown

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class scoringStats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Check background color and change if different than default
        val sharedPreferencesTheme = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        val backgroundColor = sharedPreferencesTheme.getInt("background_color", -1)
        if (backgroundColor != -1) {
            window?.decorView?.setBackgroundColor(backgroundColor)
        }
        setContentView(R.layout.activity_scoring_stats)

        //Buttons and variables
        val preferences = getSharedPreferences("match_data", MODE_PRIVATE)
        val redTakedownHStotal = preferences.getInt("redTakedownHStotal", 0)
        val greenTakedownHStotal = preferences.getInt("greenTakedownHStotal", 0)
        val redTakedownCoTotal = preferences.getInt("redTakedownCoTotal",0)
        val greenTakedownCoTotal = preferences.getInt("greenTakedownCoTotal",0)
        val homeBtnSS: Button = findViewById(R.id.homeBtnSS)
        val resetStats: Button = findViewById(R.id.resetStats)
        val greenTakedownTextHS: TextView = findViewById(R.id.greenTakedownsTextHS)
        val redTakedownTextHS: TextView = findViewById(R.id.redTakedownsTextHS)
        val greenTakedownTextCo: TextView = findViewById(R.id.greenTakedownsTextCo)
        val redTakedownTextCo: TextView = findViewById(R.id.redTakedownsTextCo)



        greenTakedownTextHS.text = greenTakedownHStotal.toString()
        redTakedownTextHS.text = redTakedownHStotal.toString()
        greenTakedownTextCo.text = greenTakedownCoTotal.toString()
        redTakedownTextCo.text = redTakedownCoTotal.toString()

        homeBtnSS.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Reset statistics
        resetStats.setOnClickListener{
            val redTakedownHStotal = 0
            val greenTakedownHStotal = 0
            val redTakedownCoTotal = 0
            val greenTakedownCoTotal = 0
            val editor = preferences.edit()
            editor.putInt("greenTakedownHStotal", greenTakedownHStotal)
            editor.putInt("redTakedownHStotal", redTakedownHStotal)
            editor.putInt("redTakedownCoTotal", redTakedownCoTotal)
            editor.putInt("greenTakedownCoTotal", greenTakedownCoTotal)
            editor.apply()

            greenTakedownTextHS.text = greenTakedownHStotal.toString()
            redTakedownTextHS.text = redTakedownHStotal.toString()
            greenTakedownTextCo.text = greenTakedownCoTotal.toString()
            redTakedownTextCo.text = redTakedownCoTotal.toString()

        }

    }

}