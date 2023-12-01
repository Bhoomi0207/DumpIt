package com.example.dumpit.Activities

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import android.os.Bundle
import com.example.dumpit.Model.BiodegradableItemsDataSource
import com.example.dumpit.Model.NonBiodegradableItemDataSource
import com.example.dumpit.Model.ChemicalItemsDataSource
import com.example.dumpit.Model.IndustrialItemsDataSource
import com.example.dumpit.R

class HomeActivity : AppCompatActivity() {
    val biodegradableButton: AppCompatButton by lazy {
        findViewById(R.id.biodegradable_Button)
    }
    val nonBiodegradableButton: AppCompatButton by lazy {
        findViewById(R.id.nonBiodegradable_Button)
    }
    val chemicalbutton: AppCompatButton by lazy {
        findViewById(R.id.chemical_Button)
    }
    val industrialbutton: AppCompatButton by lazy {
        findViewById(R.id.industrial_Button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        biodegradableButton.setOnClickListener {
            showBiodegradableList()
        }

        nonBiodegradableButton.setOnClickListener {
            shownonBiodegradableList()
        }
        chemicalbutton.setOnClickListener {
            showBiodegradableList()
        }

        industrialbutton.setOnClickListener {
            shownonBiodegradableList()
        }
    }

    private fun showBiodegradableList() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Biodegradable Waste List")

        val items = BiodegradableItemsDataSource.biodegradableItems
            .map { "${it.name}: ₹${it.pricePerKg} per kg" }
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

    private fun shownonBiodegradableList() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Non Biodegradable Waste List")

        val items = NonBiodegradableItemDataSource.nonBiodegradableItems
            .map { "${it.name}: ₹${it.pricePerKg} per kg" }
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

    private fun showChemicalList() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Chemical Waste List")

        val items = ChemicalItemsDataSource.chemicalItems
            .map { "${it.name}: ₹${it.pricePerKg} per kg" }
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

    private fun showIndustrialList() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Industrial Waste List")

        val items = IndustrialItemsDataSource.industrialItems
            .map { "${it.name}: ₹${it.pricePerKg} per kg" }
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
