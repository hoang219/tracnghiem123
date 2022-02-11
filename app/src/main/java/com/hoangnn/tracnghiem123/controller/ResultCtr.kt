package com.hoangnn.tracnghiem123.controller

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.hoangnn.tracnghiem123.database.Database
import com.hoangnn.tracnghiem123.models.Result
import java.lang.Exception

class ResultCtr(context: Context) {

    companion object{
        private const val TBL_RESULT = "tbl_result"
        private const val NAME = "name"
        private const val DATE = "date"
        private const val SCORE = "score"
    }

    private val database = Database(context)

    //Thêm kết quả lần tham gia vào Database
    fun insertResult(result: Result): Long{
        Log.d("Hoang", "#insertResult")
        val db = database.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(NAME, result.classname)
        contentValues.put(DATE, result.date)
        contentValues.put(SCORE, result.score)

        val success = db.insert(TBL_RESULT, null, contentValues)
        db.close()
        return success
    }

    //Lấy danh sách kết quả các lần tham gia
    @SuppressLint("Range")
    fun getListResult(): ArrayList<Result>{
        Log.d("Hoang", "#getListResult")
        val db = database.readableDatabase
        val listResult: ArrayList<Result> = ArrayList()
        val selectQuery = "SELECT * FROM ${TBL_RESULT}"

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            db.close()
            return ArrayList()
        }

        var name: String
        var date: String
        var score: Int

        if (cursor.moveToFirst()){
            do {
                name = cursor.getString(cursor.getColumnIndex(NAME))
                date = cursor.getString(cursor.getColumnIndex(DATE))
                score = cursor.getInt(cursor.getColumnIndex(SCORE))

                val result = Result(name, date, score)
                listResult.add(result)
            }while (cursor.moveToNext())
        }
        db.close()
        return listResult
    }
}