package com.cont96roller.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cont96roller.library.databinding.ActivityShowLibraryBinding

class LibraryShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_library)

//        var reviewId: Int = intent.getIntExtra()



    }
}