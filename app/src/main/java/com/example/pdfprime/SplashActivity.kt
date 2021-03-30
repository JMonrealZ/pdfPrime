package com.example.pdfprime

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.pdfprime.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.app_bar_main.view.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    private val splashTime = 500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        binding.apply {
            Glide.with(this@SplashActivity).load(Uri.parse("file:///android_asset/pdfPrime.png"))
                .into(ivSplash)
        }

        //Hinding status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController
            controller?.hide(WindowInsets.Type.statusBars())
        } else {
            //deprecated way
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        },splashTime)
    }
}