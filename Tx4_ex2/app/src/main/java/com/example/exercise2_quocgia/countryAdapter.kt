package com.example.exercise2_quocgia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class countryAdapter(val context: Context, val countryList: List<countryInfo>): BaseAdapter() {
    val layoutInterface=LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowV: View
        val viewHolder: ViewHolder
        if(convertView==null){
            rowV=layoutInterface.inflate(R.layout.row, parent, false)
            viewHolder=ViewHolder(rowV)
            rowV.tag=viewHolder
        }else{
            rowV=convertView
            viewHolder=rowV.tag as ViewHolder
        }
        viewHolder.imageItem.setImageResource(countryList[position].imgR)
        viewHolder.nameItem.text=countryList[position].name
        viewHolder.populationItem.text=countryList[position].population.toString()+" millons"
        return rowV
    }

    override fun getItem(position: Int): Any {
        return countryList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return countryList.count()
    }
    private class ViewHolder(val view: View){
        val imageItem = view.findViewById<ImageView>(R.id.countryLV)
        val nameItem = view.findViewById<TextView>(R.id.txtName)
        val populationItem = view.findViewById<TextView>(R.id.txtMillion)
    }
}