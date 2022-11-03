package com.jimzmx.pdfprime.presentation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jimzmx.pdfprime.domain.usecase.SetLenguageUseCase
import com.jimzmx.pdfprime.presentation.viewModels.SettingsViewModel

class SettingsViewModelFactory(
    private val setLenguageUseCase: SetLenguageUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsViewModel(setLenguageUseCase) as T
    }

}