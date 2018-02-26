package com.example.matthewcorbett.constraintlayout11

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.RESTART
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_circular_constraints.*
import java.util.*

class CircularConstraintActivity : AppCompatActivity() {
    private val radiusIncrement by lazy { resources.getDimensionPixelOffset(R.dimen.radius_increase_default) }
    private val angleIncrement by lazy { resources.getInteger(R.integer.angle_increase_default) }
    private val defaultRotationTimeMs = 1200
    private var rotating = false
    private val random = Random()

    private lateinit var innerAnimator: ValueAnimator
    private lateinit var outerAnimator: ValueAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_constraints)

        initFaces()
        initAnimators()
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

        autorotate.setOnClickListener {
            if (!rotating) {
                setButtonsEnabled(false)
                innerAnimator.start()
                outerAnimator.start()
                rotating = true
            } else {
                innerAnimator.cancel()
                outerAnimator.cancel()
                rotating = false
                setButtonsEnabled(true)
            }
        }
    }

    private fun initAnimators() {
        innerAnimator = ValueAnimator.ofInt(0, 359)
        outerAnimator = ValueAnimator.ofInt(0, 359)

        innerAnimator.duration = defaultRotationTimeMs.toLong()
        outerAnimator.duration = (defaultRotationTimeMs * 1.5).toLong()

        innerAnimator.addUpdateListener { incrementAngle(innerElement, 1) }
        outerAnimator.addUpdateListener { incrementAngle(outerElement, 1) }

        innerAnimator.interpolator = LinearInterpolator()
        outerAnimator.interpolator = LinearInterpolator()

        innerAnimator.repeatMode = RESTART
        outerAnimator.repeatMode = RESTART

        innerAnimator.repeatCount = INFINITE
        outerAnimator.repeatCount = INFINITE
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

    private fun setButtonsEnabled(enabled: Boolean) {
        incrementOuterAngle.isEnabled = enabled
        incrementOuterRadius.isEnabled = enabled
        incrementInnerAngle.isEnabled = enabled
        incrementInnerRadius.isEnabled = enabled
        reset.isEnabled = enabled
    }
}