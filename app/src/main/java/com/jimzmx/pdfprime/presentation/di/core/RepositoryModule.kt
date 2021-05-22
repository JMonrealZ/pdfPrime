package com.jimzmx.pdfprime.presentation.di.core

import com.jimzmx.pdfprime.data.repository.DocumentRepositoryImpl
import com.jimzmx.pdfprime.data.repository.datasource.DocumentLocalDataSource
import com.jimzmx.pdfprime.domain.repository.DocumentRepository
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