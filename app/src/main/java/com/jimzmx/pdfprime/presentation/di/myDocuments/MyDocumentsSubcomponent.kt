package com.jimzmx.pdfprime.presentation.di.myDocuments

import com.jimzmx.pdfprime.presentation.fragments.DocumentFrag
import dagger.Subcomponent

@MyDocumentsScope
@Subcomponent(modules = [MyDocumentsModule::class])
interface MyDocumentsSubcomponent {
    fun inject(documentFrag : DocumentFrag)
    //fun inject(mainActivity: MainActivity)
    //fun inject(documentFrag : Fragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():MyDocumentsSubcomponent
    }
}