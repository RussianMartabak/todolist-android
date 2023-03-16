package com.martabak.todolist

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

//inherit array adapter and customize it (passing context and item from CLV class to base class, while
//hard code the argument of layout to the base class)
class CustomListView(private val context: Activity, private val item:ArrayList<String>) : ArrayAdapter<String>(
    context, R.layout.custom_listview, item){
    // override getView method from base class (ArrayAdapter)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_listview, null, true)

        val itemText = rowView.findViewById(R.id.itemText) as TextView
        itemText.text = item[position]
        return rowView
    }
}
