package com.jimzmx.pdfprime.presentation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimzmx.pdfprime.presentation.viewModels.ReachusViewModel

class ReachusViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReachusViewModel() as T
    }

}