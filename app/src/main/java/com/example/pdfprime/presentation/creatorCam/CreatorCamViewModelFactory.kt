package com.example.pdfprime.presentation.creatorCam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pdfprime.domain.usecase.InsertPdfUseCase

class CreatorCamViewModelFactory(
    private val insertPdfUseCase: InsertPdfUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreatorCamViewModel(insertPdfUseCase) as T
    }

}