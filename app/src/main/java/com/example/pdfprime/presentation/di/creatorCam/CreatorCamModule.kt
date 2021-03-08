package com.example.pdfprime.presentation.di.creatorCam

import com.example.pdfprime.domain.usecase.*
import com.example.pdfprime.presentation.creatorCam.CreatorCamViewModelFactory
import com.example.pdfprime.presentation.myDocuments.MyDocumentsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CreatorCamModule {

    @CreatorCamScope
    @Provides
    fun provideCreatorCamViewModelFactory(insertPdfUseCase: InsertPdfUseCase/*, createPdfUseCase: CreatePdfUseCase*/) : CreatorCamViewModelFactory {
        return CreatorCamViewModelFactory(insertPdfUseCase/*,createPdfUseCase*/)
    }
}