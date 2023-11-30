package com.example.dumpit.Activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.dumpit.Model.*
import com.example.dumpit.R

class HomeActivity : AppCompatActivity() {
    private val biodegradableButton: AppCompatButton by lazy {
        findViewById(R.id.biodegradable_Button)
    }
    private val nonBiodegradableButton: AppCompatButton by lazy {
        findViewById(R.id.nonBiodegradable_Button)
    }
    private val chemicalButton: AppCompatButton by lazy {
        findViewById(R.id.chemical_Button)
    }
    private val industrialButton: AppCompatButton by lazy {
        findViewById(R.id.industrial_Button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        biodegradableButton.setOnClickListener {
            showItemList("Biodegradable Waste List", BiodegradableItemsDataSource.biodegradableItems)
        }

        nonBiodegradableButton.setOnClickListener {
            showItemList("Non Biodegradable Waste List", NonBiodegradableItemDataSource.nonBiodegradableItems)
        }

        chemicalButton.setOnClickListener {
            showItemList("Chemical Waste List", ChemicalItemsDataSource.chemicalItems)
        }

        industrialButton.setOnClickListener {
            showItemList("Industrial Waste List", IndustrialItemsDataSource.industrialItems)
        }
    }

    private fun showItemList(title: String, items: List<Any>) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)

        val formattedItems = items.joinToString("\n") { it.toString() }

        dialogBuilder.setMessage(formattedItems)
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = dialogBuilder.create()
        dialog.show()
    }
}
