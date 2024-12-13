package com.example.medilarm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnClock: ImageView = findViewById(R.id.btn_Clock)

        btnClock.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)

        }

        val btnAdd: ImageView = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            val intent = Intent(this, Alarm::class.java)
            startActivity(intent)
        }

    }
}