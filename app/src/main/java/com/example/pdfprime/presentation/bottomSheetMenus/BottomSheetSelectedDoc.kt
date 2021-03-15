package com.example.pdfprime.presentation.bottomSheetMenus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pdfprime.App
import com.example.pdfprime.R
import com.example.pdfprime.data.entities.Document
import com.example.pdfprime.presentation.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_new_doc.view.*

class BottomSheetSelectedDoc(
    private var documentSelected : Document,
    private val docOperationInterface: DocOperationInterface
) : BottomSheetDialogFragment(){
    private lateinit var adapter : OptionBSRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = OptionBSRecyclerViewAdapter(
            App.clickDocBottomSheetOptions,
            {optionSelected : BottomSheetOption -> onOptionSelected(optionSelected)}
        )
        val view =  inflater.inflate(R.layout.bottom_sheet_new_doc,container,false)
        view.rvOptions.layoutManager = GridLayoutManager(context,3)
        view.rvOptions.adapter = adapter
        return view
    }

    private fun onOptionSelected( option : BottomSheetOption){
        when(option.idOption){
            Constants.DOC_EDIT -> docOperationInterface.onEditDoc(documentSelected)
            Constants.DOC_SHARE -> docOperationInterface.onShareDoc(documentSelected)
            Constants.DOC_DELETE -> docOperationInterface.onDeleteDoc(documentSelected)
            Constants.DOC_OPEN -> docOperationInterface.onOpenDoc(documentSelected)
        }
        super.dismiss()
    }

    fun setDocument(document : Document){
        documentSelected = document
    }
}