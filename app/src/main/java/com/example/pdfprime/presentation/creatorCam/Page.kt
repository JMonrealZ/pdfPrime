package com.example.pdfprime.presentation.creatorCam

import android.graphics.Bitmap

data class Page(
    var image : Bitmap?,
    var imageName : String,
    var pageNumber : Int,   //This is only for updating view
    var originalPage : Int, //This is only when we are merging or extracting pages
    var delete : Boolean
)