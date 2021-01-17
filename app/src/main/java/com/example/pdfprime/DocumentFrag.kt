package com.example.pdfprime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pdfprime.databinding.FragmentDocumentBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DocumentFrag : Fragment() {
    private lateinit var binding : FragmentDocumentBinding

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.fragment_document, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_document,container,false)
        setListeners()
        return binding.root
    }

    private fun setListeners(){
        binding.apply {
            btnCreCam.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_creatorCamFrag) }
            btnCreTxt.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_creatorTxtFrag) }
            btnView.setOnClickListener{ view -> view.findNavController().navigate(R.id.action_documentFrag_to_viewerFrag) }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DocumentFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}