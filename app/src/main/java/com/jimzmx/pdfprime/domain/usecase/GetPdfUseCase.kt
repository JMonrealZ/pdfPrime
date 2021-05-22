package com.jimzmx.pdfprime.domain.usecase

import com.jimzmx.pdfprime.data.entities.Document
import com.jimzmx.pdfprime.domain.repository.DocumentRepository

class GetPdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(idDoc : Int) : Document = documentRepository.getPdf(idDoc)
}