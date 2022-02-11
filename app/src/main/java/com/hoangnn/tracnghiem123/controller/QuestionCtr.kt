package com.hoangnn.tracnghiem123.controller

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import com.hoangnn.tracnghiem123.database.Database
import com.hoangnn.tracnghiem123.models.Question
import java.lang.Exception
import kotlin.collections.ArrayList

class QuestionCtr(context: Context) {

    companion object{
        private const val TBL_QUESTION = "tbl_question"
        private const val QUESTION = "question"
        private const val ANSWER1 = "answer1"
        private const val ANSWER2 = "answer2"
        private const val ANSWER3 = "answer3"
        private const val ANSWER4 = "answer4"
        private const val CORRECT = "correct"
        private const val NAME = "name"
    }

    private val database = Database(context)

    //Lấy ngẫu nhiên 5 câu hỏi trong danh sách câu hỏi trong Database theo lớp được yêu cầu
    @SuppressLint("Range")
    fun getListQuestionInDB(classname: String): ArrayList<Question>{
        val listQuestion: ArrayList<Question> = ArrayList()
        val db = database.readableDatabase
        val selection = "$NAME = ? "
        val selectionArgs: Array<String> = arrayOf(classname)
        val cursor: Cursor?
        try {
            cursor = db.query(TBL_QUESTION, null, selection, selectionArgs, null, null, "RANDOM()", "5")
        }catch (e: Exception){
            e.printStackTrace()
            return ArrayList()
        }

        var content: String
        var answer1: String
        var answer2: String
        var answer3: String
        var answer4: String
        var correct: Int

        if (cursor.moveToFirst()){
            do {
                content = cursor.getString(cursor.getColumnIndex(QUESTION))
                answer1 = cursor.getString(cursor.getColumnIndex(ANSWER1))
                answer2 = cursor.getString(cursor.getColumnIndex(ANSWER2))
                answer3 = cursor.getString(cursor.getColumnIndex(ANSWER3))
                answer4 = cursor.getString(cursor.getColumnIndex(ANSWER4))
                correct = cursor.getInt(cursor.getColumnIndex(CORRECT))

                val question = Question(content, answer1, answer2, answer3, answer4, correct)
                listQuestion.add(question)
            }while (cursor.moveToNext())
        }
        db.close()
        return listQuestion
    }
}