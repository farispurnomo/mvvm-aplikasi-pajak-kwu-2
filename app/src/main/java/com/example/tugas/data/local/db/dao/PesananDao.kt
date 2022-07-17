package com.example.tugas.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tugas.data.local.db.entity.PesananEntity

@Dao
interface PesananDao {

    @Query("SELECT * FROM pesanan")
    fun getAll(): List<PesananEntity>

    @Insert
    fun insert(entity: PesananEntity)

    @Delete
    fun delete(entity: PesananEntity)
}