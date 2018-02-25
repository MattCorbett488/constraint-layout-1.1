package com.example.matthewcorbett.constraintlayout11

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_guideline_barrier.*

class BarrierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_guideline_barrier)

        switchButton.setOnClickListener {
            val firstText = barrierFirstText.text.toString()
            setFirstText(barrierSecondText.text.toString())
            setSecondText(firstText)
        }
    }

    private fun setFirstText(text: String) {
        barrierFirstText.text = text
        noBarrierFirstText.text = text
    }

    private fun setSecondText(text: String) {
        barrierSecondText.text = text
        noBarrierSecondText.text = text
    }
}