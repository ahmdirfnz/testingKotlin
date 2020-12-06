package com.example.custombutton1

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ncorti.slidetoact.SlideToActView

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var counterButton: Button
    private lateinit var resetButton: Button
    private lateinit var vibrateButton: Button

    private var count = 0
    private var imageindex = 0
    private var onOff = true

    private val images = arrayOf(R.drawable.zikr_image1, R.drawable.zikr_image2, R.drawable.zikr_image3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vibrateButton = findViewById(R.id.vibrate_button)
        vibrateButton.setOnClickListener {
            vibrate()
        }

        imageView = findViewById(R.id.images)
        imageView.setImageResource(images[0])

        counterButton = findViewById(R.id.counter_button)
        counterButton.setOnClickListener {
            clickCounterButton()
        }

        resetButton = findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            clickResetButton()
        }
    }

    private fun clickVibrateButton() {
        if (onOff) {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(500) // Vibrate method for below API Level 26
            }
        }
    }

    private fun clickCounterButton() {
        count += 1
        updateText()
        if (count%33 == 0) {
            imageindex += 1
            if (imageindex > images.size - 1) {
                imageindex = 0
            }
            imageView.setImageResource(images[imageindex])
            clickVibrateButton()
        }
    }

    private fun updateText() {
        counterButton.text = "$count"
    }

    private fun resetCounter() {
        count = 0
        imageindex = 0
        updateText()
        imageView.setImageResource(images[count])
    }

    private fun clickResetButton() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Reset")

        builder.setMessage("Are sure you want to reset?")

        builder.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->  resetCounter()})

        builder.setNegativeButton("No", null)

        val alertDialog = builder.create()

        alertDialog.show()

        resetCounter()          // reset all equal to zero

    }

    private fun vibrate() {
        onOff = !onOff
        if (onOff) {
            vibrateButton.text = "ON"
        } else {
            vibrateButton.text = "OFF"
        }
    }
}
