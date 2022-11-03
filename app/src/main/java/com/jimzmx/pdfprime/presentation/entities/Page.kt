package com.jimzmx.pdfprime.presentation.entities

import android.graphics.Bitmap
import android.net.Uri

data class Page(
    var owner : String, //This is the document where the page belongs
    var image : Bitmap?,
    var imageUri : Uri?,
    //var imageName : String,
    //var pageNumber : Int,   //This is only for updating view(maybe is not necesary)
    var originalPage : Int //This is only when we are merging or extracting pages
    //var delete : Boolean
)