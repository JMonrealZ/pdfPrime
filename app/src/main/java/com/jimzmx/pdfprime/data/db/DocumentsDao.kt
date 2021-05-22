package com.jimzmx.pdfprime.data.db

import androidx.room.*
import com.jimzmx.pdfprime.data.entities.Document

@Dao
interface DocumentsDao {
    @Query("SELECT * FROM DOCUMENTS")
    suspend fun getDocuments() : List<Document>

    @Query("SELECT * FROM DOCUMENTS WHERE INDEX_DOC = :idDoc")
    suspend fun getDocument(idDoc : Int) : Document

    @Query("DELETE FROM DOCUMENTS WHERE INDEX_DOC = :idDoc")
    suspend fun deleteDocument(idDoc : Int)

    @Query("UPDATE DOCUMENTS SET NAME_DOC = :newName WHERE INDEX_DOC = :idDoc")
    suspend fun updateNameDocument(newName : String, idDoc : Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewDocument(document : Document)

    @Query("DELETE FROM DOCUMENTS")
    suspend fun deleteAll()

}