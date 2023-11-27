package com.example.dumpit.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import com.example.dumpit.MainActivity
import com.example.dumpit.R
import android.os.Handler


class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
                // Delay for 2 seconds and then start the main activity
        // Delay for 2 seconds and then start the main activity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 2000 milliseconds
    }

}