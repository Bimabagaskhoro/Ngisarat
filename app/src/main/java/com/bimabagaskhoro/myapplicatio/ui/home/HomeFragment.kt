package com.bimabagaskhoro.myapplicatio.ui.home

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bimabagaskhoro.myapplicatio.R
import com.bimabagaskhoro.myapplicatio.databinding.FragmentHomeBinding
import com.vmadalin.easypermissions.EasyPermissions
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(activity, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(activity, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(activity, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(activity, R.anim.to_bottom_anim) }
    private var clicked = false

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mInputSize = 200
    private val mModelPath = "bisindo.tflite"
    private val mLabelPath = "labels.txt"
    private lateinit var classifier: Classifier
    lateinit var bitmap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reqActivity = requireActivity() as AppCompatActivity
        reqActivity.title = getString(R.string.title_home)
        reqActivity.setSupportActionBar(binding.toolbar)

        initClassifier()
        binding.apply {
            floatingAddImage.setOnClickListener { onAddButtonClick() }
            btnCamera.setOnClickListener { camera() }
            btnGalery.setOnClickListener { gallery() }
        }
    }

    private fun onAddButtonClick() {
        setVisibility(clicked)
        setAnimation(clicked)

        clicked = !clicked
    }

    private fun initClassifier() {
        classifier = Classifier(requireActivity().assets, mModelPath, mLabelPath, mInputSize)
    }

    private fun gallery() {
        Log.d("mssg", "button pressed")
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        startActivityForResult(intent, 250)
    }

    private fun camera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 250){
            binding.imgPhoto.setImageURI(data?.data)

            val uri : Uri?= data?.data
            val contentResolver = requireActivity().contentResolver
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            binding.imgPhoto.setImageBitmap(bitmap)
            val results = classifier.recognizeImage(bitmap)
            binding.tvResults.text = results.get(0).toString()
            binding.tvResultsStr.text = results.get(0).toString()
        }
        else if(requestCode == 200 && resultCode == Activity.RESULT_OK){
            bitmap = data?.extras?.get("data") as Bitmap
            binding.imgPhoto.setImageBitmap(bitmap)
            val results = classifier.recognizeImage(bitmap)
            binding.tvResults.text = results.get(0).toString()
            binding.tvResultsStr.text = results.get(0).toString()
        }
    }


    private fun setVisibility(clicked :Boolean) {
        if (!clicked){
            binding.apply {
                btnCamera.visibility = View.VISIBLE
                btnGalery.visibility = View.VISIBLE
            }
        }else {
            binding.apply {
                btnCamera.visibility = View.GONE
                btnGalery.visibility = View.GONE
            }
        }
    }

    private fun setAnimation(clicked :Boolean) {
        if (!clicked){
            binding.apply {
                btnCamera.startAnimation(fromBottom)
                btnGalery.startAnimation(fromBottom)
                floatingAddImage.startAnimation(rotateOpen)
            }
        } else {
            binding.apply {
                btnCamera.startAnimation(toBottom)
                btnGalery.startAnimation(toBottom)
                floatingAddImage.startAnimation(rotateClose)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}