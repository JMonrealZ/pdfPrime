package com.jimzmx.pdfprime.presentation.di.creatorCam

import com.jimzmx.pdfprime.presentation.creatorCam.CreatorCamFrag
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