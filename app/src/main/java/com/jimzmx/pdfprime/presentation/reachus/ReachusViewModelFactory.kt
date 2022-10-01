package com.jimzmx.pdfprime.presentation.reachus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ReachusViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReachusViewModel() as T
    }

}