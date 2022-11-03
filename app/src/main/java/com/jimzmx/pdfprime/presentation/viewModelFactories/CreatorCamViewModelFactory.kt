package com.jimzmx.pdfprime.presentation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimzmx.pdfprime.domain.usecase.InsertPdfUseCase
import com.jimzmx.pdfprime.presentation.viewModels.CreatorCamViewModel

class CreatorCamViewModelFactory(
    private val insertPdfUseCase: InsertPdfUseCase/*,
    private val createPdfUseCase: CreatePdfUseCase*/
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreatorCamViewModel(insertPdfUseCase/*,createPdfUseCase*/) as T
    }

}