package com.example.dumpit.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dumpit.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: MaterialAutoCompleteTextView
    private lateinit var addressEditText: MaterialAutoCompleteTextView
    private lateinit var usernameEditText: MaterialAutoCompleteTextView
    private lateinit var emailEditText: MaterialAutoCompleteTextView
    private lateinit var passwordEditText: MaterialAutoCompleteTextView
    private lateinit var confirmPasswordEditText: MaterialAutoCompleteTextView
    private lateinit var genderSpinner: Spinner
    private lateinit var registerButton: AppCompatButton
    private lateinit var alreadyHasAccountTextView: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize views
        fullNameEditText = findViewById(R.id.fullNameEditText)
        addressEditText = findViewById(R.id.addressEditText)
        usernameEditText = findViewById(R.id.registerUsernameEditText)
        emailEditText = findViewById(R.id.registerEmailEditText)
        passwordEditText = findViewById(R.id.registerPasswordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        genderSpinner = findViewById(R.id.spinnerGender)
        registerButton = findViewById(R.id.registerButton)
        alreadyHasAccountTextView = findViewById(R.id.alreadyHasAccountTextView)

        // Set click listeners
        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val address = addressEditText.text.toString()
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Retrieve selected gender from Spinner
            val selectedGender: String = genderSpinner.selectedItem?.toString() ?: ""

            // Validate user input (add your own validation logic)
            if (isValidInput(fullName, address, username, email, password, confirmPassword, selectedGender)) {
                // Register the user using Firebase Authentication
                registerUser(email, password)
            } else {
                showToast("Please fill in all the fields and make sure passwords match.")
            }
        }

        alreadyHasAccountTextView.setOnClickListener {
            // Navigate to the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidInput(
        fullName: String,
        address: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
        gender: String
    ): Boolean {
        // Add your validation logic here
        // For example, you might check if the fields are not empty and if passwords match

        return fullName.isNotEmpty() &&
                address.isNotEmpty() &&
                username.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                gender.isNotEmpty() &&
                password == confirmPassword
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    showToast("Registration successful!")
                    // You can add additional logic here, such as navigating to the home screen
                } else {
                    // If registration fails, display a message to the user.
                    showToast("Registration failed. ${task.exception?.message}")
                }
            }
    }

    private fun showToast(message: String) {
        // Function to show a toast message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
