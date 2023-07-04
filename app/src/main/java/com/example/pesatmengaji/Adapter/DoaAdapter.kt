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
import org.w3c.dom.Text

class DoaAdapter(private val doaList:Doa):
    RecyclerView.Adapter<DoaAdapter.ViewHolder>() {
    inner class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val teksNumber:TextView = itemView.findViewById(R.id.number_doa)
        val teksJudul:TextView = itemView.findViewById(R.id.judul_doa)
        val teksArab:TextView = itemView.findViewById(R.id.teks_doa_arab)
        val teksLatin:TextView = itemView.findViewById(R.id.teks_doa_latin)
        val teksArti:TextView = itemView.findViewById(R.id.teks_doa_arti)
        val collapse = itemView.findViewById<ImageButton>(R.id.btnCollapse)
        val doa = itemView.findViewById<CardView>(R.id.tampilDoaBtn)
        val cardDoa = itemView.findViewById<CardView>(R.id.cardDoa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_doa,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data = doaList[position]
        holder.teksNumber.text = data.id.toString()
        holder.teksLatin.text = data.latin
        holder.teksArab.text = data.arab
        holder.teksArti.text = data.terjemahan
        holder.teksJudul.text = data.doa

        var collapsibleStatus:Boolean = false

        holder.cardDoa.setOnClickListener {
           if(holder.doa.visibility == View.GONE){
               holder.collapse.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
               holder.doa.visibility = View.VISIBLE
           }else{
               holder.collapse.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
               holder.doa.visibility = View.GONE
           }

        }

        holder.collapse.setOnClickListener {
            if(holder.doa.visibility == View.GONE){
                holder.collapse.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                holder.doa.visibility = View.VISIBLE
            }else{
                holder.collapse.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                holder.doa.visibility = View.GONE
            }

        }

    }

    override fun getItemCount(): Int  = doaList.size
}