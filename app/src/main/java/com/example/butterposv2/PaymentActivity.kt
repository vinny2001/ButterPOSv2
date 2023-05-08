package com.example.butterposv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PaymentActivity : AppCompatActivity() {

    private lateinit var cardNumberInput: EditText
    private lateinit var expDateInput: EditText
    private lateinit var cvvInput: EditText
    private lateinit var payButton: Button
    private lateinit var backPayButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_screen)

        cardNumberInput = findViewById(R.id.card_number_input)
        expDateInput = findViewById(R.id.exp_date_input)
        cvvInput = findViewById(R.id.cvv_input)
        payButton = findViewById(R.id.pay_button)
        backPayButton = findViewById(R.id.back_pay_button)


        payButton.setOnClickListener {
            val cardNumber = cardNumberInput.text.toString()
            val expDate = expDateInput.text.toString()
            val cvv = cvvInput.text.toString()

            if (cardNumber.isNotEmpty() && expDate.isNotEmpty() && cvv.isNotEmpty()) {
                // Process payment
                Toast.makeText(this, "Payment processed!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }
        backPayButton.setOnClickListener{
            finish()
        }
    }
}