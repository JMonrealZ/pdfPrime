package com.example.pdfprime.presentation.utils

import android.app.ProgressDialog
import android.widget.ProgressBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object UiUpdater {
    fun updateProgressDialog(progressDialog : ProgressDialog,messageId : Int, value1 : Int, value2 : Int){
        CoroutineScope(Dispatchers.Main).launch{

        }
    }
}