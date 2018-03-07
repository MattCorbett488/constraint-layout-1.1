package com.example.matthewcorbett.constraintlayout11

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        firstHalfButton.setOnClickListener { updateVisibility(firstHalfGroup) }
        secondHalfButton.setOnClickListener { updateVisibility(secondHalfGroup) }
        evenButton.setOnClickListener { updateVisibility(evenGroup) }
        oddButton.setOnClickListener { updateVisibility(oddGroup) }
    }

    private fun updateVisibility(group: View) {
        group.visibility = if (group.visibility == GONE) {
            VISIBLE
        } else {
            GONE
        }
    }
}