package com.example.pdfprime.presentation.bottomSheetMenus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pdfprime.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetNewDoc : BottomSheetDialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.bottom_sheet_new_doc,container,false)
    }
}