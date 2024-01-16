package com.example.m206_crud_sqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ComputerAdapter(private val context: Context, private val computers: ArrayList<Computer>) : BaseAdapter() {

    override fun getCount(): Int {
        return computers.size
    }

    override fun getItem(position: Int): Any {
        return computers[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.oneitem, parent, false)

        val computer = getItem(position) as Computer

        val imageViewComputer: ImageView = view.findViewById(R.id.imageView)
        val textViewNom: TextView = view.findViewById(R.id.textViewName)
        val textViewPrix: TextView = view.findViewById(R.id.textViewPrice)

        // Utilisation de Picasso pour charger l'image depuis l'URL
        Picasso.get().load(computer.imageURL).placeholder(R.drawable.ic_launcher_foreground).into(imageViewComputer)

        textViewNom.text = computer.nom
        textViewPrix.text = " ${computer.prix} MAD"

        return view
    }
}