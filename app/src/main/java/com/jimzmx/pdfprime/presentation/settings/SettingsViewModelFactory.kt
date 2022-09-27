package com.jimzmx.pdfprime.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimzmx.pdfprime.domain.usecase.GetLenguageUseCase
import com.jimzmx.pdfprime.domain.usecase.SetLenguageUseCase

class SettingsViewModelFactory(
    private val setLenguageUseCase: SetLenguageUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsViewModel(setLenguageUseCase) as T
    }

}