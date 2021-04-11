package com.example.pdfprime.presentation.di.settings

import com.example.pdfprime.presentation.settings.SettingsFrag
import dagger.Subcomponent

@SettingsScope
@Subcomponent(modules = [SettingsModule::class])
interface SettingsSubcomponent {
    fun inject(settingsFrag: SettingsFrag)

    @Subcomponent.Factory
    interface Factory{
        fun create() : SettingsSubcomponent
    }
}