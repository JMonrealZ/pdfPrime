package com.example.pdfprime

import android.app.Application
import android.content.Context
import android.graphics.pdf.PdfDocument
import com.example.pdfprime.presentation.bottomSheetMenus.BottomSheetOption
import com.example.pdfprime.presentation.di.Injector
import com.example.pdfprime.presentation.di.core.AppComponent
import com.example.pdfprime.presentation.di.core.AppModule
import com.example.pdfprime.presentation.di.core.DaggerAppComponent
import com.example.pdfprime.presentation.di.creatorCam.CreatorCamSubcomponent
import com.example.pdfprime.presentation.di.myDocuments.MyDocumentsSubcomponent
import com.example.pdfprime.presentation.di.settings.SettingsSubcomponent
import com.example.pdfprime.presentation.di.viewer.ViewerSubcomponent
import com.example.pdfprime.presentation.settings.PageSize
import com.example.pdfprime.presentation.utils.Constants
import com.example.pdfprime.presentation.utils.Constants.*
import com.example.pdfprime.presentation.utils.Utilities
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader

class App : Application() , Injector{
    private lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()

        pageSizes = mutableListOf<PageSize>().apply {
            add(PageSize(PDRectangle.LETTER,PDRectangle.LETTER.toString(),getString(R.string.letterSize),"",false))
            add(PageSize(PDRectangle.LEGAL,PDRectangle.LEGAL.toString(),getString(R.string.legalSize),"",false))
            add(PageSize(PDRectangle.A0,PDRectangle.A0.toString(),getString(R.string.A0),"",false))
            add(PageSize(PDRectangle.A1,PDRectangle.A1.toString(),getString(R.string.A1),"",false))
            add(PageSize(PDRectangle.A2,PDRectangle.A2.toString(),getString(R.string.A2),"",false))
            add(PageSize(PDRectangle.A3,PDRectangle.A3.toString(),getString(R.string.A3),"",false))
            add(PageSize(PDRectangle.A4,PDRectangle.A4.toString(),getString(R.string.A4),"",false))
            add(PageSize(PDRectangle.A5,PDRectangle.A5.toString(),getString(R.string.A5),"",false))
            add(PageSize(PDRectangle.A6,PDRectangle.A6.toString(),getString(R.string.A6),"",false))
        }
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

    override fun createSettingsSubComponent(): SettingsSubcomponent {
        return appComponent.settingsSubcomponent().create()
    }

    companion object{
        lateinit var storagePdf : String
        lateinit var storagePagPdf : String
        lateinit var storageFirstPagePdf : String
        lateinit var newDocBottomSheetOptions : ArrayList<BottomSheetOption>
        lateinit var clickDocBottomSheetOptions : ArrayList<BottomSheetOption>
        lateinit var appContext : Context
        lateinit var pageSizes : MutableList<PageSize>
    }

    init{
        appContext = this
        storagePdf = "PDFS"
        storagePagPdf = "IMAGES"
        storageFirstPagePdf = "FIRSTPAGE"

        newDocBottomSheetOptions = ArrayList()
        newDocBottomSheetOptions.apply {
            add(BottomSheetOption(R.drawable.ic_note_add_24,NEWDOC_DIS,R.string.titleButtonNewDocDis))
            add(BottomSheetOption(R.drawable.ic_add_photo_24,NEWDOC_GAL,R.string.titleButtonNewDocGal))
            add(BottomSheetOption(R.drawable.ic_camera_24,NEWDOC_CAM,R.string.titleButtonNewDocCam))
//            add(BottomSheetOption(R.drawable.ic_keyboard_24,Constants.NEWDOC_TXT,R.string.titleButtonNewDocTxt)) //I am not goint to implement this

        }

        clickDocBottomSheetOptions = ArrayList()
        clickDocBottomSheetOptions.apply {
            add(BottomSheetOption(R.drawable.ic_open_in_full_24,DOC_OPEN,R.string.titleButtonOpenDoc))
            add(BottomSheetOption(R.drawable.ic_edit_24,DOC_EDIT,R.string.titleButtonEditDoc))
            add(BottomSheetOption(R.drawable.ic_share_24,DOC_SHARE,R.string.titleButtonShareDoc))
            add(BottomSheetOption(R.drawable.ic_delete_24,DOC_DELETE,R.string.titleButtonDeleteDoc))
        }
    }
}