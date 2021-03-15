package com.example.pdfprime.presentation.di.core

import android.content.Context
import com.example.pdfprime.presentation.di.creatorCam.CreatorCamSubcomponent
import com.example.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent
import com.example.pdfprime.presentation.di.viewer.ViewerSubcomponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    MyDocumentsSubcomponent::class,
    CreatorCamSubcomponent::class,
    ViewerSubcomponent::class
])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext():Context{
        return context.applicationContext
    }

}