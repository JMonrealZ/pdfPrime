package com.jimzmx.pdfprime.presentation.bottomSheetMenus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.R
import com.jimzmx.pdfprime.data.entities.Document
import com.jimzmx.pdfprime.presentation.utils.Constants.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jimzmx.pdfprime.presentation.entities.BottomSheetOption
import com.jimzmx.pdfprime.presentation.interfaces.IDocOperation
import kotlinx.android.synthetic.main.bottom_sheet_new_doc.view.*

class BottomSheetSelectedDoc(
    private var documentSelected : Document,
    private val iDocOperation: IDocOperation
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
            DOC_EDIT -> iDocOperation.onEditDoc(documentSelected)
            DOC_SHARE -> iDocOperation.onShareDoc(documentSelected)
            DOC_DELETE -> iDocOperation.onDeleteDoc(documentSelected)
            DOC_OPEN -> iDocOperation.onOpenDoc(documentSelected)
        }
        super.dismiss()
    }

    fun setDocument(document : Document){
        documentSelected = document
    }
}