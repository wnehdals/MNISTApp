package com.jdm.mnistapp

import android.graphics.Camera
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.camera2.internal.Camera2CamcorderProfileProvider
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.digitclassifier.tflite.Classifier
import com.google.common.util.concurrent.ListenableFuture
import com.jdm.mnistapp.base.ViewBindingFragment
import com.jdm.mnistapp.databinding.FragmentCameraBinding

class CameraFragment : ViewBindingFragment<FragmentCameraBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_camera

    override fun subscribe() {
    }

    override fun initView() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder2.surfaceProvider)
                }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(requireActivity(), cameraSelector, preview)

            } catch (exe: Exception) {

            }
        }, ContextCompat.getMainExecutor(requireContext()))


    }
    companion object {
        const val TAG = "CameraFragment"
        fun newInstance() = CameraFragment()
    }
}