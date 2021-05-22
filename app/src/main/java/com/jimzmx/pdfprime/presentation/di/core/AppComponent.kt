package com.jimzmx.pdfprime.presentation.di.core

import com.jimzmx.pdfprime.presentation.di.creatorCam.CreatorCamSubcomponent
import com.jimzmx.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent
import com.jimzmx.pdfprime.presentation.di.settings.SettingsSubcomponent
import com.jimzmx.pdfprime.presentation.di.viewer.ViewerSubcomponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
AppModule::class,
DataBaseModule::class,
LocalDataModule::class,
RepositoryModule::class,
UseCaseModule::class
])
interface AppComponent {
    fun myDocumentsSubcomponent():MyDocumentsSubcomponent.Factory
    fun creatorCamSubcomponent():CreatorCamSubcomponent.Factory
    fun viewerSubcomponent():ViewerSubcomponent.Factory
    fun settingsSubcomponent():SettingsSubcomponent.Factory
}