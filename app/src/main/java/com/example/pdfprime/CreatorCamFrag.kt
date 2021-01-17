package com.example.pdfprime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pdfprime.databinding.FragmentCreatorCamBinding
import com.example.pdfprime.databinding.FragmentDocumentBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CreatorCamFrag : Fragment() {
    private lateinit var binding : FragmentCreatorCamBinding

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
//        return inflater.inflate(R.layout.fragment_creator_cam, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_creator_cam,container,false)
        setListeners()
        return binding.root
    }

    private fun setListeners(){

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreatorCamFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}