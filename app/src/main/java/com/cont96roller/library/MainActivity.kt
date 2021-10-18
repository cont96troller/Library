package com.cont96roller.library

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cont96roller.library.databinding.ActivityMainBinding
import com.cont96roller.library.viewmodel.LogMsg
import com.cont96roller.library.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val title: String = "BOOK LIBRARY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)

        /*Text 버튼에 setText하기*/
        binding.txtTitle.text = title

        /*Custom Log 사용하기*/
        LogMsg.e("", "")


        observeKakaoBookModel()
        viewModel.getSearchBookResult()


    }

    fun observeKakaoBookModel() {
        viewModel.kakaoBookModel.observe(this, Observer {
            it
            Toast.makeText(baseContext, "${it.documents[0].title}", Toast.LENGTH_SHORT).show()
        })
    }

    fun moveToSearch(v: View) {
        Toast.makeText(baseContext, "찾기 버튼 클릭", Toast.LENGTH_SHORT).show()
    }

    fun moveToWriteWrite(v: View) {
        Toast.makeText(baseContext, "독후감 작성 버튼 클릭", Toast.LENGTH_SHORT).show()
    }

}