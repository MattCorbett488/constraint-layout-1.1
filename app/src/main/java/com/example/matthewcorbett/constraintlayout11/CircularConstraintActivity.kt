package com.example.matthewcorbett.constraintlayout11

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_circular_constraints.*

class CircularConstraintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_constraints)

        incrementInner.setOnClickListener { incrementAngle(innerElement, ANGLE_INCREMENT_DEFAULT) }

        incrementOuter.setOnClickListener { incrementAngle(outerElement, ANGLE_INCREMENT_DEFAULT) }

        reset.setOnClickListener {
            reset(innerElement)
            reset(outerElement)
        }
    }

    private fun incrementAngle(view: View, angleIncrement: Int) {
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.circleAngle = (params.circleAngle + angleIncrement) % 360
        view.layoutParams = params
    }

    private fun reset(view: View) {
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.circleAngle = 0f
        view.layoutParams = params
    }

    companion object Values {
        val ANGLE_INCREMENT_DEFAULT = 15
    }
}