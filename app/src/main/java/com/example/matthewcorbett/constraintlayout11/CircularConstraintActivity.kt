package com.example.matthewcorbett.constraintlayout11

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.view.View
import kotlinx.android.synthetic.main.activity_circular_constraints.*
import java.util.*
import kotlin.collections.ArrayList

class CircularConstraintActivity : AppCompatActivity() {
    private val radiusIncrement by lazy { resources.getDimensionPixelOffset(R.dimen.radius_increase_default) }
    private val angleIncrement by lazy { resources.getInteger(R.integer.angle_increase_default) }

    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_constraints)

        initFaces()
        setListeners()
    }

    private fun initFaces() {
        val facesList = arrayListOf(R.mipmap.ic_adam, R.mipmap.ic_eric, R.mipmap.ic_matt)
        centerElement.setImageDrawable(getFace(facesList))
        innerElement.setImageDrawable(getFace(facesList))
        outerElement.setImageDrawable(getFace(facesList))
    }

    private fun getFace(faces: ArrayList<Int>): Drawable {
        val face = faces[random.nextInt(faces.size)]
        faces.remove(face)
        return ContextCompat.getDrawable(this, face)
    }

    private fun setListeners() {
        incrementInnerAngle.setOnClickListener { incrementAngle(innerElement, angleIncrement) }

        incrementOuterAngle.setOnClickListener { incrementAngle(outerElement, angleIncrement) }

        incrementInnerRadius.setOnClickListener { incrementRadius(innerElement, radiusIncrement) }

        incrementOuterRadius.setOnClickListener { incrementRadius(outerElement, radiusIncrement) }

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