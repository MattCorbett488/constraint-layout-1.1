package com.example.matthewcorbett.constraintlayout11

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_dimension_constraints.*

class DimensionConstraintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dimension_constraints)

        val percent = (percentView.layoutParams as ConstraintLayout.LayoutParams).matchConstraintPercentWidth * 100
        percentText.text = getString(R.string.percent_text, percent.toInt())

        percentEntry.editText?.addTextChangedListener(percentChangeWatcher)
    }

    private var percentChangeWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s == null || s.toString().isEmpty()) {
                setPercentError()
                return
            }
            val percent = s.toString().toInt()
            if (percent in 0..100) {
                adjustPercent(percent)
            } else {
                setPercentError()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //no-op
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //no-op
        }
    }

    private fun adjustPercent(percent: Int) {
        percentText.text = getString(R.string.percent_text, percent)

        val params = percentView.layoutParams as ConstraintLayout.LayoutParams
        params.matchConstraintPercentWidth = (percent / 100.0).toFloat()
        percentView.layoutParams = params

    }

    private fun setPercentError() {
        percentEntry.error = "Number not between 0\nand 100"
    }
}