package com.cont96roller.library.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Review::class], version = 1)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao

    companion object {
        private var instance: ReviewDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ReviewDatabase? {
            if (instance == null) {
                synchronized(ReviewDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ReviewDatabase::class.java,
                        "review-database"
                    ).build()

                }
            }
            return instance
        }
    }
}