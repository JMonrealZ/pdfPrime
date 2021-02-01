package com.example.pdfprime.presentation.myDocuments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pdfprime.domain.usecase.DeletePdfUseCase
import com.example.pdfprime.domain.usecase.GetPdfsUseCase
import com.example.pdfprime.domain.usecase.InsertPdfUseCase
import com.example.pdfprime.domain.usecase.UpdateNamePdfUseCase

class MyDocumentsViewModelFactory(
    private val getPdfsUseCase: GetPdfsUseCase,
    private val updateNamePdfUseCase: UpdateNamePdfUseCase,
    private val deletePdfUseCase: DeletePdfUseCase,
    private val insertPdfUseCase: InsertPdfUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyDocumentsViewModel(getPdfsUseCase, updateNamePdfUseCase, deletePdfUseCase,insertPdfUseCase) as T
    }
}