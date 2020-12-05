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

    private val images = arrayOf(R.drawable.zikr_image1, R.drawable.zikr_image2, R.drawable.zikr_image3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vibrateButton = findViewById(R.id.vibrate_button)

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

//        val button_vibrate = findViewById(R.id.button) as Button
//        button_vibrate.setOnClickListener {
//            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
//            } else {
//                vibrator.vibrate(500) // Vibrate method for below API Level 26
//            }

//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener() {
//            Toast.makeText(this, R.string.message, Toast.LENGTH_LONG).show()
//        }
    }

    private fun clickVibrateButton() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(500) // Vibrate method for below API Level 26
            }
    }

    private fun clickCounterButton() {
        count += 1
        updateText()
        Log.d("z", "Count")
        if (count%33 == 0) {
            Log.d("z", "Siap")
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
        updateText()
    }

    private fun clickResetButton() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Reset")

        builder.setMessage("Are sure you want to reset?")

        builder.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->  resetCounter()})

        builder.setNegativeButton("No", null)

        val alertDialog = builder.create()

        alertDialog.show()

    }

//        fun onTap(views: View) {
//            count++;
//
//            val textView = findViewById(R.id.button) as Button
//
//            val imageView = findViewById(R.id.images) as ImageView
//
//            textView.text = "$count"
//
//            if (count <= 33) {
//
//                imageView.setImageResource(R.drawable.zikr_image1)
//
//            } else if (count > 33 && count <= 66) {
//
//                imageView.setImageResource(R.drawable.zikr_image2)
//
//            } else if (count > 66 && count <= 99) {
//
//                imageView.setImageResource(R.drawable.zikr_image3)
//
//            } else {
//
//                imageView.setImageResource(R.drawable.zikr_image1)
//                count = 0
//
//            }
//        }
}
