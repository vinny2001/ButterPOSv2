package com.example.butterposv2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var themeGroup: RadioGroup
    private lateinit var languageSpinner: Spinner
    private lateinit var paymentSpinner: Spinner
    private lateinit var notificationsSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        themeGroup = findViewById(R.id.theme_group)
        languageSpinner = findViewById(R.id.language_spinner)
        paymentSpinner = findViewById(R.id.payment_spinner)
        notificationsSwitch = findViewById(R.id.notifications_switch)

        /*// Set up the radio group for selecting the theme
        themeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.light_theme -> setTheme(R.style.Theme_ButterPOSv2)
                R.id.dark_theme -> setTheme(R.style.Theme_ButterPOSv2)
            }
            recreate() // Restart the activity to apply the new theme
        }*/
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener { finish() }

        // Set up the spinner for selecting the language
        val languages = resources.getStringArray(R.array.languages)
        val languageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = languageAdapter
        languageSpinner.setSelection(0) // Select the default language

        // Set up the spinner for selecting the default payment method
        val paymentMethods = resources.getStringArray(R.array.payment_methods)
        val paymentAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentMethods)
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        paymentSpinner.adapter = paymentAdapter
        paymentSpinner.setSelection(0) // Select the default payment method

        // Set up the switch for turning notifications on or off
        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save the notification setting to the app's preferences
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            preferences.edit().putBoolean("notifications_enabled", isChecked).apply()
        }
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val notificationsEnabled = preferences.getBoolean("notifications_enabled", true)

// Set the state of the notifications switch
        notificationsSwitch.isChecked = notificationsEnabled

        // Set up the button for providing feedback to the app's developers
        val feedbackButton: Button = findViewById(R.id.feedback_button)
        feedbackButton.setOnClickListener {
            // Launch the email app to send feedback to the app's developers
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("example@example.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email body")
                setPackage("com.google.android.gm")
            }

            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(emailIntent)
            } else {
                Toast.makeText(this, "Gmail app not installed", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun composeEmail(addresses: String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Only email apps handle this.
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }else {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show()
        }

    }

}
