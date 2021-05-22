package com.jimzmx.pdfprime.presentation.di.settings

import com.jimzmx.pdfprime.presentation.settings.SettingsViewModelFactory
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