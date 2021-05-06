package com.example.exercise2b

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class countryRV(val context: Context, val countryS: List<countryInfo>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_rv,parent,false))
    }

    override fun getItemCount(): Int {
        return countryS.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTV.text=countryS[position].name
        holder.populationTV.text=countryS[position].population.toString()+" millions"
        holder.coutryRV.setImageResource(countryS[position].imgResource)
    }
}
class ViewHolder(view: View):RecyclerView.ViewHolder(view){
    val coutryRV = view.findViewById<ImageView>(R.id.imgI)
    val nameTV = view.findViewById<TextView>(R.id.txtName)
    val populationTV = view.findViewById<TextView>(R.id.txtML)
}