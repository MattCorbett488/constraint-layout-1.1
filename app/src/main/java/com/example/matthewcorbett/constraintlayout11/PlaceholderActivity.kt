package com.example.matthewcorbett.constraintlayout11

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_placeholder.*
import kotlinx.android.synthetic.main.include_placeholder.*

class PlaceholderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeholder)

        firstFace.onClick { Toast.makeText(this, "Here come Dat Boi!", LENGTH_SHORT).show() }

        secondFace.onClick { }

        thirdFace.onClick { Toast.makeText(this, "Gotta go fast", LENGTH_SHORT).show() }
    }

    private fun View.onClick(func: () -> Unit) {
        setOnClickListener {
            TransitionManager.beginDelayedTransition(root)
            func()
            placeholderFace.setContentId(this.id)
        }
    }
}