package com.jimzmx.pdfprime.presentation.interfaces

import android.net.Uri

interface INameDocDialog {
    fun onNameDocSelected(name : String,uri : Uri?, size : Int)
}