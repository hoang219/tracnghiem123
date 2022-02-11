package com.hoangnn.tracnghiem123.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.hoangnn.tracnghiem123.models.Question

class Database(context: Context): SQLiteOpenHelper(context, DATEBASE_NAME, null, DATABASE_VERSION) {

    lateinit var db: SQLiteDatabase
    var tong = 0
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATEBASE_NAME = "tracnghiem.db"
        private const val TBL_QUESTION = "tbl_question"
        private const val TBL_RESULT = "tbl_result"
        private const val ID = "id"
        private const val QUESTION = "question"
        private const val ANSWER1 = "answer1"
        private const val ANSWER2 = "answer2"
        private const val ANSWER3 = "answer3"
        private const val ANSWER4 = "answer4"
        private const val CORRECT = "correct"
        private const val NAME = "name"
        private const val DATE = "date"
        private const val SCORE = "score"
    }

    //Tạo bảng tbl_question để lưu danh sách câu hỏi
    //Tạo bảng tbl_result để lưu danh sách kết quả các lần tham gia
    override fun onCreate(db: SQLiteDatabase?) {

        if (db != null) {
            this.db = db
        }

        val createTblQuestion = ("CREATE TABLE " + TBL_QUESTION + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QUESTION + " TEXT, "
                + ANSWER1 + " TEXT, "
                + ANSWER2 + " TEXT, "
                + ANSWER3 + " TEXT, "
                + ANSWER4 + " TEXT, "
                + CORRECT + " INTEGER, "
                + NAME + " TEXT" + ")"
                )

        val createTblResult = ("CREATE TABLE " + TBL_RESULT + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + DATE + " TEXT, "
                + SCORE + " INTEGER" + ")"
                )

        //tạo bảng
        db?.execSQL(createTblQuestion)
        db?.execSQL(createTblResult)

        //thêm dữ liệu câu hỏi vào database
        createQuestion()
        Log.d("Hoang", "So cau da them: $tong")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_QUESTION")
        db.execSQL("DROP TABLE IF EXISTS $TBL_RESULT")
        onCreate(db)
    }

    //Thêm câu hỏi vào Database
    private fun insertQuestion(question: Question, classname: String){
        Log.d("Hoang", "#insertQuestion")
        val contentValues = ContentValues()
        contentValues.put(QUESTION, question.content)
        contentValues.put(ANSWER1, question.answer1)
        contentValues.put(ANSWER2, question.answer2)
        contentValues.put(ANSWER3, question.answer3)
        contentValues.put(ANSWER4, question.answer4)
        contentValues.put(CORRECT, question.correct)
        contentValues.put(NAME, classname)
        db.insert(TBL_QUESTION, null, contentValues)
        tong++
    }

    //tạo danh sách các câu hỏi
    private fun createQuestion(){
        Log.d("Hoang", "#createQuestion")
        //Danh sách câu hỏi toán 1
        insertQuestion(Question("Kết quả của 3+4 là",
            "5",
            "7",
            "8",
            "9",
            2), "math1")
        insertQuestion(Question("Số cần điền vào ...-2 = 3 là",
            "1",
            "3",
            "5",
            "7",
            3), "math1")
        insertQuestion(Question("Sắp xếp các số: 0, 5, 2, 10 theo thứ tự từ bé đến lớn",
            "10, 5, 2, 0",
            "2, 0, 10, 5",
            "0, 2, 5, 10",
            "5, 10, 2, 0",
            3),
            "math1")
        insertQuestion(Question("Dấu cần điền vào 8+2...9–2 là",
            ">",
            "<",
            "=",
            "x",
            1),
            "math1")
        insertQuestion(Question("Chọn phép tính đúng",
            "10-5 = 6",
            "4+5 = 9",
            "9-6 =2",
            "1+1 = 3",
            2),
            "math1")
        insertQuestion(Question("Số lớn nhất có một chữ số là",
            "8",
            "7",
            "10",
            "9",
            4),
            "math1")
        insertQuestion(Question("Số bé nhất trong các số",
            "1",
            "5",
            "7",
            "9",
            1),
            "math1")

        //Danh sách câu hỏi toán 2
        insertQuestion(Question("Kết quả của phép tính 67+26 là",
            "83",
            "93",
            "94",
            "95",
            2),
            "math2")
        insertQuestion(Question("Kết quả của phép tính 100-57 là",
            "53",
            "44",
            "43",
            "33",
            3),
            "math2")
        insertQuestion(Question("Tổng nào bé hơn 56",
            "50+8",
            "49+7",
            "36+29",
            "48+6",
            4),
            "math2")
        insertQuestion(Question("Kết quả của phép tính 86-6-9 là",
            "71",
            "70",
            "81",
            "61",
            1),
            "math2")
        insertQuestion(Question("Hiệu của 73 và 37",
            "36",
            "37",
            "35",
            "47",
            1),
            "math2")

        //Danh sách câu hỏi toán 3
        insertQuestion(Question("Dãy số dưới có bao nhiêu số: \n 8; 1998; 195; 2007; 1000; 71; 768; 9999; 17",
            "11",
            "9",
            "8",
            "10",
            2),
            "math3")
        insertQuestion(Question("Tổng của 47856 và 35678 là",
            "83433",
            "82443",
            "83543",
            "82543",
            3),
            "math3")
        insertQuestion(Question("Trong các số dưới đây, số nào không thuộc dãy số: 1, 4, 7, 10, 13, ...",
            "1000",
            "1234",
            "2007",
            "100",
            3),
            "math3")
        insertQuestion(Question("Mai có 7 viên bi, Hồng có 15 viên bi. Hỏi Hồng phải cho Mai bao nhiêu viên bi để số bi của hai bạn bằng nhau.",
            "3",
            "5",
            "6",
            "4",
            4),
            "math3")
        insertQuestion(Question("9m 4cm = ...cm",
            "94 cm",
            "940 cm",
            "904 cm",
            "9004 cm",
            3),
            "math3")
    }
}