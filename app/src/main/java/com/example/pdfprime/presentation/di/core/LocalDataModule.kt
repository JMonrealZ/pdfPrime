package com.example.pdfprime.presentation.di.core

import androidx.transition.Visibility
import com.example.pdfprime.data.db.DocumentsDao
import com.example.pdfprime.data.repository.datasource.DocumentLocalDataSource
import com.example.pdfprime.data.repository.datasourceImpl.DocumentLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideDocumentLocalDataSource(documentsDao: DocumentsDao) : DocumentLocalDataSource{
        return DocumentLocalDataSourceImpl(documentsDao)
    }
}