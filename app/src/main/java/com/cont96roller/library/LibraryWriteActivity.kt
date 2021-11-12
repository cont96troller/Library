package com.cont96roller.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.bumptech.glide.Glide
import com.cont96roller.library.common.Constants.INTENT_KEY_TEST
import com.cont96roller.library.databinding.ActivityWriteLibraryBinding
import com.cont96roller.library.model.SearchBookModel
import com.cont96roller.library.model.SearchBookResultModel
import com.cont96roller.library.room.Review
import com.cont96roller.library.room.ReviewDatabase
import com.cont96roller.library.viewmodel.LibraryWriteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LibraryWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteLibraryBinding
    private lateinit var viewModel: LibraryWriteViewModel
//    private lateinit var newReview: Review


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_write_library)

        var mIsEditMode :Boolean= intent.getBooleanExtra("key_isEditModel", false)
        if (mIsEditMode == true) {

            var mReview: Review? = intent.getParcelableExtra<Review>("key_review")
            var title = mReview?.title

        }






//            var review = intent.getParcelableExtra<SearchBookModel?>(INTENT_KEY_TEST)
//            review?.let {
//                Glide.with(baseContext).asBitmap().load(it.thumbnailUrl)
//                    .into(binding.imgBook)
//            }
//        }


//        val bundle = intent.getBundleExtra("key_bundle")
//        bundle?.getParcelable<SearchBookModel>(INTENT_KEY_TEST)?.let {
//            Glide.with(baseContext).asBitmap().load(it.thumbnailUrl)
//                .into(binding.imgBook)
//        }
    }
//        Glide.with(this).asBitmap().load(review.thumbnail)
//            .into(img)

//        Glide.with(this).asBitmap().load(review.[])
//            .into(binding.imgBook.)

//        Glide.with(root.context).asBitmap().load(review.thumbnail)
//            .into(imgThumbnail)

    fun write(v: View) {

        var newReview = Review("${binding.editDiaryTitle.text}", "${binding.editDiaryContents.text}")
//        newReview.title = binding.editDiaryTitle.text.toString()
//        newReview.book = binding.editDiaryContents.text.toString()

        val db = Room.databaseBuilder(
            applicationContext,
           ReviewDatabase::class.java,
            "review-database"
        ).allowMainThreadQueries()
            .build()
        db.reviewDao().insert(newReview)

//        val db = ReviewDatabase.getInstance(applicationContext)
//            db!!.reviewDao().insert(newReview)



        val intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun back(v: View) {

        val intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
