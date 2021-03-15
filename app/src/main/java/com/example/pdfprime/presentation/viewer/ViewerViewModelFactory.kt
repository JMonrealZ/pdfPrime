package com.example.pdfprime.presentation.viewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pdfprime.domain.usecase.DeletePdfUseCase
import com.example.pdfprime.domain.usecase.UpdateNamePdfUseCase

class ViewerViewModelFactory(
    private val updateNamePdfUseCase: UpdateNamePdfUseCase,
    private val deletePdfUseCase: DeletePdfUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewerViewModel(updateNamePdfUseCase,deletePdfUseCase) as T
    }

}