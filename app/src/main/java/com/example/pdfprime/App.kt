package com.example.pdfprime

import android.app.Application
import android.content.Context
import com.example.pdfprime.presentation.bottomSheetMenus.BottomSheetOption
import com.example.pdfprime.presentation.di.Injector
import com.example.pdfprime.presentation.di.core.AppComponent
import com.example.pdfprime.presentation.di.core.AppModule
import com.example.pdfprime.presentation.di.core.DaggerAppComponent
import com.example.pdfprime.presentation.di.creatorCam.CreatorCamSubcomponent
import com.example.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent
import com.example.pdfprime.presentation.di.viewer.ViewerSubcomponent
import com.example.pdfprime.presentation.utils.Constants
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader

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

    override fun createCreatorCamSubComponent(): CreatorCamSubcomponent {
        return appComponent.creatorCamSubcomponent().create()
    }

    override fun createViewerSubComponent(): ViewerSubcomponent {
        return appComponent.viewerSubcomponent().create()
    }

    companion object{
        lateinit var storagePdf : String
        lateinit var storagePagPdf : String
        lateinit var storageFirstPagePdf : String
        lateinit var newDocBottomSheetOptions : ArrayList<BottomSheetOption>
        lateinit var clickDocBottomSheetOptions : ArrayList<BottomSheetOption>
        lateinit var appContext : Context
    }

    init{
        storagePdf = "PDFS"
        storagePagPdf = "IMAGES"
        storageFirstPagePdf = "FIRSTPAGE"

        newDocBottomSheetOptions = ArrayList()
        newDocBottomSheetOptions.apply {
            add(BottomSheetOption(R.drawable.ic_note_add_24,Constants.NEWDOC_DIS,R.string.titleButtonNewDocDis))
            add(BottomSheetOption(R.drawable.ic_camera_24,Constants.NEWDOC_CAM,R.string.titleButtonNewDocCam))
            add(BottomSheetOption(R.drawable.ic_keyboard_24,Constants.NEWDOC_TXT,R.string.titleButtonNewDocTxt))
        }

        clickDocBottomSheetOptions = ArrayList()
        clickDocBottomSheetOptions.apply {
            add(BottomSheetOption(R.drawable.ic_open_in_full_24,Constants.DOC_OPEN,R.string.titleButtonOpenDoc))
            add(BottomSheetOption(R.drawable.ic_edit_24,Constants.DOC_EDIT,R.string.titleButtonEditDoc))
            add(BottomSheetOption(R.drawable.ic_share_24,Constants.DOC_SHARE,R.string.titleButtonShareDoc))
            add(BottomSheetOption(R.drawable.ic_delete_24,Constants.DOC_DELETE,R.string.titleButtonDeleteDoc))
        }

        appContext = this
    }
}