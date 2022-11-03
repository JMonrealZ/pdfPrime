package com.jimzmx.pdfprime.presentation.entities

import com.tom_roush.pdfbox.pdmodel.common.PDRectangle

data class PageSize(
    var pdRectangle : PDRectangle,
    var pdRectangleName : String,
    var visibleName : String,
    var dimensions : String?,
    var isSelected : Boolean
)