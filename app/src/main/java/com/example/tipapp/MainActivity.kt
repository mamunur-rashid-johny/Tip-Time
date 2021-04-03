package com.example.tipapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipapp.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val cost = (binding.costOfService.text.toString()).toDoubleOrNull()
        if (cost != null){
            val selectedId = binding.tipOptions.checkedRadioButtonId
            val tipPercentage = when(selectedId){
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
            var tip = cost * tipPercentage
            if (binding.roundUpSwitch.isChecked){
                tip = ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
        }else{
            binding.tipResult.text = "Please a Amount"
        }

    }

}