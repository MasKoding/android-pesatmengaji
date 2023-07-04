package com.example.pesatmengaji

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pesatmengaji.Adapter.DoaAdapter
import com.example.pesatmengaji.data.Doa
import com.example.pesatmengaji.data.DoaItem
import com.example.pesatmengaji.service.ApiClient
import com.example.pesatmengaji.service.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaActivity : AppCompatActivity() {


    lateinit var doaList:Doa
    lateinit var tampilDoa:CardView

    lateinit var rvDoa:RecyclerView
    lateinit var toolbarDoa:Toolbar

    private val api: ApiServices by lazy {
        ApiClient().getClientAsmaulHusna().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doa)

        rvDoa = findViewById(R.id.rv_doa)

        toolbarDoa = findViewById(R.id.toolbar_doa)


       rvDoa!!.layoutManager = LinearLayoutManager(this)


         getDoaList()



        toolbarDoa.setNavigationOnClickListener{
            val i = Intent(this,HomeMainActivity::class.java)
            startActivity(i)
        }




    }

    private fun getDoaList() {
        api.getDoaList().enqueue(object :Callback<Doa>{
            override fun onResponse(call: Call<Doa>, response: Response<Doa>) {
                rvDoa!!.adapter = response.body()?.let { DoaAdapter(it) }
            }

            override fun onFailure(call: Call<Doa>, t: Throwable) {
                Log.d("failed", "${t.message}: ")
            }

        })
    }
}