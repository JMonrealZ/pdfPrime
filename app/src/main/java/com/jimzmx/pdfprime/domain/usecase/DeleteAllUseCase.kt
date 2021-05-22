package com.jimzmx.pdfprime.domain.usecase

import com.jimzmx.pdfprime.domain.repository.DocumentRepository

class DeleteAllUseCase(private val documentRepository: DocumentRepository) {
    suspend fun execute() = documentRepository.deleteAll()
}