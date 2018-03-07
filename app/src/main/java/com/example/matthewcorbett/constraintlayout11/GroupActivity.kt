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

        firstHalfButton.setOnCheckedChangeListener { _, isChecked -> updateVisibility(firstHalfGroup, isChecked) }
        secondHalfButton.setOnCheckedChangeListener {  _, isChecked -> updateVisibility(secondHalfGroup, isChecked) }
        evenButton.setOnCheckedChangeListener {  _, isChecked -> updateVisibility(evenGroup, isChecked) }
        oddButton.setOnCheckedChangeListener {  _, isChecked -> updateVisibility(oddGroup, isChecked) }
    }

    private fun updateVisibility(group: View, visible: Boolean) {
        group.visibility = if (visible) {
            VISIBLE
        } else {
            GONE
        }
    }
}