package com.jimzmx.pdfprime.presentation.di.core

import android.content.Context
import com.jimzmx.pdfprime.domain.repository.DocumentRepository
import com.jimzmx.pdfprime.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideDeletePdfUseCase(documentRepository: DocumentRepository, context: Context) : DeletePdfUseCase{
        return DeletePdfUseCase(documentRepository, context)
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

    @Provides
    fun setLanguageUseCase() : SetLenguageUseCase {
        return SetLenguageUseCase()
    }
}