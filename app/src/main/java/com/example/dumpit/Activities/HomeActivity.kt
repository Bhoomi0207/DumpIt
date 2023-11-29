package com.example.dumpit.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.dumpit.Model.BiodegradableItem
import com.example.dumpit.Model.BiodegradableItemsDataSource
import com.example.dumpit.Model.NonBiodegradableItemsDataSource
import com.example.dumpit.R

class HomeActivity : AppCompatActivity() {
    val biodegradableButton: AppCompatButton by lazy {
        findViewById(R.id.biodegradable_Button)
    }
    val nonBiodegradableButton: AppCompatButton by lazy {
        findViewById(R.id.nonBiodegradable_Button)
    }
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


       biodegradableButton.setOnClickListener {
            showBiodegradableList()
        }
    }

    private fun showBiodegradableList() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Biodegradable Waste List")

        val items = BiodegradableItemsDataSource.biodegradableItems
            .map { "${it.name}: \$${it.pricePerKg} per kg" }
            .toTypedArray()

        dialogBuilder.setItems(items) { dialog, which ->
            // Handle item selection if needed
        }

        dialogBuilder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = dialogBuilder.create()
        dialog.show()

        nonBiodegradableButton.setOnClickListener {
            shownonBiodegradableList()
        }
    }
    private fun shownonBiodegradableList() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Non Biodegradable Waste List")

        val items = NonBiodegradableItemsDataSource.nonBiodegradableItems
            .map { "${it.name}: \$${it.pricePerKg} per kg" }
            .toTypedArray()

        dialogBuilder.setItems(items) { dialog, which ->
            // Handle item selection if needed
        }

        dialogBuilder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = dialogBuilder.create()
        dialog.show()






}
}