package com.example.pdfprime.presentation.bottomSheetMenus

import com.example.pdfprime.data.model.Document

interface DocOperationInterface {
    fun onEditDoc(document : Document)
    fun onShareDoc(document : Document)
    fun onDeleteDoc(document : Document)
}