package com.example.pdfprime.presentation.bottomSheetMenus

import com.example.pdfprime.data.entities.Document

interface DocOperationInterface {
    fun onEditDoc(document : Document)
    fun onShareDoc(document : Document)
    fun onDeleteDoc(document : Document)
    fun onOpenDoc(document: Document)
}