package com.jimzmx.pdfprime.domain.usecase

import com.jimzmx.pdfprime.data.entities.Document
import com.jimzmx.pdfprime.domain.repository.DocumentRepository

class GetPdfsUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute() : List<Document> = documentRepository.getPdfs()
}