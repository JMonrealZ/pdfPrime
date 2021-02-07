package com.example.pdfprime.presentation.bottomSheetMenus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pdfprime.App
import com.example.pdfprime.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_new_doc.view.*

class BottomSheetNewDoc() : BottomSheetDialogFragment(), NewDocInterface{
    private lateinit var adapter : OptionBSRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = OptionBSRecyclerViewAdapter(App.newDocBottomSheetOptions,this)
        val view =  inflater.inflate(R.layout.bottom_sheet_new_doc,container,false)
        view.rvOptions.layoutManager = LinearLayoutManager(context)
        view.rvOptions.adapter = adapter
        return view
    }

    override fun newDocCamera() {
        NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorCamFrag)
        super.dismiss()
    }

    override fun newDocText() {
        NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorTxtFrag)
        super.dismiss()
    }
}