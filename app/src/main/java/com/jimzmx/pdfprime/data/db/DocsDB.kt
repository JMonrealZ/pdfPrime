package com.jimzmx.pdfprime.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jimzmx.pdfprime.data.entities.Document

@Database(entities = [Document::class],
    version = 1,
    exportSchema = false //Todo: What exactly this means?
)
abstract class DocsDB : RoomDatabase(){
    abstract fun documentsDao() : DocumentsDao
}