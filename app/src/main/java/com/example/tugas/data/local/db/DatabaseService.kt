package com.example.tugas.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tugas.data.local.db.dao.PesananDao
import com.example.tugas.data.local.db.entity.PesananEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        PesananEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun pesananDao(): PesananDao
}