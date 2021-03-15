package com.example.pdfprime.presentation.di

import com.example.pdfprime.presentation.di.creatorCam.CreatorCamSubcomponent
import com.example.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent
import com.example.pdfprime.presentation.di.viewer.ViewerSubcomponent

interface Injector {
    fun createMyDocumentsSubComponent():MyDocumentsSubcomponent
    fun createCreatorCamSubComponent():CreatorCamSubcomponent
    fun createViewerSubComponent():ViewerSubcomponent
}