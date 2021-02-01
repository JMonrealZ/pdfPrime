package com.example.pdfprime.presentation.di.core

import android.content.Context
import com.example.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MyDocumentsSubcomponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext():Context{
        return context.applicationContext
    }
}