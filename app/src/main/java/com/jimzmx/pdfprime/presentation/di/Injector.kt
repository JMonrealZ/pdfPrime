package com.jimzmx.pdfprime.presentation.di

import com.jimzmx.pdfprime.presentation.di.creatorCam.CreatorCamSubcomponent
import com.jimzmx.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent
import com.jimzmx.pdfprime.presentation.di.settings.SettingsSubcomponent
import com.jimzmx.pdfprime.presentation.di.viewer.ViewerSubcomponent

interface Injector {
    fun createMyDocumentsSubComponent():MyDocumentsSubcomponent
    fun createCreatorCamSubComponent():CreatorCamSubcomponent
    fun createViewerSubComponent():ViewerSubcomponent
    fun createSettingsSubComponent():SettingsSubcomponent
}