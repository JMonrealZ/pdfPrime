package com.jimzmx.pdfprime.domain.usecase

import com.jimzmx.pdfprime.domain.repository.DocumentRepository

class DeletePdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(idDoc : Int) = documentRepository.deletePdf(idDoc)

}