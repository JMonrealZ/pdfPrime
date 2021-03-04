package com.example.pdfprime.presentation.creatorCam

import android.graphics.Bitmap

data class Page(
    var image : Bitmap?,
    var imageName : String,
    var pageNumber : Int,
    var newPage : Int,
    var delete : Boolean
)