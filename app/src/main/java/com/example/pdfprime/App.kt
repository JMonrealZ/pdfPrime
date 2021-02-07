package com.example.pdfprime

import android.app.Application
import android.graphics.drawable.Drawable
import com.example.pdfprime.presentation.bottomSheetMenus.BottomSheetOption
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

    companion object{
        lateinit var newDocBottomSheetOptions : ArrayList<BottomSheetOption>
    }

    init{
        newDocBottomSheetOptions = ArrayList()
        newDocBottomSheetOptions.add(BottomSheetOption(R.drawable.ic_camera_24,1,R.string.titleButtonNewDocCam))
        newDocBottomSheetOptions.add(BottomSheetOption(R.drawable.ic_keyboard_24,2,R.string.titleButtonNewDocTxt))
    }
}