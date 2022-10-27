package com.example.newquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {

    lateinit var easy: Button
    lateinit var medium: Button
    lateinit var hard: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        easy = findViewById(R.id.easy)

        easy.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            // start your next activity
            startActivity(intent)
        }

        medium = findViewById(R.id.medium)

        medium.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            // start your next activity
            startActivity(intent)
        }
        hard = findViewById(R.id.hard)

        hard.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}