package com.example.custombutton1

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ncorti.slidetoact.SlideToActView

class MainActivity : AppCompatActivity() {

    var count = 0

    var test = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_vibrate = findViewById(R.id.button) as Button
        button_vibrate.setOnClickListener {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(500) // Vibrate method for below API Level 26
            }

//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener() {
//            Toast.makeText(this, R.string.message, Toast.LENGTH_LONG).show()
//        }
        }

        fun onTap(views: View) {
            count++;

            val textView = findViewById(R.id.button) as Button

            val imageView = findViewById(R.id.images) as ImageView

            textView.text = "$count"

            if (count <= 33) {

                imageView.setImageResource(R.drawable.zikr_image1)

            } else if (count > 33 && count <= 66) {

                imageView.setImageResource(R.drawable.zikr_image2)

            } else if (count > 66 && count <= 99) {

                imageView.setImageResource(R.drawable.zikr_image3)

            } else {

                imageView.setImageResource(R.drawable.zikr_image1)
                count = 0

            }
        }
    }
}
