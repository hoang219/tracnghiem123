package com.hoangnn.tracnghiem123.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hoangnn.tracnghiem123.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Xử lý sự kiện khi người dùng click btnMath
        binding.btnMath.setOnClickListener {
            val intent = Intent(this, ClassActivity::class.java)
            intent.putExtra("class", "math")
            startActivity(intent)
        }

        //Xử lý sự kiện khi người dùng click btnEnglish
        //Vì chưa có dữ liệu tệp câu hỏi nên đang để thông báo không có dữ liệu
        binding.btnEnglish.setOnClickListener {
//            val intent = Intent(this, ClassActivity::class.java)
//            intent.putExtra("class", "english")
//            startActivity(intent)
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show()
        }

        //Xử lý sự kiện khi người dùng click btnVietnamese
        //Vì chưa có dữ liệu tệp câu hỏi nên đang để thông báo không có dữ liệu
        binding.btnVietnamese.setOnClickListener {
//            val intent = Intent(this, ClassActivity::class.java)
//            intent.putExtra("class", "vietnamese")
//            startActivity(intent)
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show()
        }

        //Xử lý sự kiện khi người dùng click btnHistory
        binding.btnHistory.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        //Xử lý sự kiện click vào nút feedback -> chuyển người dùng sang ứng dụng Gmail
        binding.btnFeedback.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val recipients = arrayOf("hoangnn219@gmail.com")
            intent.putExtra(Intent.EXTRA_EMAIL, recipients)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback app Trắc nghiệm 123")
            intent.type = "text/html"
            intent.setPackage("com.google.android.gm")
            startActivity(Intent.createChooser(intent, "Send mail"))
        }

        //Xử ký sự kiện click vào nút More App -> chuyển người dùng sang ứng dụng CH Play
        binding.btnMoreapp.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http:play.google.com/store")
            startActivity(Intent.createChooser(intent, "More Apps"))
        }
    }
}