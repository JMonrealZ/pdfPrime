package com.example.pdfprime.presentation.di.core

import android.provider.ContactsContract
import com.example.pdfprime.domain.repository.DocumentRepository
import com.example.pdfprime.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideDeletePdfUseCase(documentRepository: DocumentRepository) : DeletePdfUseCase{
        return DeletePdfUseCase(documentRepository)
    }

    @Provides
    fun providesGetPdfsUseCase(documentRepository: DocumentRepository) : GetPdfsUseCase{
        return GetPdfsUseCase(documentRepository)
    }

    @Provides
    fun provideGetPdfUseCase(documentRepository: DocumentRepository) : GetPdfUseCase{
        return GetPdfUseCase(documentRepository)
    }

    @Provides
    fun provideInsertPdfUseCase(documentRepository: DocumentRepository) : InsertPdfUseCase{
        return InsertPdfUseCase(documentRepository)
    }

    @Provides
    fun provideUpdateNamePdfUseCase(documentRepository: DocumentRepository) : UpdateNamePdfUseCase{
        return UpdateNamePdfUseCase(documentRepository)
    }

    @Provides
    fun deleteAllUseCase(documentRepository: DocumentRepository) : DeleteAllUseCase{
        return DeleteAllUseCase(documentRepository)
    }
}