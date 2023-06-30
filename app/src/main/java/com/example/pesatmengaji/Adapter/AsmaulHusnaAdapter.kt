package com.example.pesatmengaji.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pesatmengaji.R
import com.example.pesatmengaji.data.AsmulHusna

class AsmaulHusnaAdapter(private val asmaulHusnaList:AsmulHusna):
    RecyclerView.Adapter<AsmaulHusnaAdapter.ViewHolder>() {
    inner class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val teksArab:TextView = itemView.findViewById(R.id.teks_arab)
        val teksLatin:TextView = itemView.findViewById(R.id.teks_latin)
        val teksArti:TextView = itemView.findViewById(R.id.teks_arti)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_asmulhusna,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data = asmaulHusnaList[position]
        holder.teksLatin.text = data.latin
        holder.teksArab.text = data.arab
        holder.teksArti.text = data.terjemahan
    }

    override fun getItemCount(): Int  = asmaulHusnaList.size
}