package com.isc.apppropina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isc.apppropina.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costoServicio.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.str_cantidad, formattedTip)
    }
}

//prueba de git