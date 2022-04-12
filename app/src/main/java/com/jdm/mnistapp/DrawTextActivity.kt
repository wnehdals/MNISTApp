package com.jdm.mnistapp

import android.graphics.Color
import android.util.Log
import android.util.Pair
import android.widget.Toast
import com.example.digitclassifier.tflite.Classifier
import com.jdm.mnistapp.base.ViewBindingActivity
import com.jdm.mnistapp.databinding.ActivityDrawTextBinding
import java.io.IOException
import java.util.*

class DrawTextActivity : ViewBindingActivity<ActivityDrawTextBinding>() {
    private val cls: Classifier by lazy {
        Classifier(this)
    }
    override val layoutId: Int
        get() = R.layout.activity_draw_text

    override fun initView() {
        with(binding){
            drawViewLayout.setStrokeWidth(50.0f)
            drawViewLayout.setColor(Color.WHITE)
            drawViewLayout.setBackgroundColor(Color.BLACK)
            runButton.setOnClickListener {
                val image = drawViewLayout.getBitmap()
                val res: Pair<Int, Float> = cls.classify(image)
                val outStr = String.format(Locale.ENGLISH, "%d, %.0f%%", res.first, res.second * 100.0f)
                resultText.text = outStr
            }
            clearButton.setOnClickListener {
                drawViewLayout.clearCanvas()
            }
        }

        try {
            cls.init()
        } catch (e: IOException) {

        }


    }

    override fun onDestroy() {
        cls.finish()
        super.onDestroy()

    }

    override fun subscribe() {
    }
}