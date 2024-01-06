package com.example.dumpit.registration

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dumpit.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class AdminRegistrationActivity : AppCompatActivity() {

    private lateinit var adminFullNameEdit: MaterialAutoCompleteTextView
    private lateinit var adminUsernameEdit: MaterialAutoCompleteTextView
    private lateinit var adminEmailEdit: MaterialAutoCompleteTextView
    private lateinit var adminPasswordEdit: MaterialAutoCompleteTextView
    private lateinit var adminConfirmPasswordEdit: MaterialAutoCompleteTextView
    private lateinit var adminRegister: AppCompatButton
    private lateinit var alreadyHasAdminAccountText: TextView

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize views
        adminFullNameEdit = findViewById(R.id.adminFullNameEditText)
        adminUsernameEdit = findViewById(R.id.adminUsernameEditText)
        adminEmailEdit = findViewById(R.id.adminEmailEditText)
        adminPasswordEdit = findViewById(R.id.adminPasswordEditText)
        adminConfirmPasswordEdit = findViewById(R.id.adminConfirmPasswordEditText)
        adminRegister = findViewById(R.id.adminRegisterButton)
        alreadyHasAdminAccountText = findViewById(R.id.alreadyHasAdminAccountTextView)

        // Set click listeners
        adminRegister.setOnClickListener {
            val fullName = adminFullNameEdit.text.toString()
            val username = adminUsernameEdit.text.toString()
            val email = adminEmailEdit.text.toString()
            val password = adminPasswordEdit.text.toString()
            val confirmPassword = adminConfirmPasswordEdit.text.toString()

            // Validate user input (add your own validation logic)
            if (isValidInput(fullName, username, email, password, confirmPassword)) {
                // Register the user using Firebase Authentication
                registerAdmin(email, password)
            } else {
                showToast("Please fill in all the fields and make sure passwords match.")
            }
        }

        alreadyHasAdminAccountText.setOnClickListener {
            // Navigate to the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidInput(
        fullName: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        // Add your validation logic here
        // For example, you might check if the fields are not empty and if passwords match
        return fullName.isNotEmpty() &&
                username.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                password == confirmPassword
    }

    private fun registerAdmin(email: String, password: String) {
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
