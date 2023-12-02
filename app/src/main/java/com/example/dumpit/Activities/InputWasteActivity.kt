package com.example.dumpit.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.dumpit.R
import com.example.dumpit.databinding.ActivityInputWasteBinding
import com.example.dumpit.databinding.ActivityRegistrationBinding
import com.google.firebase.firestore.FirebaseFirestore

class InputWasteActivity : AppCompatActivity() {
    val binding:ActivityInputWasteBinding by lazy{
    ActivityInputWasteBinding.inflate(layoutInflater)
    }


    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set up the spinner with waste categories
        val wasteCategories = resources.getStringArray(R.array.waste_categories)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, wasteCategories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerWaste.adapter = spinnerAdapter

        // Handle the "Add Waste" button click
        binding.addwaste.setOnClickListener {
            addWaste()
        }
    }

    private fun addWaste() {
        val wasteType = binding.WasteTypeEditText.text.toString()
        val address = binding.locationEditText.text.toString()
        val approxPrice = binding.approxPriceEditText.text.toString().toDoubleOrNull()
        val quantity = binding.QuantityEditText.text.toString().toDoubleOrNull()
        val wasteCategory = binding.spinnerWaste.selectedItem.toString()

        if (wasteType.isNotEmpty() && address.isNotEmpty() && approxPrice != null && quantity != null) {
            // Create a map with the data
            val wasteData = hashMapOf(
                "wasteType" to wasteType,
                "address" to address,
                "approxPrice" to approxPrice,
                "quantity" to quantity,
                "wasteCategory" to wasteCategory
            )

            // Add the data to Firestore
            firestore.collection("wasteListings")
                .add(wasteData)
                .addOnSuccessListener {
                    // Display a success message
                    Toast.makeText(this, "Waste added successfully!", Toast.LENGTH_SHORT).show()

                    // Clear input fields if needed
                    binding.WasteTypeEditText.text.clear()
                    binding.locationEditText.text.clear()
                    binding.approxPriceEditText.text.clear()
                    binding.QuantityEditText.text.clear()
                }
                .addOnFailureListener { e ->
                    // Display an error message if the data couldn't be added
                    Toast.makeText(this, "Error adding waste: $e", Toast.LENGTH_SHORT).show()
                }
        } else {
            // Display an error message if any field is empty
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
