package com.example.pdfprime.presentation.di.creatorCam

import com.example.pdfprime.presentation.creatorCam.CreatorCamFrag
import com.example.pdfprime.presentation.myDocuments.DocumentFrag
import dagger.Subcomponent

@CreatorCamScope
@Subcomponent(modules = [CreatorCamModule::class])
interface CreatorCamSubcomponent {
    fun inject(creatorCamFrag: CreatorCamFrag)

    @Subcomponent.Factory
    interface Factory{
        fun create():CreatorCamSubcomponent
    }
}