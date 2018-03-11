package com.example.matthewcorbett.constraintlayout11

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.view.isVisible
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        firstHalfButton.setOnCheckedChangeListener { _, _ -> firstHalfGroup.updateVisibility() }
        evenButton.setOnCheckedChangeListener { _, _ -> evenGroup.updateVisibility() }
        oddButton.setOnCheckedChangeListener { _, _ -> oddGroup.updateVisibility() }
    }

    private fun View.updateVisibility() {
        visibility = if (isVisible) {
            GONE
        } else {
            VISIBLE
        }
    }
}