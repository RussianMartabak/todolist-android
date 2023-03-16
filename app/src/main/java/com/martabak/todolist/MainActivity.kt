package com.martabak.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var item : EditText
    lateinit var addButton : Button
    lateinit var listView : ListView
    var fileHelper = fileHelper()

    var itemList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.inputText)
        addButton = findViewById(R.id.button)
        listView = findViewById(R.id.listView)
        itemList = fileHelper.readData(this)

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, android.R.id.text1, itemList)

        listView.adapter = arrayAdapter

        addButton.setOnClickListener {
            itemList.add(item.text.toString())
            item.setText("")
            fileHelper.writeData(itemList, applicationContext)
            arrayAdapter.notifyDataSetChanged()

        }
        listView.setOnItemClickListener { parent, view, position, id ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("You Sure Mate?")
            alert.setCancelable(false)
            // tips : use crtl + space on setpositive/neg button to add onlicklistener

            alert.setPositiveButton("Aye", DialogInterface.OnClickListener { dialog, which ->
                itemList.removeAt(position)
                fileHelper.writeData(itemList, this)
                arrayAdapter.notifyDataSetChanged()


            })
            alert.setNegativeButton("Nah", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })

            alert.create().show()


        }
    }
}