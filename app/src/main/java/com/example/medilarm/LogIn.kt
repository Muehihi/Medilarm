package com.example.medilarm

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val btnSignIn: Button = findViewById(R.id.btn_signIn)

        btnSignIn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)

        }

        val signUpTextView = findViewById<TextView>(R.id.linkToSignUp)
        val text = "Don't have an account? \nSign Up"
        val spannableString = SpannableString(text)
        val signUpStartIndex = text.indexOf("Sign Up")
        val signUpEndIndex = signUpStartIndex + "Sign Up".length
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Intent to navigate to SignUpActivity
                val intent = Intent(this@LogIn, SignUp::class.java)
                startActivity(intent)
            }
        }

        spannableString.setSpan(
            clickableSpan,
            signUpStartIndex,
            signUpEndIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        signUpTextView.text = spannableString
        signUpTextView.movementMethod = LinkMovementMethod.getInstance()

    }
}

