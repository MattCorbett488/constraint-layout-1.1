package com.example.matthewcorbett.constraintlayout11

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.Gravity.CENTER
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_placeholder.*
import kotlinx.android.synthetic.main.include_placeholder.*

@SuppressLint("ShowToast")
class PlaceholderActivity : AppCompatActivity() {
    private val toast by lazy { Toast.makeText(this, "", LENGTH_SHORT) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placeholder)

        toast.setGravity(CENTER, 0, 0)

        firstFace.onClick { toast.showWithText(R.string.here_come) }

        secondFace.onClick { }

        thirdFace.onClick { toast.showWithText(R.string.go_fast) }
    }

    private inline fun View.onClick(crossinline func: () -> Unit) = setOnClickListener {
        func()
        placeholderFace.setContentId(this.id)
        TransitionManager.beginDelayedTransition(root, ChangeBounds().apply {
            duration = 1000
            interpolator = OvershootInterpolator()
        })
    }

    private fun Toast.showWithText(messageRes: Int) = this.apply {
        setText(messageRes)
        show()
    }
}