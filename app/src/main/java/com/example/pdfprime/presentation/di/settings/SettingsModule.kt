package com.example.pdfprime.presentation.di.settings

import com.example.pdfprime.presentation.settings.SettingsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @SettingsScope
    @Provides
    fun provideSettingsViewModelFactory() : SettingsViewModelFactory{
        return SettingsViewModelFactory()
    }
}