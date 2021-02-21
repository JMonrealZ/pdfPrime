package com.example.pdfprime.presentation.bottomSheetMenus

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pdfprime.App
import com.example.pdfprime.R
import com.example.pdfprime.presentation.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_new_doc.view.*

class BottomSheetNewDoc() : BottomSheetDialogFragment()/*, NewDocInterface*/{
    private lateinit var adapter : OptionBSRecyclerViewAdapter
    private lateinit var parentFrag: Fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = OptionBSRecyclerViewAdapter(
            App.newDocBottomSheetOptions,
            {optionSelected : BottomSheetOption -> onOptionSelected(optionSelected)}
        )
        val view =  inflater.inflate(R.layout.bottom_sheet_new_doc,container,false)
        //view.rvOptions.layoutManager = LinearLayoutManager(context)
        view.rvOptions.layoutManager = GridLayoutManager(context,3)
        view.rvOptions.adapter = adapter
        return view
    }

    private fun onOptionSelected( option : BottomSheetOption){
        when(option.idOption){
            Constants.NEWDOC_DIS -> openFileBrowser()
            Constants.NEWDOC_CAM -> NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorCamFrag)
            Constants.NEWDOC_TXT -> NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorTxtFrag)
        }
        super.dismiss()
    }

//    override fun newDocCamera() {
//        NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorCamFrag)
//        super.dismiss()
//    }
//
//    override fun newDocText() {
//        NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorTxtFrag)
//        super.dismiss()
//    }
//
//    override fun newDocDispositivo() {
//        openFileBrowser()
//        super.dismiss()
//    }

    fun setParent(fragment: Fragment){
        parentFrag = fragment
    }

    fun openFileBrowser(){
        val fileIntent = Intent(Intent.ACTION_GET_CONTENT)
        fileIntent.type = "application/pdf"
        parentFrag.startActivityForResult(fileIntent,1)
    }
}