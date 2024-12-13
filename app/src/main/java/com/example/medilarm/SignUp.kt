package com.example.medilarm

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.medilarm.LogIn

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signUpTextView = findViewById<TextView>(R.id.linkToSignUp)
        val text = "Already have an account? \nSign In"
        val spannableString = SpannableString(text)
        val signUpStartIndex = text.indexOf("Sign In")
        val signUpEndIndex = signUpStartIndex + "Sign In".length
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Intent to navigate to SignUpActivity
                val intent = Intent(this@SignUp, LogIn::class.java)
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