package com.example.pdfprime.presentation.myDocuments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pdfprime.domain.usecase.*

class MyDocumentsViewModelFactory(
    private val getPdfsUseCase: GetPdfsUseCase,
    private val updateNamePdfUseCase: UpdateNamePdfUseCase,
    private val deletePdfUseCase: DeletePdfUseCase,
    private val insertPdfUseCase: InsertPdfUseCase,
    private val deleteAllUseCase: DeleteAllUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyDocumentsViewModel(getPdfsUseCase, updateNamePdfUseCase, deletePdfUseCase,insertPdfUseCase,deleteAllUseCase) as T
    }
}