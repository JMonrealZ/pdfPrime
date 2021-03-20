package com.example.pdfprime.presentation.CustomControls

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.pdfprime.App

/**
 * This class is used to show app's version in drawer layout, could grow up in future
 */
class CustomTextView : AppCompatTextView{
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr : Int) : super(context,attributeSet,defStyleAttr)

    init {
        super.setText(App.appContext.packageManager.getPackageInfo(
            App.appContext.packageName,
            0
        ).versionName)
    }

}