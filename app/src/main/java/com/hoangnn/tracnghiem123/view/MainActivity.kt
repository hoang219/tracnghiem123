package com.hoangnn.tracnghiem123.view

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hoangnn.tracnghiem123.R
import com.hoangnn.tracnghiem123.controller.QuestionCtr
import com.hoangnn.tracnghiem123.controller.ResultCtr
import com.hoangnn.tracnghiem123.databinding.ActivityMainBinding
import com.hoangnn.tracnghiem123.models.Question
import com.hoangnn.tracnghiem123.models.Result
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var listQuestion: ArrayList<Question> // Danh sách câu hỏi
    private lateinit var mQuestion: Question // câu hỏi hiện tại
    private var currentQuestion: Int = 0 // số câu hiện tại
    private var score: Int = 0 // điểm hiện tại
    private lateinit var questionCtr: QuestionCtr
    private lateinit var resultCtr: ResultCtr
    private var classname = "" // tên lớp môn học đang tham gia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        questionCtr = QuestionCtr(this)
        resultCtr = ResultCtr(this)
        classname = intent.getStringExtra("classname").toString()
        getListQuestion()
        setQuestion()
    }

    // lấy ra danh sách câu hỏi từ QuestionCtr
    fun getListQuestion(){
        listQuestion = questionCtr.getListQuestionInDB(classname)
        score = 0
        currentQuestion = 0
    }

    //Tạo sự kiện click cho các TextView
    private fun setClick(){
        binding.tvAnswer1.setOnClickListener(this)
        binding.tvAnswer2.setOnClickListener(this)
        binding.tvAnswer3.setOnClickListener(this)
        binding.tvAnswer4.setOnClickListener(this)
    }

    //Xử lí sự kiện người dùng click
    override fun onClick(p0: View?) {
        if (p0 != null) {
            when(p0.id){
                binding.tvAnswer1.id -> {
                    binding.tvAnswer1.setBackgroundResource(R.drawable.bg_orange_30dp)
                    checkAnswer(binding.tvAnswer1, 1)
                    Log.d("Hoang", "#chooseAnswer1")
                }
                binding.tvAnswer2.id -> {
                    binding.tvAnswer2.setBackgroundResource(R.drawable.bg_orange_30dp)
                    checkAnswer(binding.tvAnswer2, 2)
                    Log.d("Hoang", "#chooseAnswer2")
                }
                binding.tvAnswer3.id -> {
                    binding.tvAnswer3.setBackgroundResource(R.drawable.bg_orange_30dp)
                    checkAnswer(binding.tvAnswer3, 3)
                    Log.d("Hoang", "#chooseAnswer3")
                }
                binding.tvAnswer4.id -> {
                    binding.tvAnswer4.setBackgroundResource(R.drawable.bg_orange_30dp)
                    checkAnswer(binding.tvAnswer4, 4)
                    Log.d("Hoang", "#chooseAnswer4")
                }
            }
        }
    }

    //Kiểm tra câu hỏi người dùng đã chọn có đúng hay không
    //Đúng thì chuyển sang câu hỏi tiếp theo nextQuestion()
    //Sai thì hiển thị câu trả lời đúng showAnswer() rồi chuyển câu hỏi tiếp theo
    private fun checkAnswer(textView: TextView, answer: Int){
        val runnable = Runnable {
            kotlin.run {
                if (mQuestion.correct == answer){
                    textView.setBackgroundResource(R.drawable.bg_green_30dp)
                    score++
                    nextQuestion()
                }else{
                    textView.setBackgroundResource(R.drawable.bg_red_30dp)
                    showAnswer();
                    nextQuestion()
                }
            }
        }
        Handler().postDelayed(runnable, 1000)
        Log.d("Hoang", "#checkAnswer")
    }

    //Chuyển sang câu hỏi tiếp theo
    //Kiểm tra xem đã hết câu hỏi hay chưa
    //Nếu đã hết gọi hàm showDialog() hiển thị Dialog thông báo kết quả cho người chơi
    //Chưa hết thì lấy câu hỏi mới và gọi hàm setQuestion()
    private fun nextQuestion(){
        Log.d("Hoang", "#nextQuestion")
        if(currentQuestion >= listQuestion.size - 1 || currentQuestion >= 4){
            showDialog()
        }else{
            currentQuestion++;
            val runnable = Runnable {
                kotlin.run {
                    setQuestion()
                }
            }
            Handler().postDelayed(runnable, 1000)
        }
    }

    //set dữ liệu câu hỏi hiện tại vào các view
    private fun setQuestion(){
        if(listQuestion == null) return

        mQuestion = listQuestion[currentQuestion]
        binding.tvAnswer1.setBackgroundResource(R.drawable.bg_blue_30dp)
        binding.tvAnswer2.setBackgroundResource(R.drawable.bg_blue_30dp)
        binding.tvAnswer3.setBackgroundResource(R.drawable.bg_blue_30dp)
        binding.tvAnswer4.setBackgroundResource(R.drawable.bg_blue_30dp)

        binding.tvQuestion.text = "Câu số: ${currentQuestion + 1}"
        binding.tvContentQuestion.text = mQuestion.content
        binding.tvAnswer1.text = mQuestion.answer1
        binding.tvAnswer2.text = mQuestion.answer2
        binding.tvAnswer3.text = mQuestion.answer3
        binding.tvAnswer4.text = mQuestion.answer4

        setClick()

        Log.d("Hoang", "#setQuestion")
    }

    //Kiểm tra câu trả lời đúng và hiển thị cho người dùng
    private fun showAnswer(){
        Log.d("Hoang", "#showAnswer")
        if(mQuestion == null){
            return
        }
        when(mQuestion.correct){
            1 -> binding.tvAnswer1.setBackgroundResource(R.drawable.bg_green_30dp)
            2 -> binding.tvAnswer2.setBackgroundResource(R.drawable.bg_green_30dp)
            3 -> binding.tvAnswer3.setBackgroundResource(R.drawable.bg_green_30dp)
            4 -> binding.tvAnswer4.setBackgroundResource(R.drawable.bg_green_30dp)
        }
    }

    //Hiển thị Dialog thông báo kết quả và hỏi người dùng có tiếp tục tham gia hay không
    //Tiếp tục làm bài thì sẽ lưu kết quả trước đó và lấy danh sách câu hỏi mới
    //Không tham nữa thì sẽ lưu kết quả và kết thúc Activity
    private fun showDialog(){
        Log.d("Hoang", "#showDialog")
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Trắc nghiệm kết thúc")
        alertDialog.setMessage("Điểm của bạn: $score/${currentQuestion+1}")

        alertDialog.setPositiveButton("Thoát", { dialogInterface: DialogInterface, i: Int ->
            saveResult()
            finish()
        })

        alertDialog.setNegativeButton("Chơi tiếp", { dialogInterface: DialogInterface, i: Int ->
            saveResult()
            getListQuestion()
            setQuestion()
        })

        alertDialog.show()
    }

    //Lưu kết quả tham gia vào Database
    private fun saveResult(){
        val dateFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
        val sDate = dateFormat.format(Date())
        val result = Result(classname, sDate, score)
        if (resultCtr.insertResult(result) > -1){
            Toast.makeText(this, "Đã lưu kết quả", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Không thể lưu kết quả", Toast.LENGTH_LONG).show()
        }
        Log.d("Hoang", result.toString())
    }
}
