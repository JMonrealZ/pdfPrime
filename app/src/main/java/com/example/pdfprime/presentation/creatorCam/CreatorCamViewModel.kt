package com.example.pdfprime.presentation.creatorCam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pdfprime.domain.usecase.InsertPdfUseCase

class CreatorCamViewModel(
    private val insertPdfUseCase: InsertPdfUseCase
) : ViewModel(){

    var pages : MutableLiveData<List<Page>> = MutableLiveData()

    fun getObservers() : MutableLiveData<List<Page>>{
        return pages
    }


}