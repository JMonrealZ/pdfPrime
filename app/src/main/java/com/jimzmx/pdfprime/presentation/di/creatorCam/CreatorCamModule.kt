package com.jimzmx.pdfprime.presentation.di.creatorCam

import com.jimzmx.pdfprime.domain.usecase.*
import com.jimzmx.pdfprime.presentation.creatorCam.CreatorCamViewModelFactory
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