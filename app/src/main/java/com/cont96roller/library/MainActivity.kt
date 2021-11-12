package com.cont96roller.library

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cont96roller.library.adapter.MainResultAdapter
import com.cont96roller.library.databinding.ActivityMainBinding
import com.cont96roller.library.common.LogMsg
import com.cont96roller.library.room.Review
import com.cont96roller.library.room.ReviewDao
import com.cont96roller.library.room.ReviewDatabase
import com.cont96roller.library.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val title: String = "BOOK LIBRARY"

    private val adapter: MainResultAdapter by lazy {

        MainResultAdapter {
            val mainReviewResultModel = it


            val i = Intent(applicationContext, LibraryShowActivity::class.java)
            var isEditMode: Boolean = true

            i.putExtra("key_isEditMode", isEditMode)
            i.putExtra("key_review", it)
            startActivity(i)
//            Toast.makeText(this, "${it.title}", Toast.LENGTH_SHORT).show()
            //글씨가져오기기
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)

        /*Text 버튼에 setText하기*/
        binding.txtTitle.text = title

        /*Custom Log 사용하기*/
        LogMsg.e("", "")
        viewModel.getAll().observe(this, Observer<List<Review>> { reviews ->
        Log.e("", reviews.size.toString())
        })
        setUp()

    }

    fun moveToSearch(v: View) {

        val intent = Intent(applicationContext, SearchBookActivity::class.java)
        startActivity(intent)

    }

    fun moveToWriteWrite(v: View) {
//        Toast.makeText(baseContext, "독후감 작성 버튼 클릭", Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext, LibraryWriteActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setUp() {
        binding.bookRecyclerView.adapter = adapter
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }


//    fun list(v: View) {
//
//            val intent = Intent(application, LibraryWriteActivity::class.java)
//            startActivity(intent)
//            finish()
//    }


}

