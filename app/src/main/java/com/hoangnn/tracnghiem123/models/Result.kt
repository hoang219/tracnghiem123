package com.hoangnn.tracnghiem123.models

import java.io.Serializable
import java.util.*

data class Result(val classname: String, val date: String, val score: Int): Serializable {
    //classname: tên của môn học
    //date: thời gian lưu kết quả
    //score: điểm

    override fun toString(): String {
        when(classname){
            "math1" -> return ("Môn: Toán lớp 1 \nThời gian: $date \nĐiểm: $score")
            "math2" -> return ("Môn: Toán lớp 2 \nThời gian: $date \nĐiểm: $score")
            "math3" -> return ("Môn: Toán lớp 3 \nThời gian: $date \nĐiểm: $score")
            "english1" -> return ("Môn: Tiếng anh lớp 1 \nThời gian: $date \nĐiểm: $score")
            "english2" -> return ("Môn: Tiếng anh lớp 2 \nThời gian: $date \nĐiểm: $score")
            "english3" -> return ("Môn: Tiếng anh lớp 3 \nThời gian: $date \nĐiểm: $score")
            "vietnamese1" -> return ("Môn: Tiếng việt lớp 1 \nThời gian: $date \nĐiểm: $score")
            "vietnamese2" -> return ("Môn: Tiếng việt lớp 2 \nThời gian: $date \nĐiểm: $score")
            "vietnamese3" -> return ("Môn: Tiếng việt lớp 3 \nThời gian: $date \nĐiểm: $score")
        }
        return ("Môn: $classname \nThời gian: $date \nĐiềm: $score")
    }
}
