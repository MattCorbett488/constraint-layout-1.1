package com.example.matthewcorbett.constraintlayout11

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_circular_constraints.*

class CircularConstraintActivity : AppCompatActivity() {
    private val RADIUS_INCREMENT_DEFAULT by lazy { resources.getDimensionPixelOffset(R.dimen.radius_increase_default) }
    private val ANGLE_INCREMENT_DEFAULT by lazy { resources.getInteger(R.integer.angle_increase_default) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_constraints)

        incrementInnerAngle.setOnClickListener { incrementAngle(innerElement, ANGLE_INCREMENT_DEFAULT) }

        incrementOuterAngle.setOnClickListener { incrementAngle(outerElement, ANGLE_INCREMENT_DEFAULT) }

        incrementInnerRadius.setOnClickListener { incrementRadius(innerElement, RADIUS_INCREMENT_DEFAULT) }

        incrementOuterRadius.setOnClickListener { incrementRadius(outerElement, RADIUS_INCREMENT_DEFAULT) }

        reset.setOnClickListener {
            reset(innerElement, R.dimen.inner_radius)
            reset(outerElement, R.dimen.outer_radius)
        }
    }

    private fun incrementAngle(view: View, angleIncrement: Int) {
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.circleAngle = (params.circleAngle + angleIncrement) % 360
        view.layoutParams = params
    }

    private fun incrementRadius(view: View, radiusIncrement: Int) {
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.circleRadius += radiusIncrement
        view.layoutParams = params
    }

    private fun reset(view: View, radiusRes: Int) {
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.circleAngle = 0f
        params.circleRadius = resources.getDimensionPixelOffset(radiusRes)
        view.layoutParams = params
    }
}