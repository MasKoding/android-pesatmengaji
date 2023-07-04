package com.example.pesatmengaji.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pesatmengaji.R
import com.example.pesatmengaji.data.AsmulHusna
import com.example.pesatmengaji.data.Doa
import com.example.pesatmengaji.data.Surah
import org.w3c.dom.Text

class SurahAdapter(private val surahList:Surah):
    RecyclerView.Adapter<SurahAdapter.ViewHolder>() {
    inner class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val teksNumber:TextView = itemView.findViewById(R.id.number_surah)
        val teksJudul:TextView = itemView.findViewById(R.id.judul_surah)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_surah,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data = surahList[position]
        holder.teksNumber.text = data.id.toString()
        holder.teksJudul.text = data.surah


    }

    override fun getItemCount(): Int  = surahList.size
}