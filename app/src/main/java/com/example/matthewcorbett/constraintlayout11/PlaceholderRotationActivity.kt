package com.example.matthewcorbett.constraintlayout11

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_placeholder.*

class PlaceholderRotationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeholder_rotation)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        TransitionManager.beginDelayedTransition(root)
    }
}