package com.hoangnn.tracnghiem123.view

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hoangnn.tracnghiem123.controller.ResultCtr
import com.hoangnn.tracnghiem123.databinding.ActivityHistoryBinding

class HistoryActivity: AppCompatActivity() {

    lateinit var binding: ActivityHistoryBinding
    lateinit var resultCtr: ResultCtr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        resultCtr = ResultCtr(this)

        getListResult()
    }

    private fun getListResult(){
        val listResult = resultCtr.getListResult()
        val listString: ArrayList<String> = ArrayList()
        for(result in listResult){
            listString.add(result.toString())
        }
        Log.d("Hoang", "HistorySize = ${listResult.size}")
        binding.lvHistory.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listString)
    }
}