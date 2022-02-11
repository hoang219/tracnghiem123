package com.hoangnn.tracnghiem123.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.hoangnn.tracnghiem123.databinding.ActivityClassBinding

class ClassActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityClassBinding
    var nameclass: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        nameclass = intent.getStringExtra("class").toString()
        getNameButton(nameclass)

        binding.btnClass1.setOnClickListener(this)
        binding.btnClass2.setOnClickListener(this)
        binding.btnClass3.setOnClickListener(this)
    }

    //setText cho các nút chọn lớp sau khi người dùng đang chọn môn học nào từ data của intent
    @SuppressLint("SetTextI18n")
    fun getNameButton(nameclass: String){
        when(nameclass){
            "math" -> {
                binding.btnClass1.text = "Toán lớp 1"
                binding.btnClass2.text = "Toán lớp 2"
                binding.btnClass3.text = "Toán lớp 3"
            }
            "english" -> {
                binding.btnClass1.text = "Tiếng Anh lớp 1"
                binding.btnClass2.text = "Tiếng Anh lớp 2"
                binding.btnClass3.text = "Tiếng Anh lớp 3"
            }
            "vietnamese" -> {
                binding.btnClass1.text = "Tiếng Việt lớp 1"
                binding.btnClass2.text = "Tiếng Việt lớp 2"
                binding.btnClass3.text = "Tiếng Việt lớp 3"
            }
        }
    }

    //Xử lý sự kiện người dùng click
    override fun onClick(p0: View?) {
        if (p0 != null) {
            val intent = Intent(this, MainActivity::class.java)
            when(p0.id){
                binding.btnClass1.id -> {
                    when(nameclass){
                        "math" -> {
                            goToMainActivity("math1")
                        }
                        "english" -> {
                            goToMainActivity("english1")
                        }
                        "vietnamese" -> {
                            goToMainActivity("vietnamese1")
                        }
                    }
                }
                binding.btnClass2.id -> {
                    when(nameclass){
                        "math" -> {
                            goToMainActivity("math2")
                        }
                        "english" -> {
                            goToMainActivity("english2")
                        }
                        "vietnamese" -> {
                            goToMainActivity("vietnamese2")
                        }
                    }
                }
                binding.btnClass3.id -> {
                    when(nameclass){
                        "math" -> {
                            goToMainActivity("math3")
                        }
                        "english" -> {
                            goToMainActivity("english3")
                        }
                        "vietnamese" -> {
                            goToMainActivity("vietnamese3")
                        }
                    }
                }
            }
        }
    }

    //Chuyển đến MainActivity để bắt đầu tham gia trắc nghiệm
    fun goToMainActivity(nameclass: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("classname", nameclass)
        startActivity(intent)
    }
}