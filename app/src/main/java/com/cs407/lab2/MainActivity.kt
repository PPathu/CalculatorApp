package com.cs407.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Keeping original insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Calculator inputs and buttons
        val number1 = findViewById<EditText>(R.id.editTextNumber1)
        val number2 = findViewById<EditText>(R.id.editTextNumber2)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)

        // Addition
        buttonAdd.setOnClickListener {
            if (validateInput(number1, number2)) {
                val result = number1.text.toString().toDouble() + number2.text.toString().toDouble()
                goToResultActivity(result)
            }
        }

        // Subtraction
        buttonSubtract.setOnClickListener {
            if (validateInput(number1, number2)) {
                val result = number1.text.toString().toDouble() - number2.text.toString().toDouble()
                goToResultActivity(result)
            }
        }

        // Multiplication
        buttonMultiply.setOnClickListener {
            if (validateInput(number1, number2)) {
                val result = number1.text.toString().toDouble() * number2.text.toString().toDouble()
                goToResultActivity(result)
            }
        }

        // Division
        buttonDivide.setOnClickListener {
            if (validateInput(number1, number2)) {
                if (number2.text.toString().toDouble() == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero!", Toast.LENGTH_SHORT).show()
                } else {
                    val result = number1.text.toString().toDouble() / number2.text.toString().toDouble()
                    goToResultActivity(result)
                }
            }
        }
    }

    private fun validateInput(number1: EditText, number2: EditText): Boolean {
        if (number1.text.isEmpty() || number2.text.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun goToResultActivity(result: Double) {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("RESULT", result)
        }
        startActivity(intent)
    }
}
