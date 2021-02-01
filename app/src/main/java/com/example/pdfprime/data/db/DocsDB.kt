package com.example.pdfprime.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pdfprime.data.model.Document

@Database(entities = [Document::class],
    version = 1,
    exportSchema = false
)
abstract class DocsDB : RoomDatabase(){
    abstract fun documentsDao() : DocumentsDao
}