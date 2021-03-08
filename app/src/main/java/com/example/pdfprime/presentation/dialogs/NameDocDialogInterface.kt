package com.example.pdfprime.presentation.dialogs

import android.net.Uri

interface NameDocDialogInterface {
    fun onNameDocSelected(name : String,uri : Uri?, size : Int)
}