package com.example.matthewcorbett.constraintlayout11

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.RESTART
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
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
        innerAnimator = initAnimator(innerElement, 1.0f)
        outerAnimator = initAnimator(outerElement, 1.5f)
        setListeners()
    }

    private fun initFaces() {
        val facesList = arrayListOf(R.mipmap.ic_adam, R.mipmap.ic_eric, R.mipmap.ic_matt)
        centerElement.setCircleImage(getFace(facesList))
        innerElement.setCircleImage(getFace(facesList))
        outerElement.setCircleImage(getFace(facesList))
    }

    private fun getFace(faces: ArrayList<Int>): Int {
        val face = faces[random.nextInt(faces.size)]
        faces.remove(face)
        return face
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

    private fun initAnimator(view: View, durationOffset: Float): ValueAnimator {
        return ValueAnimator.ofInt(0, 359).apply {
            duration = (defaultRotationTimeMs * durationOffset).toLong()
            addUpdateListener { incrementAngle(view, 1) }
            interpolator = LinearInterpolator()
            repeatMode = RESTART
            repeatCount = INFINITE
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

    private fun setButtonsEnabled(enabled: Boolean) {
        incrementOuterAngle.isEnabled = enabled
        incrementOuterRadius.isEnabled = enabled
        incrementInnerAngle.isEnabled = enabled
        incrementInnerRadius.isEnabled = enabled
        reset.isEnabled = enabled
    }

    private fun AppCompatImageView.setCircleImage(drawableRes: Int) {
        GlideApp.with(this).load(drawableRes).circleCrop().into(this)
    }
}