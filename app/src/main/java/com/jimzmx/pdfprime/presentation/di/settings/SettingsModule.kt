package com.jimzmx.pdfprime.presentation.di.settings

import com.jimzmx.pdfprime.domain.usecase.SetLenguageUseCase
import com.jimzmx.pdfprime.presentation.viewModelFactories.SettingsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @SettingsScope
    @Provides
    fun provideSettingsViewModelFactory(setLenguageUseCase: SetLenguageUseCase) : SettingsViewModelFactory {
        return SettingsViewModelFactory(setLenguageUseCase)
    }
}