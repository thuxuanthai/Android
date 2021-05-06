package com.example.tx4_2a

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CountryAdapter(val context: Context,val countryList: List<CountryInfo>): BaseAdapter() {

    val layoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //Version 1 - Basic
        /*
        val rowView = layoutInflater.inflate(R.layout.row_list_item,parent,false)
        rowView.findViewById<TextView>(R.id.countryTV).text = countryList[position].name
        rowView.findViewById<TextView>(R.id.populationTV).text =
            countryList[position].population.toString() + " millions"
        rowView.findViewById<ImageView>(R.id.flagIV).setImageResource(countryList[position].imgResource)
        return rowView
         */
        //Version 2 - Optimized using ViewHolder
        val rowView: View
        val viewHolder: ViewHolder
        if(convertView == null){
            rowView = layoutInflater.inflate(R.layout.row_list_item,parent,false)
            viewHolder = ViewHolder(rowView)
            rowView.tag = viewHolder
        }
        else{
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }
        viewHolder.imageItem.setImageResource(countryList[position].imgResource)
        viewHolder.nameItem.text = countryList[position].name
        viewHolder.populationItem.text = countryList[position].population.toString() + " millions"
        return rowView

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
        val imageItem = view.findViewById<ImageView>(R.id.flagIV)
        val nameItem = view.findViewById<TextView>(R.id.countryTV)
        val populationItem = view.findViewById<TextView>(R.id.populationTV)
    }

}