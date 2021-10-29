package com.cont96roller.library

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cont96roller.library.viewmodel.SearchBookViewModel
import com.cont96roller.library.adapter.SearchResultAdapter
import com.cont96roller.library.common.Constants
import com.cont96roller.library.common.Constants.INTENT_KEY_IS_FROM_REVIEW
import com.cont96roller.library.databinding.ActivitySearchBookBinding
import com.cont96roller.library.model.SearchBookModel
import com.cont96roller.library.model.SearchBookResultModel

class SearchBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBookBinding

    private var isFromReview = true
    private val title: String = "BOOK LIBRARY"
    private lateinit var viewModel: SearchBookViewModel
    private val adapter: SearchResultAdapter by lazy {

        SearchResultAdapter {
            val searchBookResultModel = it
            Toast.makeText(baseContext, "아이템 선택", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_book)
        viewModel =
            ViewModelProviders.of(this).get(SearchBookViewModel::class.java)

        setUp()
        setStatusBarColor()
        isFromReview = getIsFromReview()

        observeKakaoBookModel()

        binding.editSearch.setOnEditorActionListener { v, id, event ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                searchBook(v)

            }
            true
        }

        binding.btnSearch.setOnClickListener {
            searchBook(it)
        }
    }

    private fun setUp() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerSearchResult.adapter = adapter

    }

    private fun getBookModeIntent(model: SearchBookResultModel): Intent {
        return Intent().apply {
            var bundle = Bundle()
            bundle.putParcelable(Constants.BUNDLE_KEY_BUNDLE_SEARCH, model)
            bundle.putParcelable(Constants.BUNDLE_KEY_INTENT_SEARCH, bundle)
        }
    }

    private fun getIsFromReview(): Boolean =
        intent.getBooleanExtra(INTENT_KEY_IS_FROM_REVIEW, true)

    private fun startReview(model: SearchBookModel) {
        val intent = Intent()
        var bundle = Bundle()
        bundle.putParcelable(Constants.BUNDLE_KEY_BUNDLE_SEARCH, model)
        intent.putExtra(Constants.BUNDLE_KEY_INTENT_SEARCH, bundle)
        setResult(Activity.RESULT_OK, intent)
        finish()

    }

    private fun setStatusBarColor() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_200)

    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(binding.editSearch.windowToken, 0)

    }

    private fun checkHasResult(enable: Boolean = false, msg: String? = "") {
//        binding.txtSearchMsg.isVisible = !enable
//        binding.recyclerSearchResult.isVisible = enable
//        binding.txtSearchMsg.text = msg

    }

    fun observeKakaoBookModel() {
        viewModel.responseSearchBook.observe(this, Observer {
            it
            Toast.makeText(baseContext, "${it.bookListModel[0].title}", Toast.LENGTH_SHORT).show()
        })
    }

    fun searchBook(V: View) {
        viewModel.searchBook(
            binding.editSearch.text.toString()

        )
    }

    companion object {
        fun startSearchActivity(
            activity: Activity,
            isFromReview: Boolean
        ) {
            activity.startActivity(
                Intent(activity, SearchBookActivity::class.java).apply {
                    putExtra(INTENT_KEY_IS_FROM_REVIEW, isFromReview)
                }
            )
        }
    }
}