package com.jimzmx.pdfprime.presentation.di.settings

import com.jimzmx.pdfprime.presentation.fragments.SettingsFrag
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