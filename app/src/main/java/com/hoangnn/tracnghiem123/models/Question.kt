package com.hoangnn.tracnghiem123.models

import java.io.Serializable

data class Question(val content: String, val answer1: String, val answer2: String, val answer3: String, val answer4: String, val correct: Int): Serializable
//content: Nội dung câu hỏi
//answer1, answer2, answer3, answer4: Nội dung của các câu trả lời
//correct: câu trả lời đúng
