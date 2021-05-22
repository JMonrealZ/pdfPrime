package com.jimzmx.pdfprime.domain.usecase

import com.jimzmx.pdfprime.data.entities.Document
import com.jimzmx.pdfprime.domain.repository.DocumentRepository

class InsertPdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(document : Document) = documentRepository.insertPdf(document)
}