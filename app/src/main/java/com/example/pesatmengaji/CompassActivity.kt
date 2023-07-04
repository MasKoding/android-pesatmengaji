package com.example.pesatmengaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.pesatmengaji.service.ApiClient
import com.example.pesatmengaji.service.ApiServices
import io.github.derysudrajat.compassqibla.CompassQibla

class CompassActivity : AppCompatActivity() {
    private var currentCompassDegree = 0f
    private var currentNeedleDegree = 0f
    lateinit var  tvLocation:TextView
    lateinit var  tvDirection:TextView
    lateinit var ivCompass:ImageView
    lateinit var ivNeedle:ImageView
    lateinit var toolbarCompass:Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compass)

        tvLocation = findViewById(R.id.tvLocation)
        tvDirection = findViewById(R.id.tvDirection)
        ivCompass = findViewById(R.id.ivCompass)
        ivNeedle = findViewById(R.id.ivNeedle)
        toolbarCompass = findViewById(R.id.toolbar_compas)

        toolbarCompass.setNavigationOnClickListener {
            val i= Intent(this,HomeMainActivity::class.java)
            startActivity(i)
        }


        CompassQibla.Builder(this).onPermissionGranted { permission ->
            Toast.makeText(this, "onPermissionGranted $permission", Toast.LENGTH_SHORT).show()
        }.onPermissionDenied {
            Toast.makeText(this, "onPermissionDenied", Toast.LENGTH_SHORT).show()
        }.onGetLocationAddress { address ->
            tvLocation.text = buildString {
                append(address.locality)
                append(", ")
                append(address.subAdminArea)
            }
        }.onDirectionChangeListener { qiblaDirection ->
            tvDirection.text = if (qiblaDirection.isFacingQibla) "You're Facing Qibla"
            else "${qiblaDirection.needleAngle.toInt()}°"

            val rotateCompass = RotateAnimation(
                currentCompassDegree, qiblaDirection.compassAngle, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 1000
            }
            currentCompassDegree = qiblaDirection.compassAngle

            ivCompass.startAnimation(rotateCompass)

            val rotateNeedle = RotateAnimation(
                currentNeedleDegree, qiblaDirection.needleAngle, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 1000
            }
            currentNeedleDegree = qiblaDirection.needleAngle

            ivNeedle.startAnimation(rotateNeedle)
        }.build()
    }
}