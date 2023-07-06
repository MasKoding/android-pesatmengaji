package com.example.pesatmengaji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pesatmengaji.Adapter.DoaAdapter
import com.example.pesatmengaji.Adapter.SurahAdapter
import com.example.pesatmengaji.data.Doa
import com.example.pesatmengaji.data.Surah
import com.example.pesatmengaji.service.ApiClient
import com.example.pesatmengaji.service.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranActivity : AppCompatActivity() {
    lateinit var SurahList: Surah

    lateinit var rvSurah: RecyclerView
    lateinit var toolbarSurah: Toolbar
    private val api: ApiServices by lazy {
        ApiClient().getClientAsmaulHusna().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran)

        rvSurah = findViewById(R.id.rv_quran)

        toolbarSurah = findViewById(R.id.toolbar_quran)


        rvSurah!!.layoutManager = LinearLayoutManager(this)

        getListSurah()

        toolbarSurah.setNavigationOnClickListener{
            val i = Intent(this,HomeMainActivity::class.java)
            startActivity(i)
        }




    }

    private fun getListSurah() {
        api.getSurahList().enqueue(object : Callback<Surah> {
            override fun onResponse(call: Call<Surah>, response: Response<Surah>) {
                rvSurah!!.adapter = response.body()?.let { SurahAdapter(it) }
            }

            override fun onFailure(call: Call<Surah>, t: Throwable) {
                Log.d("failed", "${t.message}: ")
            }

        })
    }
}