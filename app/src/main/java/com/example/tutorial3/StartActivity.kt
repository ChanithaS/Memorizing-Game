package com.example.tutorial3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var start = findViewById<Button>(R.id.Startbtn)
        start.setOnClickListener{
            val startGame = Intent(this, MainActivity::class.java)
            startActivity(startGame)
        }
    }
}