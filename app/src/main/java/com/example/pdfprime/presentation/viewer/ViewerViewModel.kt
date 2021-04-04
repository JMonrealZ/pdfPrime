package com.example.pdfprime.presentation.viewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pdfprime.domain.usecase.DeletePdfUseCase
import com.example.pdfprime.domain.usecase.UpdateNamePdfUseCase

class ViewerViewModel(
    private val updateNamePdfUseCase: UpdateNamePdfUseCase,
    private val deletePdfUseCase: DeletePdfUseCase
) : ViewModel() {
    var pdfName : MutableLiveData<String> = MutableLiveData()

    fun setpdfName(docName : String){
        pdfName.postValue(docName)
    }
}