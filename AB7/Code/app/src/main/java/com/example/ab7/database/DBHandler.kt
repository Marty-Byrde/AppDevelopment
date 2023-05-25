package com.example.ab7.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DBHandler(context: Context) : SQLiteOpenHelper(context, "rateStorage", null, 1) {
    object DBEntry : BaseColumns {
        const val table_name = "rateStorage"
        const val dateColumnTitle = "date"
        const val jsonColumnTitle = "json"

    }

    private val SQL_CreateTable = "CREATE TABLE ${DBEntry.table_name} (${BaseColumns._ID} INTEGER PRIMARY KEY, ${DBEntry.dateColumnTitle} TEXT, ${DBEntry.jsonColumnTitle} TEXT)"
    private val SQL_DropTable = "DROP TABLE IF EXISTS ${DBEntry.table_name}"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CreateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DropTable)
        onCreate(db)
    }


}