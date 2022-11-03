package com.jimzmx.pdfprime.presentation.bottomSheetMenus

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.R
import com.jimzmx.pdfprime.presentation.utils.Constants.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jimzmx.pdfprime.presentation.entities.BottomSheetOption
import kotlinx.android.synthetic.main.bottom_sheet_new_doc.view.*

class BottomSheetNewDoc() : BottomSheetDialogFragment(){
    private lateinit var adapter : OptionBSRecyclerViewAdapter
    private lateinit var parentFrag: Fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = OptionBSRecyclerViewAdapter(
            App.newDocBottomSheetOptions,
            {optionSelected : BottomSheetOption -> onOptionSelected(optionSelected)}
        )
        val view =  inflater.inflate(R.layout.bottom_sheet_new_doc,container,false)
        view.rvOptions.layoutManager = GridLayoutManager(context,3)
        view.rvOptions.adapter = adapter
        return view
    }

    private fun onOptionSelected( option : BottomSheetOption){
        when(option.idOption){
            NEWDOC_DIS -> openFileBrowser()
            NEWDOC_CAM -> NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorCamFrag)
//            Constants.NEWDOC_TXT -> NavHostFragment.findNavController(this).navigate(R.id.action_documentFrag_to_creatorTxtFrag)
            NEWDOC_GAL -> openFileBrowserImages()
        }
        super.dismiss()
    }

    fun setParent(fragment: Fragment){
        parentFrag = fragment
    }

    private fun openFileBrowser(){
        val fileIntent = Intent(Intent.ACTION_GET_CONTENT)
        fileIntent.type = "application/pdf"
//        parentFrag.startActivityForResult(fileIntent,1)
        parentFrag.startActivityForResult(fileIntent,NEWDOC_DIS)
    }

    private fun openFileBrowserImages(){
        val fileIntent = Intent(Intent.ACTION_GET_CONTENT)
        fileIntent.type = "image/*"
        fileIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
        parentFrag.startActivityForResult(fileIntent,NEWDOC_GAL)
    }
}