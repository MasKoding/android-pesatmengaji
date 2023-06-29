package com.example.pesatmengaji

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.SearchAutoComplete

class AsmaulHusnaActivity : AppCompatActivity() {
    lateinit var searchAsmulHusna:SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asmaul_husna)

        searchAsmulHusna = findViewById(R.id.search)

        searchAsmulHusna.setOnSearchClickListener{
            val searchPlate = searchAsmulHusna.findViewById<SearchView>(R.id.search)

        }
    }
}