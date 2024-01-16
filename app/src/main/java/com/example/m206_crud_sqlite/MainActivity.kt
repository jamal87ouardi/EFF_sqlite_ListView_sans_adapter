package com.example.m206_crud_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshListView()


        val addButton = findViewById<Button>(R.id.buttonAdd)
        val editTextId = findViewById<EditText>(R.id.editTextId)
        val editTextNom = findViewById<EditText>(R.id.editTextName)
        val editTextPrix = findViewById<EditText>(R.id.editTextPrice)
        val editTextImageURL = findViewById<EditText>(R.id.editTextImageURL)


        addButton.setOnClickListener {

            val maDB = DB(applicationContext)

            val id = editTextId.text.toString().toInt()
            val nom = editTextNom.text.toString()
            val prix = editTextPrix.text.toString().toDouble()
            val imageURL = editTextImageURL.text.toString()

            val newComputer = Computer(id, nom, prix, imageURL)

            if(maDB.ajouterOrdinateur(newComputer) != -1L) {
                Toast.makeText(applicationContext,"Ordi ajouté",Toast.LENGTH_LONG).show()
                refreshListView()
            }
            else {
                Toast.makeText(applicationContext,"Ordi non ajouté",Toast.LENGTH_LONG).show()

            }
        }








    }

    fun refreshListView() {

        val maDB = DB(applicationContext)

        val myListView = findViewById<ListView>(R.id.listView)

        val listeOrdinateurs = maDB.getAll()

        val computerAdapter = ComputerAdapter(this, listeOrdinateurs)

        myListView.adapter = computerAdapter

    }
}