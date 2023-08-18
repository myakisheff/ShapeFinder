package com.example.figurefind

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val startButton = findViewById<Button>(R.id.startButton)
        val infoButton = findViewById<Button>(R.id.infoButton)

        startButton.setOnClickListener {
            val game = Intent(this, GameActivity::class.java)
            game.putExtra("stage", 1)
            startActivity(game)
            finish()
        }

        infoButton.setOnClickListener {
            TODO("add new activity with information about game")
        }
    }

}