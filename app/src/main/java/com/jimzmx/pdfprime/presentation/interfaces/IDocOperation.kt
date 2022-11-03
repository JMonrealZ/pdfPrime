package com.jimzmx.pdfprime.presentation.interfaces

import com.jimzmx.pdfprime.data.entities.Document

interface IDocOperation {
    fun onEditDoc(document : Document)
    fun onShareDoc(document : Document)
    fun onDeleteDoc(document : Document)
    fun onOpenDoc(document: Document)
}