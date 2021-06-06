package com.example.tx9

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
val DATABASE_NAME = "UserDB"
val TABLE_NAME = "User"
val COL_ID = "id"
val COL_NAME = "name"
val COL_YEAR = "birth_year"

class DatabaseHandler(context: Context)
    :SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE " + TABLE_NAME + "("+
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " VARCHAR(256), "+
                COL_YEAR + " INTEGER)";
        db?.execSQL(createTableQuery)
    }
    fun insertUser(user:User){
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME,user.name)
        contentValues.put(COL_YEAR,user.year)
        db.insert(TABLE_NAME,null,contentValues)
        db.close()
    }
    fun readData():ArrayList<User>{
        val db = readableDatabase
        val userList = ArrayList<User>()
        val query = "SELECT * FROM " + TABLE_NAME
        val resultQuery = db.rawQuery(query,null)
        if(resultQuery.moveToFirst()){
            do{
                var user = User()
                user.name = resultQuery.getString(resultQuery.getColumnIndex(COL_NAME))
                user.year = resultQuery.getInt(resultQuery.getColumnIndex(COL_YEAR))
                userList.add(user)
            }
            while(resultQuery.moveToNext())
        }
        db.close()
        return userList
    }
    fun updateData(){
        val db = writableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val resultQuery = db.rawQuery(query,null)
        if(resultQuery.moveToFirst()){
            do{
                val year = resultQuery.getInt(resultQuery.getColumnIndex(COL_YEAR))
                val id = resultQuery.getString(resultQuery.getColumnIndex(COL_ID))
                val contentValues = ContentValues()
                contentValues.put(COL_YEAR,year + 1)
                db.update(TABLE_NAME,contentValues, COL_ID + " = ?", arrayOf(id))
            }
            while(resultQuery.moveToNext())
        }
        db.close()
    }
    fun deleteData(username: String){
        val db = writableDatabase
        db.delete(TABLE_NAME, COL_NAME + "=?", arrayOf(username))
        db.close()
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}