package com.example.pdfprime.presentation.di.core

import com.example.pdfprime.presentation.di.creatorCam.CreatorCamSubcomponent
import com.example.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent
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
}