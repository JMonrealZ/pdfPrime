package com.jimzmx.pdfprime.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.presentation.entities.Acknowledgement

class ReachusViewModel() : ViewModel(){
    private var acknowledgement : MutableLiveData<MutableList<Acknowledgement>> = MutableLiveData(App.acknowledgements)

}