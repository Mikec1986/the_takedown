package com.example.thetakedown

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class folkstyle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Check background color and change if different than default
        val sharedPreferencesTheme = getSharedPreferences("ThemePref", Context.MODE_PRIVATE)
        val backgroundColor = sharedPreferencesTheme.getInt("background_color", -1)
        if (backgroundColor != -1) {
            window?.decorView?.setBackgroundColor(backgroundColor)
        }
        setContentView(R.layout.activity_folkstyle)

        //Buttons and variables
        var redScore = 0
        var greenScore = 0
        var greenCautionCount = 0
        var redCautionCount = 0
        var redStallingCount = 0
        var greenStallingCount = 0
        var greenIllegalMoveCount = 0
        var redIllegalMoveCount = 0
        val greenTakedownbutton: Button = findViewById(R.id.takedown2Green)
        val redTakedownbutton: Button = findViewById(R.id.takedown2Red)
        val redNearFalltwoButton: Button = findViewById(R.id.nfall2Red)
        val greenNearFalltwoButton: Button = findViewById(R.id.nfall2Green)
        val redNearFallthreeButton: Button = findViewById(R.id.nfall3Red)
        val greenNearFallthreeButton: Button = findViewById(R.id.nfall3Green)
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
        val resetMatchButton: Button = findViewById(R.id.resetmatch)
        val endMatchBtn: Button = findViewById(R.id.endMatchbtn)
        val backButton: Button = findViewById(R.id.backbutton)
        val redscoreView: TextView = findViewById(R.id.redScore)
        val greenscoreView: TextView = findViewById(R.id.greenScore)
        val greenStallingCountView: TextView = findViewById(R.id.stallingGreenCount)
        val redStallingCountView: TextView = findViewById(R.id.stallingCountRed)
        val greenCautionView: TextView = findViewById(R.id.cautionCountGreen)
        val redCautionView: TextView = findViewById(R.id.cautionCountRed)
        val redIllegalView: TextView = findViewById(R.id.illegalMoveCountred)
        val greenIllegalView: TextView = findViewById(R.id.illegalMoveCountGreen)
        val selectedOption = intent.getStringExtra("selected_option")
        val redTextHS: TextView = findViewById(R.id.redTextHS)
        val greenTextHS: TextView = findViewById(R.id.greenTextHS)
        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val wrestlerName = sharedPreferences.getString("wrestler_name", "Default Name")

        //Change anklet color to default wrestlers name based on selection on secondary
        if (selectedOption == "red"){
            redTextHS.text = wrestlerName
            greenTextHS.text= "Green"
        }
        if (selectedOption == "green"){
            greenTextHS.text = wrestlerName
            redTextHS.text = "Red"
        }
        if (selectedOption == "ignore"){
            redTextHS.text = "Red"
            greenTextHS.text = "Green"
        }

        //Takedown counting for ScoringStats activity
        var greenTakedowns = 0
        var redTakedowns = 0
        var redTakedownHStotal: Int = 0
        var greenTakedownHStotal: Int = 0

        val preferences = getSharedPreferences("match_data", MODE_PRIVATE)
        redTakedownHStotal = preferences.getInt("redTakedownHStotal", 0)
        greenTakedownHStotal = preferences.getInt("greenTakedownHStotal", 0)

        greenscoreView.text = greenScore.toString()
        redscoreView.text = redScore.toString()
        greenStallingCountView.text = greenStallingCount.toString()
        redStallingCountView.text = redStallingCount.toString()
        greenCautionView.text = greenCautionCount.toString()
        redCautionView.text = redCautionCount.toString()
        greenIllegalView.text = greenIllegalMoveCount.toString()
        redIllegalView.text = redIllegalMoveCount.toString()


        //Scoring
        greenTakedownbutton.setOnClickListener {
            greenScore += 2
            greenTakedowns+=1
            greenscoreView.text = greenScore.toString()
        }

        greenNearFalltwoButton.setOnClickListener {
            greenScore += 2
            greenscoreView.text = greenScore.toString()
        }

        greenNearFallthreeButton.setOnClickListener {
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

        redTakedownbutton.setOnClickListener {
            redScore += 2
            redTakedowns +=1
            redscoreView.text = redScore.toString()
        }

        redNearFalltwoButton.setOnClickListener {
            redScore += 2
            redscoreView.text = redScore.toString()
        }

        redNearFallthreeButton.setOnClickListener {
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

        //Scoring Stalling for HS
        greenStallingButton.setOnClickListener {
            greenStallingCount += 1
            if (greenStallingCount == 2){
                redScore += 1
            }
            if (greenStallingCount == 3){
                redScore += 1
            }
            if (greenStallingCount == 4){
                redScore += 2
            }
            redscoreView.text = redScore.toString()
            greenStallingCountView.text = greenStallingCount.toString()
        }

        redStallingButton.setOnClickListener {
            redStallingCount += 1
            if (redStallingCount == 2){
                greenScore += 1
            }
            if (redStallingCount == 3){
                greenScore += 1
            }
            if (redStallingCount == 4){
                greenScore += 2
            }
            greenscoreView.text = greenScore.toString()
            redStallingCountView.text = redStallingCount.toString()
        }

        //Caution Scoring for HS
        greenCautionButton.setOnClickListener {
            greenCautionCount += 1
            if (greenCautionCount >= 3){
                redScore += 1
            }
            redscoreView.text = redScore.toString()
            greenCautionView.text = greenCautionCount.toString()
        }

        redCautionButton.setOnClickListener {
            redCautionCount += 1
            if (redCautionCount >= 3){
                greenScore += 1
            }
            greenscoreView.text = greenScore.toString()
            redCautionView.text = redCautionCount.toString()
        }

        //Illegal move scoring for HS
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
        endMatchBtn.setOnClickListener{
            redTakedownHStotal += redTakedowns
            greenTakedownHStotal += greenTakedowns
            val preferences = getSharedPreferences("match_data", MODE_PRIVATE)
            with (preferences.edit()) {
                putInt("redTakedownHStotal", redTakedownHStotal)
                putInt("greenTakedownHStotal", greenTakedownHStotal)
                apply()
            }

            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }

        //Reset Match and variables
        resetMatchButton.setOnClickListener{
            greenScore = 0
            redScore = 0
            greenCautionCount = 0
            redCautionCount = 0
            redStallingCount = 0
            greenStallingCount = 0
            greenIllegalMoveCount = 0
            redIllegalMoveCount = 0
            redTakedowns = 0
            greenTakedowns = 0


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