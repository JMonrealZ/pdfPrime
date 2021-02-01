package com.example.pdfprime.presentation.di.myDocuments

import com.example.pdfprime.domain.usecase.DeletePdfUseCase
import com.example.pdfprime.domain.usecase.GetPdfsUseCase
import com.example.pdfprime.domain.usecase.InsertPdfUseCase
import com.example.pdfprime.domain.usecase.UpdateNamePdfUseCase
import com.example.pdfprime.presentation.myDocuments.MyDocumentsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MyDocumentsModule {

    @MyDocumentsScope
    @Provides
    fun provideMyDocumentsViewModelFactory(getPdfsUseCase: GetPdfsUseCase, updateNamePdfUseCase: UpdateNamePdfUseCase,
        deletePdfUseCase: DeletePdfUseCase, insertPdfUseCase: InsertPdfUseCase) : MyDocumentsViewModelFactory {
        return MyDocumentsViewModelFactory(getPdfsUseCase, updateNamePdfUseCase, deletePdfUseCase, insertPdfUseCase)
    }
}