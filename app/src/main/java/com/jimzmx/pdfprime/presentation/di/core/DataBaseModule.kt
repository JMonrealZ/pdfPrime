package com.jimzmx.pdfprime.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.jimzmx.pdfprime.data.db.DocsDB
import com.jimzmx.pdfprime.data.db.DocumentsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDocumentsDataBase(context: Context) : DocsDB{
        return Room.databaseBuilder(context,DocsDB::class.java,"DBDOCS").build()
    }

    @Singleton
    @Provides
    fun provideDocumentsDao(docsDB: DocsDB) : DocumentsDao{
        return docsDB.documentsDao()
    }
}