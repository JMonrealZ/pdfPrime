package com.jimzmx.pdfprime.presentation.di.myDocuments

import com.jimzmx.pdfprime.domain.usecase.*
import com.jimzmx.pdfprime.presentation.myDocuments.MyDocumentsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MyDocumentsModule {

    @MyDocumentsScope
    @Provides
    fun provideMyDocumentsViewModelFactory(getPdfsUseCase: GetPdfsUseCase, updateNamePdfUseCase: UpdateNamePdfUseCase,
        deletePdfUseCase: DeletePdfUseCase, insertPdfUseCase: InsertPdfUseCase, deleteAllUseCase: DeleteAllUseCase) : MyDocumentsViewModelFactory {
        return MyDocumentsViewModelFactory(getPdfsUseCase, updateNamePdfUseCase, deletePdfUseCase, insertPdfUseCase,deleteAllUseCase)
    }
}