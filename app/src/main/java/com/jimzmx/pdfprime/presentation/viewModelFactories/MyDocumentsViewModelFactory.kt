package com.jimzmx.pdfprime.presentation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimzmx.pdfprime.domain.usecase.*
import com.jimzmx.pdfprime.presentation.viewModels.MyDocumentsViewModel

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