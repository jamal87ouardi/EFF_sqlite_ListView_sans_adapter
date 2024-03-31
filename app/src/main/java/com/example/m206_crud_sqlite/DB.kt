package com.example.m206_crud_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(context: Context) : SQLiteOpenHelper(context, "db_computers", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Créer la table lors de la création de la base de données
        db.execSQL("CREATE TABLE computers (id INTEGER PRIMARY KEY, nom TEXT, prix REAL, imageURL TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Mettre à jour la table en cas de changement de version
        db.execSQL("DROP TABLE IF EXISTS computers")
        onCreate(db)
    }

    fun ajouterOrdinateur(ordinateur: Computer):Long {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put("id", ordinateur.id)
        values.put("nom", ordinateur.nom)
        values.put("prix", ordinateur.prix)
        values.put("imageURL", ordinateur.imageURL)

        val inserted = db.insert("computers", null, values)
        db.close()
        return  inserted
    }

    fun getAll(): ArrayList<Computer> {
        val computersList = ArrayList<Computer>()
        val db = readableDatabase

        val selectQuery = "SELECT * FROM computers"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nom = cursor.getString(1)
                val prix = cursor.getDouble(2)
                val imageURL = cursor.getString(3)

                val ordinateur = Computer(id, nom, prix, imageURL)
                computersList.add(ordinateur)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return computersList
    }

    fun searchById(id: Int): Computer? {
        val db = this.readableDatabase
        val cursor: Cursor?
        var computer : Computer? = null
        try {
            val query =
                "SELECT nom, prix, imageURL FROM computers WHERE id = ?"
            cursor = db.rawQuery(query, arrayOf(id.toString()))
            cursor.moveToFirst()

            if (cursor.count > 0) {
                val name = cursor.getString(cursor.getColumnIndex("nom"))
                val price = cursor.getDouble(cursor.getColumnIndex("prix"))
                val imageURL = cursor.getString(cursor.getColumnIndex("imageURL"))

                computer = Computer(id,name, price, imageURL)
            }
        } catch (e: Exception) {

        }
        db.close()
        return computer

    }


}

