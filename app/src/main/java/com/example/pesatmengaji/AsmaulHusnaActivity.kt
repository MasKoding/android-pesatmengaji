package com.example.pesatmengaji

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.SearchAutoComplete
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pesatmengaji.Adapter.AsmaulHusnaAdapter
import com.example.pesatmengaji.data.AsmulHusna
import com.example.pesatmengaji.data.AsmulHusnaItem
import com.example.pesatmengaji.service.ApiClient
import com.example.pesatmengaji.service.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AsmaulHusnaActivity : AppCompatActivity() {
    lateinit var searchAsmulHusna:SearchView
    lateinit var recylerView:RecyclerView
    lateinit var  progressDialog: ProgressDialog
    lateinit var toolbarAsmaulHusna:Toolbar
//    lateinit var  viewGaris: View
    private val api: ApiServices by lazy {
        ApiClient().getClientAsmaulHusna().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asmaul_husna)

        searchAsmulHusna = findViewById(R.id.search)
        recylerView = findViewById(R.id.rv_asmaulhusna)
        toolbarAsmaulHusna = findViewById(R.id.toolbar_asmaulhusna)
//        viewGaris = findViewById(R.id.view_garis)
        recylerView.layoutManager =  LinearLayoutManager(this)


        progressDialog = ProgressDialog(this@AsmaulHusnaActivity)
        progressDialog.setMessage("Loading ...")
        progressDialog.setCancelable(false)

       getAsmaulHusnaList()

        toolbarAsmaulHusna.setNavigationOnClickListener{
            val i=Intent(this,HomeMainActivity::class.java)
            startActivity(i)
        }



        searchAsmulHusna.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val call = api.getAsmaulHusnaSearch(query!!)
                progressDialog.show()
                call.enqueue(object:Callback<AsmulHusna>{
                    override fun onResponse(
                        call: Call<AsmulHusna>,
                        response: Response<AsmulHusna>
                    ) {

                        progressDialog.dismiss()
                        if(query.isEmpty()){
                            getAsmaulHusnaList()
                        }
                        Log.d("response", "${query}: and ${response.body()} ")
                        val myAsmaulHusnaAdapter = response.body()?.let { AsmaulHusnaAdapter(it) }
                        recylerView.adapter = myAsmaulHusnaAdapter


                    }

                    override fun onFailure(call: Call<AsmulHusna>, t: Throwable) {
                        Log.d("gagal", "${t.message} ")
                    }

                })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })







    }

    private fun getAsmaulHusnaList() {

        val asmaulHusnaAPI = api.getAsmaulHusnaList()
        progressDialog.show()
        asmaulHusnaAPI.enqueue(object:Callback<AsmulHusna>{
            override fun onResponse(call: Call<AsmulHusna>, response: Response<AsmulHusna>) {
              progressDialog.dismiss()
                val myAsmaulHusnaAdapter = response.body()?.let { AsmaulHusnaAdapter(it) }
                recylerView.adapter = myAsmaulHusnaAdapter
            }

            override fun onFailure(call: Call<AsmulHusna>, t: Throwable) {
                Log.d("error", "${t.message}")
                progressDialog.dismiss()
            }

        })
    }
}