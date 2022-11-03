package com.jimzmx.pdfprime.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.jimzmx.pdfprime.App
import com.jimzmx.pdfprime.R
import com.jimzmx.pdfprime.databinding.FragmentViewerBinding
import com.jimzmx.pdfprime.presentation.di.Injector
import com.jimzmx.pdfprime.presentation.utils.Constants
import com.jimzmx.pdfprime.presentation.viewModels.ViewerViewModel
import com.jimzmx.pdfprime.presentation.viewModelFactories.ViewerViewModelFactory
import java.io.File
import javax.inject.Inject

/**
 * This class is used to display any pdf
 */
class ViewerFrag : Fragment() {
    @Inject lateinit var factory : ViewerViewModelFactory
    private lateinit var binding : FragmentViewerBinding
    //adapter
    lateinit var viewerViewModel: ViewerViewModel
    private lateinit var document2View : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVariables(inflater,container)
        setListeners()
        setObservers()
        return binding.root
    }

    private fun initVariables(inflater: LayoutInflater, container: ViewGroup?){
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viewer,container,false)

        (activity?.application as Injector).createViewerSubComponent()
            .inject(this)

        viewerViewModel = ViewModelProvider(this,factory).get(ViewerViewModel::class.java)

        if(arguments != null) {
            document2View = requireArguments().getString(Constants.DOCUMENT, "")
            if(document2View.isNotEmpty()){
                binding.pdfViewer.fromFile(File(File(context?.filesDir,App.storagePdf),document2View)).load()
                viewerViewModel.setpdfName(document2View)
            }
        }
    }

    private fun setListeners(){
        binding.apply {
            ibDelete.setOnClickListener{

            }
            ibEdit.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constants.DOCUMENT,document2View)
                NavHostFragment.findNavController(this@ViewerFrag).navigate(R.id.action_viewerFrag_to_creatorCamFrag,bundle)
            }
            ibShare.setOnClickListener {

            }
        }
    }

    private fun setObservers() {

    }

    companion object {}
}