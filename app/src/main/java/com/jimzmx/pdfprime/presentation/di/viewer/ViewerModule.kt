package com.jimzmx.pdfprime.presentation.di.viewer

import com.jimzmx.pdfprime.domain.usecase.DeletePdfUseCase
import com.jimzmx.pdfprime.domain.usecase.UpdateNamePdfUseCase
import com.jimzmx.pdfprime.presentation.viewModelFactories.ViewerViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewerModule {
    @ViewerScope
    @Provides
    fun provideViewerViewModelFactory(updateNamePdfUseCase: UpdateNamePdfUseCase, deletePdfUseCase: DeletePdfUseCase) : ViewerViewModelFactory {
        return ViewerViewModelFactory(updateNamePdfUseCase,deletePdfUseCase)
    }
}