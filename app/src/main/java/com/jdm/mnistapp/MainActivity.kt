package com.jdm.mnistapp

import android.content.Intent
import android.graphics.Camera
import com.jdm.mnistapp.base.ViewBindingActivity
import com.jdm.mnistapp.databinding.ActivityMainBinding

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initView() {
        initEvent()
    }
    fun initEvent() {
        binding.textRecogButton.setOnClickListener {
            Intent(this, DrawTextActivity::class.java).run {
                startActivity(this)
            }
        }
    }
    override fun subscribe() {

    }

}