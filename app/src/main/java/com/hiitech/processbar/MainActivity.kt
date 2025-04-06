package com.hiitech.processbar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hiitech.progressbar.CustomVerticalProgressBar

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: CustomVerticalProgressBar
    private lateinit var progressText: TextView
    private var brightness = 0.5f // Default brightness
    private val step = 0.05f // Brightness change per button press

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.brightnessProgress)
        progressText = findViewById(R.id.progress)

        val increaseButton: Button = findViewById(R.id.increaseButton)
        val decreaseButton: Button = findViewById(R.id.decreaseButton)

        // Set initial brightness and progress
        updateBrightnessDisplay()

        increaseButton.setOnClickListener {
            brightness = (brightness + step).coerceIn(0f, 1f)
            updateBrightnessDisplay()
        }

        decreaseButton.setOnClickListener {
            brightness = (brightness - step).coerceIn(0f, 1f)
            updateBrightnessDisplay()
        }
    }

    private fun updateBrightnessDisplay() {
        // Apply brightness to window
        val layoutParams = window.attributes
        layoutParams.screenBrightness = brightness
        window.attributes = layoutParams

        // Convert brightness to scale of 0–32 for your custom progress bar
        val progressLevel = (brightness * 32).toInt()
        progressBar.progress = progressLevel
        progressText.text = "Progress: ${"%.2f".format(brightness)}"
    }
}