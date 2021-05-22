package com.jimzmx.pdfprime.presentation.di.core

import com.jimzmx.pdfprime.data.db.DocumentsDao
import com.jimzmx.pdfprime.data.repository.datasource.DocumentLocalDataSource
import com.jimzmx.pdfprime.data.repository.datasourceImpl.DocumentLocalDataSourceImpl
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