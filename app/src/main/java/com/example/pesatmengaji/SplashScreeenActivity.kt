package com.example.pesatmengaji

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreeenActivity :AppCompatActivity(){
    lateinit var bulansabit:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.splash_screen)


        var bulansabit:ImageView = findViewById(R.id.bulan_sabit)
        bulansabit.visibility= View.VISIBLE
        val animation =AnimationUtils.loadAnimation(this,R.anim.fadein)
        bulansabit.startAnimation(animation)

        Handler().postDelayed({


            val i =Intent(this@SplashScreeenActivity,
            HomeMainActivity::class.java)


            startActivity(i)

            finish()
        },5000)


    }
}