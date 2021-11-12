package com.cont96roller.library.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ReviewDao {
    @Query("SELECT * FROM Review") // 테이블의 모든 값을 가져와라
    fun getAll(): LiveData<List<Review>>

    @Insert
    fun insert(review: Review)

    @Update
    fun update(review: Review)

    @Delete
    fun delete(review: Review)

//    @Query("SELECT * FROM User") // 테이블의 모든 값을 가져와라
//    fun getAll(): List<Review>

//    @Query("SELECT * FROM Review")
//    fun getAll(): List<Review>




}