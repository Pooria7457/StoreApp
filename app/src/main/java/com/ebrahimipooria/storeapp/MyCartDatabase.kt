package com.ebrahimipooria.storeapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyCartDatabase(private val context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(QUERY)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}
    fun addInfo(
        myId: Int?,
    ): Long {
        val contentValues = ContentValues()
        contentValues.put(COL_MyId, myId)
        val sqLiteDatabase = this.writableDatabase
        return sqLiteDatabase.insert(TBL_NAME, null, contentValues)
    }

    val getInfos: Cursor
        get() {
            val sqLiteDatabase = this.readableDatabase
            return sqLiteDatabase.rawQuery("SELECT * FROM $TBL_NAME", null)
        }

    companion object {
        private const val DB_NAME = "StoreCart"
        private const val DB_VERSION = 1
        private const val TBL_NAME = "cart"
        private const val COL_ID = "id"
        private const val COL_MyId = "myId"
        private const val QUERY = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COL_MyId + " INTEGER ); "
    }
}