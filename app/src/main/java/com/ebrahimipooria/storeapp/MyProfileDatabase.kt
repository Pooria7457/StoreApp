package com.ebrahimipooria.storeapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyProfileDatabase(private val context: Context) :
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
        name: String?,
        userName: String?,
        email: String?,
        phone: String?
    ): Long {
        val contentValues = ContentValues()
        contentValues.put(COL_Name, name)
        contentValues.put(COL_UserName, userName)
        contentValues.put(COL_Email, email)
        contentValues.put(COL_Phone, phone)
        val sqLiteDatabase = this.writableDatabase
        return sqLiteDatabase.insert(TBL_NAME, null, contentValues)
    }

    val getInfos: Cursor
        get() {
            val sqLiteDatabase = this.readableDatabase
            return sqLiteDatabase.rawQuery("SELECT * FROM $TBL_NAME", null)
        }

    companion object {
        private const val DB_NAME = "store"
        private const val DB_VERSION = 1
        private const val TBL_NAME = "profile"
        private const val COL_ID = "id"
        private const val COL_Name = "name"
        private const val COL_UserName = "userName"
        private const val COL_Email = "email"
        private const val COL_Phone = "phone"
        private const val QUERY = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COL_Name + " TEXT , " +
                COL_UserName + " TEXT , " +
                COL_Email + " TEXT , " +
                COL_Phone + " TEXT ); "
    }
}