package com.example.dumpit.registration

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.textfield.MaterialAutoCompleteTextView

import com.example.dumpit.Activities.HomeActivity
import com.example.dumpit.R
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: MaterialAutoCompleteTextView
    private lateinit var passwordEditText: MaterialAutoCompleteTextView
    private lateinit var loginButton: AppCompatButton
    private lateinit var createAccountTextView: TextView
    private lateinit var auth:FirebaseAuth // Firebase Authentication instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        createAccountTextView = findViewById(R.id.createAccountTextView)

        // Set click listeners
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validate username and password (add your own validation logic)
            if (isValidCredentials(username, password)) {
                // Authenticate with Firebase
                signInWithEmailAndPassword(username, password)
            } else {
                showToast("Invalid credentials. Please try again.")
            }
        }

        createAccountTextView.setOnClickListener {
            // Handle create account text view click
            // Navigate to the registration or sign-up activity
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // Add your validation logic here
        return username.isNotEmpty() && password.isNotEmpty()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showToast("Login successful")
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish() // Finish LoginActivity to prevent going back to it using the back button
                } else {
                    showToast("Authentication failed. Please check your credentials.")
                }
            }
    }
}
