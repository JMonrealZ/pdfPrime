package com.jimzmx.pdfprime.presentation.interfaces

import com.jimzmx.pdfprime.presentation.entities.Page

interface IPageOperation {
    fun onDeletePage(pageNumber : Int)
    fun onMovePage(from : Int, to : Int)
    fun onClickPage(page : Page?)
}