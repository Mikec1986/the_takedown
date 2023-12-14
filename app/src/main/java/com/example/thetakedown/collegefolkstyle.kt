package com.example.thetakedown

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class collegefolkstyle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Check background color and change if different than default
        val sharedPreferencesTheme = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        val backgroundColor = sharedPreferencesTheme.getInt("background_color", -1)
        if (backgroundColor != -1) {
            window?.decorView?.setBackgroundColor(backgroundColor)
        }
        setContentView(R.layout.activity_collegefolkstyle)

        //Buttons and variables
        var redScore = 0
        var greenScore = 0
        var greenCautionCount = 0
        var redCautionCount = 0
        var redStallingCount = 0
        var greenStallingCount = 0
        var greenIllegalMoveCount = 0
        var redIllegalMoveCount = 0
        var ridingGreenCount = 0
        var ridingRedCount = 0
        val greenTakedownbutton: Button = findViewById(R.id.takedown2Green)
        val redTakedownbutton: Button = findViewById(R.id.takedown2Red)
        val redNearFalltwoButton: Button = findViewById(R.id.nfall2Red)
        val greenNearFalltwoButton: Button = findViewById(R.id.nfall2Green)
        val redNearFallfourButton: Button = findViewById(R.id.nfall4Red)
        val greenNearFallfourButton: Button = findViewById(R.id.nfall4Green)
        val redEscapeButton: Button = findViewById(R.id.escapeRed)
        val greenEscapeButton: Button = findViewById(R.id.escapeGreen)
        val redReversalButton: Button = findViewById(R.id.reversalRed)
        val greenReversalButton: Button = findViewById(R.id.reversalGreen)
        val redStallingButton: Button = findViewById(R.id.stallingRed)
        val greenStallingButton: Button = findViewById(R.id.stallingGreen)
        val greenCautionButton: Button = findViewById(R.id.cautionGreen)
        val redCautionButton: Button = findViewById(R.id.cautionRed)
        val redIllegalButton: Button = findViewById(R.id.illegalMoveRed)
        val greenIllegalButton: Button = findViewById(R.id.illegalMoveGreen)
        val resetMatchButtonCo: Button = findViewById(R.id.resetmatchCo)
        val backButton: Button = findViewById(R.id.backbuttonCo)
        val redscoreView: TextView = findViewById(R.id.redScoreCo)
        val greenscoreView: TextView = findViewById(R.id.greenScoreCo)
        val greenStallingCountView: TextView = findViewById(R.id.stallingGreenCount)
        val redStallingCountView: TextView = findViewById(R.id.stallingCountRed)
        val greenCautionView: TextView = findViewById(R.id.cautionCountGreen)
        val redCautionView: TextView = findViewById(R.id.cautionCountRed)
        val redIllegalView: TextView = findViewById(R.id.illegalMoveCountred)
        val greenIllegalView: TextView = findViewById(R.id.illegalMoveCountGreen)
        val ridingTimeRed: Button = findViewById(R.id.ridingtimeRed)
        val ridingTimeGreen: Button = findViewById(R.id.ridingtimeGreen)
        val endMatchBtnCo: Button = findViewById(R.id.endMatchbtnCo)
        val redTextCo: TextView = findViewById(R.id.redTextHS)
        val greenTextCo: TextView = findViewById(R.id.greenTextHS)
        val selectedOption = intent.getStringExtra("selected_option")
        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val wrestlerName = sharedPreferences.getString("wrestler_name", "Default Name")
        greenscoreView.text = greenScore.toString()
        redscoreView.text = redScore.toString()
        greenStallingCountView.text = greenStallingCount.toString()
        redStallingCountView.text = redStallingCount.toString()
        greenCautionView.text = greenCautionCount.toString()
        redCautionView.text = redCautionCount.toString()
        greenIllegalView.text = greenIllegalMoveCount.toString()
        redIllegalView.text = redIllegalMoveCount.toString()

        //Change anklet color to default wrestler's name
        if (selectedOption == "red"){
            redTextCo.text = wrestlerName
            greenTextCo.text= "Green"
        }
        if (selectedOption == "green"){
            greenTextCo.text = wrestlerName
            redTextCo.text = "Red"
        }
        if (selectedOption == "ignore"){
            redTextCo.text = "Red"
            greenTextCo.text = "Green"
        }

        //Takedown tracking for ScoringStats activity
        var greenTakedownsCo = 0
        var redTakedownsCo = 0
        var redTakedownCoTotal: Int = 0
        var greenTakedownCoTotal: Int = 0

        val preferences = getSharedPreferences("match_data", MODE_PRIVATE)
        redTakedownCoTotal = preferences.getInt("redTakedownCoTotal", 0)
        greenTakedownCoTotal = preferences.getInt("greenTakedownCoTotal", 0)


        //Scoring
        greenTakedownbutton.setOnClickListener {
            greenScore += 3 //This was changed to reflect a recent rule change
            greenTakedownsCo += 1
            greenscoreView.text = greenScore.toString()
        }

        greenNearFalltwoButton.setOnClickListener {
            greenScore += 2
            greenscoreView.text = greenScore.toString()
        }

        greenNearFallfourButton.setOnClickListener {
            greenScore += 3
            greenscoreView.text = greenScore.toString()
        }

        greenEscapeButton.setOnClickListener {
            greenScore += 1
            greenscoreView.text = greenScore.toString()
        }

        greenReversalButton.setOnClickListener {
            greenScore += 2
            greenscoreView.text = greenScore.toString()
        }

        //Riding Time scoring for College
        ridingTimeGreen.setOnClickListener{
            if (ridingRedCount > 0){
                ridingRedCount = 0
                redScore -= 1
                redscoreView.text = redScore.toString()
            }
            if (ridingGreenCount == 0){
                ridingGreenCount += 1
            }
            else {
                ridingGreenCount = 0
                greenScore -= 1
                greenscoreView.text = greenScore.toString()
            }

            if (ridingGreenCount == 1){
                greenScore += 1
            }
            greenscoreView.text = greenScore.toString()
        }

        ridingTimeRed.setOnClickListener{
            if (ridingGreenCount > 0){
                ridingGreenCount = 0
                greenScore -= 1
                greenscoreView.text = greenScore.toString()
            }
            if (ridingRedCount == 0){
                ridingRedCount += 1
            }
            else {
                ridingRedCount = 0
                redScore -= 1
                redscoreView.text = redScore.toString()
            }

            if (ridingRedCount == 1) {
                redScore += 1
            }
            redscoreView.text = redScore.toString()
        }

        redTakedownbutton.setOnClickListener {
            redScore += 3
            redTakedownsCo += 1
            redscoreView.text = redScore.toString()
        }

        redNearFalltwoButton.setOnClickListener {
            redScore += 2
            redscoreView.text = redScore.toString()
        }

        redNearFallfourButton.setOnClickListener {
            redScore += 3
            redscoreView.text = redScore.toString()
        }

        redEscapeButton.setOnClickListener {
            redScore += 1
            redscoreView.text = redScore.toString()
        }

        redReversalButton.setOnClickListener {
            redScore += 2
            redscoreView.text = redScore.toString()
        }


        //Stalling Scoring for College
        greenStallingButton.setOnClickListener {
            greenStallingCount += 1
            if (greenStallingCount == 2) {
                redScore += 1
            }
            if (greenStallingCount == 3) {
                redScore += 1
            }
            if (greenStallingCount == 4) {
                redScore += 2
            }
            redscoreView.text = redScore.toString()
            greenStallingCountView.text = greenStallingCount.toString()
        }

        redStallingButton.setOnClickListener {
            redStallingCount += 1
            if (redStallingCount == 2) {
                greenScore += 1
            }
            if (redStallingCount == 3) {
                greenScore += 1
            }
            if (redStallingCount == 4) {
                greenScore += 2
            }
            greenscoreView.text = greenScore.toString()
            redStallingCountView.text = redStallingCount.toString()
        }

        //Caution Scoring for College
        greenCautionButton.setOnClickListener {
            greenCautionCount += 1
            if (greenCautionCount >= 3) {
                redScore += 1
            }
            redscoreView.text = redScore.toString()
            greenCautionView.text = greenCautionCount.toString()
        }

        redCautionButton.setOnClickListener {
            redCautionCount += 1
            if (redCautionCount >= 3) {
                greenScore += 1
            }
            greenscoreView.text = greenScore.toString()
            redCautionView.text = redCautionCount.toString()
        }

        //Illegal Move scoring for College
        greenIllegalButton.setOnClickListener {
            greenIllegalMoveCount += 1
            redScore += 1
            redscoreView.text = redScore.toString()
            greenIllegalView.text = greenIllegalMoveCount.toString()
        }

        redIllegalButton.setOnClickListener {
            redIllegalMoveCount += 1
            greenScore += 1
            greenscoreView.text = greenScore.toString()
            redIllegalView.text = redIllegalMoveCount.toString()
        }

        backButton.setOnClickListener {
            val intent = Intent(this, secondary::class.java)
            startActivity(intent)
        }

        //End match and save takedown counts for ScoringStats activity
        endMatchBtnCo.setOnClickListener{
            redTakedownCoTotal += redTakedownsCo
            greenTakedownCoTotal += greenTakedownsCo
            val preferences = getSharedPreferences("match_data", MODE_PRIVATE)
            with (preferences.edit()) {
                putInt("redTakedownCoTotal", redTakedownCoTotal)
                putInt("greenTakedownCoTotal", greenTakedownCoTotal)
                apply()
            }

            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }

        //Reset match and variables
        resetMatchButtonCo.setOnClickListener{
            greenScore = 0
            redScore = 0
            greenCautionCount = 0
            redCautionCount = 0
            greenIllegalMoveCount = 0
            redIllegalMoveCount = 0
            greenStallingCount = 0
            redStallingCount = 0
            redTakedownsCo = 0
            greenTakedownsCo = 0
            ridingGreenCount = 0
            ridingRedCount = 0

            greenscoreView.text = greenScore.toString()
            redscoreView.text = redScore.toString()
            greenStallingCountView.text = greenStallingCount.toString()
            redStallingCountView.text = redStallingCount.toString()
            greenCautionView.text = greenCautionCount.toString()
            redCautionView.text = redCautionCount.toString()
            greenIllegalView.text = greenIllegalMoveCount.toString()
            redIllegalView.text = redIllegalMoveCount.toString()
        }
    }
}