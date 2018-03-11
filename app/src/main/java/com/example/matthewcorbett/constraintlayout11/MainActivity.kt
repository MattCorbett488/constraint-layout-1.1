package com.example.matthewcorbett.constraintlayout11

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        barrierGuidelineButton.setIntentListener(BarrierActivity::class.java)
        circleConstraintButton.setIntentListener(CircularConstraintActivity::class.java)
        percentRatioButton.setIntentListener(DimensionConstraintActivity::class.java)
        placeholderButton.setIntentListener(PlaceholderActivity::class.java)
        placeholderRotationButton.setIntentListener(PlaceholderRotationActivity::class.java)
        groupButton.setIntentListener(GroupActivity::class.java)
    }

    private fun AppCompatButton.setIntentListener(clazz: Class<out AppCompatActivity>) = setOnClickListener {
        startActivity(Intent(this@MainActivity, clazz))
    }
}