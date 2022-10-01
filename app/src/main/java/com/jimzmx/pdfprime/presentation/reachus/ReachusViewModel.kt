package com.jimzmx.pdfprime.presentation.reachus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimzmx.pdfprime.App

class ReachusViewModel() : ViewModel(){
    private var acknowledgement : MutableLiveData<MutableList<Acknowledgement>> = MutableLiveData(App.acknowledgements)

}