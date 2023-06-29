package com.example.pesatmengaji

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pesatmengaji.data.Data
import com.example.pesatmengaji.data.PrayerTime
import com.example.pesatmengaji.service.ApiClient
import com.example.pesatmengaji.service.ApiServices
import kotlinx.coroutines.Runnable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.log

class HomeMainActivity :AppCompatActivity(){

    lateinit var jamTextView:TextClock
    lateinit var shubuhTime:TextView
    lateinit var dhuhurTime:TextView
    lateinit var asharTime:TextView
    lateinit var maghribTime:TextView
    lateinit var isyaTime:TextView
    lateinit var pengingat:TextView
    lateinit var waktuAdzan:String
    lateinit var btnAsmaulhusna:ImageButton

    private val api:ApiServices by lazy {
        ApiClient().getClient().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)

        jamTextView = findViewById(R.id.jamm)
        shubuhTime = findViewById(R.id.shubuh_time)
        asharTime = findViewById(R.id.ashar_time)
        dhuhurTime = findViewById(R.id.dhuhur_time)
        maghribTime = findViewById(R.id.maghrib_time)
        isyaTime = findViewById(R.id.isya_time)
        pengingat = findViewById(R.id.pengingat)

        jamTextView.format24Hour = "HH:mm"


        Log.d("jam", "${jamTextView.text}")


        val executorService = Executors.newSingleThreadScheduledExecutor()
        executorService.scheduleAtFixedRate({ getTimingPrayer() }, 0, 1, TimeUnit.SECONDS)


        onClickAsmaulHusna()


    }

    private fun onClickAsmaulHusna() {
        btnAsmaulhusna = findViewById(R.id.btn_asmaulhusna)

        btnAsmaulhusna.setOnClickListener {
            val i = Intent(this@HomeMainActivity,AsmaulHusnaActivity::class.java)

            startActivity(i)
        }
    }

    private fun getTimingPrayer() {
        Log.d("jam", "${jamTextView.text}")
        val jamSekarang = jamTextView.text

        val callPrayerTime = api.getTimingByCity()
        callPrayerTime.enqueue(object: Callback<PrayerTime>{
            override fun onResponse(call: Call<PrayerTime>, response: Response<PrayerTime>) {
                when(response.code()){
                    in 200..299->{
                        Log.d("response code", "${response.body()}")
                        response.body().let {itBody->
                            shubuhTime.text = itBody!!.data!!.timings!!.Fajr
                            dhuhurTime.text = itBody!!.data!!.timings!!.Dhuhr
                            asharTime.text = itBody!!.data!!.timings!!.Asr
                            maghribTime.text = itBody!!.data!!.timings!!.Maghrib
                            isyaTime.text = itBody!!.data!!.timings!!.Isha



                            val formatter = DateTimeFormatter.ofPattern("HH:mm")
                            val dateNow = SimpleDateFormat("HH:mm").format(Date())
                            val formatWaktuSekarang = LocalTime.parse(dateNow,formatter)
                            val nextTimeShubuhPrayer = LocalTime.parse(shubuhTime.text,formatter)
                            val nextTimeDhuhurPrayer = LocalTime.parse(dhuhurTime.text,formatter)
                            val nextTimeAsharPrayer = LocalTime.parse(asharTime.text,formatter)
                            val nextTimeMaghribPrayer = LocalTime.parse(maghribTime.text,formatter)
                            val nextTimeIsyaPrayer = LocalTime.parse(isyaTime.text,formatter)



                            var cHours = formatWaktuSekarang.until(nextTimeShubuhPrayer,ChronoUnit.HOURS)
                            var cMin = formatWaktuSekarang.until(nextTimeShubuhPrayer,ChronoUnit.MINUTES)%60
                            var hours = cHours
                            var minutes = cMin

                            if(hours<0){
                                hours+=24
                            }

                            if(minutes<0){
                                minutes+=60
                            }
                            if(formatWaktuSekarang.isBefore(nextTimeShubuhPrayer)){

                                waktuAdzan ="Isya"
                            }
                           else if(formatWaktuSekarang.isBefore(nextTimeDhuhurPrayer)){
                                waktuAdzan = "Shubuh"
                            }
                            else if(formatWaktuSekarang.isBefore(nextTimeAsharPrayer)){
                                waktuAdzan = "Dhuhur"
                            }
                            else if(formatWaktuSekarang.isBefore(nextTimeMaghribPrayer)){
                                waktuAdzan = "Ashar"
                            }
                            else if(formatWaktuSekarang.isBefore(nextTimeIsyaPrayer)){
                                waktuAdzan = "Maghrib"
                            }


                            Log.d("waktuAdzan", waktuAdzan)

                            if(waktuAdzan.equals("Dhuhur")){
                                cHours = formatWaktuSekarang.until(nextTimeAsharPrayer,ChronoUnit.HOURS)
                                cMin = formatWaktuSekarang.until(nextTimeAsharPrayer,ChronoUnit.MINUTES)%60
                                var hours = cHours
                                var minutes = cMin

                                if(hours<0){
                                    hours+=24
                                }

                                if(minutes<0){
                                    minutes+=60
                                }

                                pengingat.text = "Waktu Ashar sekitar ${hours} jam ${minutes} menit lagi"
                            }

                           else if(waktuAdzan.equals("Ashar")){
                                cHours = formatWaktuSekarang.until(nextTimeMaghribPrayer,ChronoUnit.HOURS)
                                cMin = formatWaktuSekarang.until(nextTimeMaghribPrayer,ChronoUnit.MINUTES)%60
                                var hours = cHours
                                var minutes = cMin

                                if(hours<0){
                                    hours+=24
                                }

                                if(minutes<0){
                                    minutes+=60
                                }

                                pengingat.text = "Waktu Maghrib sekitar ${hours} jam ${minutes} menit lagi"
                            }
                            else if(waktuAdzan.equals("Maghrib")){
                                cHours = formatWaktuSekarang.until(nextTimeIsyaPrayer,ChronoUnit.HOURS)
                                cMin = formatWaktuSekarang.until(nextTimeIsyaPrayer,ChronoUnit.MINUTES)%60
                                var hours = cHours
                                var minutes = cMin

                                if(hours<0){
                                    hours+=24
                                }

                                if(minutes<0){
                                    minutes+=60
                                }

                                pengingat.text = "Waktu Isya sekitar ${hours} jam ${minutes} menit lagi"
                            }
                            else if(waktuAdzan.equals("Isya")){
                                cHours = formatWaktuSekarang.until(nextTimeShubuhPrayer,ChronoUnit.HOURS)
                                cMin = formatWaktuSekarang.until(nextTimeShubuhPrayer,ChronoUnit.MINUTES)%60
                                var hours = cHours
                                var minutes = cMin

                                if(hours<0){
                                    hours+=24
                                }

                                if(minutes<0){
                                    minutes+=60
                                }

                                pengingat.text = "Waktu Shubuh sekitar ${hours} jam ${minutes} menit lagi"
                            }
                            else if(waktuAdzan.equals("Shubuh")){
                                cHours = formatWaktuSekarang.until(nextTimeDhuhurPrayer,ChronoUnit.HOURS)
                                cMin = formatWaktuSekarang.until(nextTimeDhuhurPrayer,ChronoUnit.MINUTES)%60
                                var hours = cHours
                                var minutes = cMin

                                if(hours<0){
                                    hours+=24
                                }

                                if(minutes<0){
                                    minutes+=60
                                }

                                pengingat.text = "Waktu Dhuhur sekitar ${hours} jam ${minutes} menit lagi"
                            }


                            Log.d("sekarang",formatWaktuSekarang.toString())
                            Log.d("nanti",nextTimeShubuhPrayer.toString())

                            Log.d("Selisih Jam", hours.toString())
                            Log.d("Selisih Menit", minutes.toString())



                        }
                    }
                }
            }

            override fun onFailure(call: Call<PrayerTime>, t: Throwable) {
                Log.d("error code", "${t.message}: ")
            }

        })
    }

    private fun updateClock() {
        val currentTime = Calendar.getInstance().time
        val timeFormat = SimpleDateFormat.getTimeInstance()
        val timeString = timeFormat.format(currentTime)
        jamTextView.text = timeString
    }
}