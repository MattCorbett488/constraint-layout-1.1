package com.example.matthewcorbett.constraintlayout11

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        barrierGuidelineButton.setOnClickListener {
            startActivity(Intent(this, BarrierActivity::class.java))
        }
        circleConstraintButton.setOnClickListener {
            startActivity(Intent(this, CircularConstraintActivity::class.java))
        }
        percentRatioButton.setOnClickListener {
            startActivity(Intent(this, DimensionConstraintActivity::class.java))
        }

        placeholderButton.setOnClickListener {
            startActivity(Intent(this, PlaceholderActivity::class.java))
        }

        placeholderRotationButton.setOnClickListener {
            startActivity(Intent(this, PlaceholderRotationActivity::class.java))
        }
        //TODO: Group
    }
}