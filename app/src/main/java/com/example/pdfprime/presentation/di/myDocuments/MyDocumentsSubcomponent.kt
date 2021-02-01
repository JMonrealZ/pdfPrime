package com.example.pdfprime.presentation.di.myDocuments

import androidx.fragment.app.Fragment
import com.example.pdfprime.MainActivity
import com.example.pdfprime.presentation.myDocuments.DocumentFrag
import dagger.Module
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