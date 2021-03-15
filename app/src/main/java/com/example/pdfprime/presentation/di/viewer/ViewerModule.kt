package com.example.pdfprime.presentation.di.viewer

import com.example.pdfprime.domain.usecase.DeletePdfUseCase
import com.example.pdfprime.domain.usecase.UpdateNamePdfUseCase
import com.example.pdfprime.presentation.viewer.ViewerViewModel
import com.example.pdfprime.presentation.viewer.ViewerViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewerModule {
    @ViewerScope
    @Provides
    fun provideViewerViewModelFactory(updateNamePdfUseCase: UpdateNamePdfUseCase, deletePdfUseCase: DeletePdfUseCase) : ViewerViewModelFactory{
        return ViewerViewModelFactory(updateNamePdfUseCase,deletePdfUseCase)
    }
}