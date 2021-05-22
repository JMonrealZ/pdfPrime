package com.jimzmx.pdfprime.domain.usecase

import com.jimzmx.pdfprime.domain.repository.DocumentRepository

class UpdateNamePdfUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute(newName : String,idDoc : Int) = documentRepository.updateNamePdf(newName,idDoc)

}