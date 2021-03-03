package com.example.pdfprime.presentation.di

import com.example.pdfprime.presentation.di.creatorCam.CreatorCamSubcomponent
import com.example.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent

interface Injector {
    fun createMyDocumentsSubComponent():MyDocumentsSubcomponent
    fun createCreatorCamSubComponent():CreatorCamSubcomponent
}