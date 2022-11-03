package com.jimzmx.pdfprime.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimzmx.pdfprime.domain.usecase.DeletePdfUseCase
import com.jimzmx.pdfprime.domain.usecase.UpdateNamePdfUseCase

class ViewerViewModel(
    private val updateNamePdfUseCase: UpdateNamePdfUseCase,
    private val deletePdfUseCase: DeletePdfUseCase
) : ViewModel() {
    var pdfName : MutableLiveData<String> = MutableLiveData()

    fun setpdfName(docName : String){
        pdfName.postValue(docName)
    }
}