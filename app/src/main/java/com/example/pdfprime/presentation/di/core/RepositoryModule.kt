package com.example.pdfprime.presentation.di.core

import com.example.pdfprime.data.repository.DocumentRepositoryImpl
import com.example.pdfprime.data.repository.datasource.DocumentLocalDataSource
import com.example.pdfprime.domain.repository.DocumentRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDocumentsRepository(documentLocalDataSource: DocumentLocalDataSource) :
            DocumentRepository{
        return DocumentRepositoryImpl(documentLocalDataSource)
    }
}