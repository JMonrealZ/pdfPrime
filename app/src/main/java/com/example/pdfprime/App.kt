package com.example.pdfprime

import android.app.Application
import com.example.pdfprime.presentation.di.Injector
import com.example.pdfprime.presentation.di.core.AppComponent
import com.example.pdfprime.presentation.di.core.AppModule
import com.example.pdfprime.presentation.di.core.DaggerAppComponent
import com.example.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent

class App : Application() , Injector{
    private lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

    override fun createMyDocumentsSubComponent(): MyDocumentsSubcomponent {
        return appComponent.myDocumentsSubcomponent().create()
    }
}