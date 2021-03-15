package com.example.pdfprime.presentation.di.viewer

import com.example.pdfprime.presentation.viewer.ViewerFrag
import dagger.Subcomponent

@ViewerScope
@Subcomponent(modules = [ViewerModule::class])
interface ViewerSubcomponent {
    fun inject(viewerFrag: ViewerFrag)

    @Subcomponent.Factory
    interface Factory{
        fun create():ViewerSubcomponent
    }
}