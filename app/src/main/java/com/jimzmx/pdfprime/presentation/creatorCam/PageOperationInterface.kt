package com.jimzmx.pdfprime.presentation.creatorCam

interface PageOperationInterface {
    fun onDeletePage(pageNumber : Int)
    fun onMovePage(from : Int, to : Int)
    fun onClickPage(page : Page?)
}