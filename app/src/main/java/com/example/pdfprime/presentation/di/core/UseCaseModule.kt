package com.example.pdfprime.presentation.di.core

import android.provider.ContactsContract
import com.example.pdfprime.domain.repository.DocumentRepository
import com.example.pdfprime.domain.usecase.*
import com.example.pdfprime.presentation.creatorCam.Page
import com.example.pdfprime.presentation.utils.ProgressUpdater
import com.tom_roush.pdfbox.pdmodel.PDDocument
import dagger.Module
import dagger.Provides
import java.io.File

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

//    @Provides
//    fun createPdfUseCase(direc : File, oldDocName : String, list : MutableList<Page>, progressUpdater: ProgressUpdater) : CreatePdfUseCase {
//        return CreatePdfUseCase(direc,oldDocName,list,progressUpdater)
//    }
}