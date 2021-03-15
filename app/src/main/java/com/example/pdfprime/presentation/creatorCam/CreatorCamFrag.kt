package com.example.pdfprime.presentation.creatorCam

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pdfprime.App
import com.example.pdfprime.R
import com.example.pdfprime.databinding.FragmentCreatorCamBinding
import com.example.pdfprime.presentation.bottomSheetMenus.BottomSheetNewDoc
import com.example.pdfprime.presentation.di.Injector
import com.example.pdfprime.presentation.dialogs.Dialogs
import com.example.pdfprime.presentation.dialogs.NameDocDialogInterface
import com.example.pdfprime.presentation.utils.Constants
import com.example.pdfprime.presentation.utils.PdfCreator
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/**
 * This fragment is used to create a pdf from camera
 */
class CreatorCamFrag : Fragment() , NameDocDialogInterface{
    @Inject lateinit var factory : CreatorCamViewModelFactory
    private lateinit var binding : FragmentCreatorCamBinding
    private lateinit var adapter : CreatorCamRecyclerViewAdapter
    private lateinit var creatorCamViewModel: CreatorCamViewModel
    private lateinit var document2Edit : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVariables(inflater,container)
        initRecyclerView()
        setListeners()
        setObservers()
        return binding.root
    }

    private fun initVariables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_creator_cam,container,false)

        (activity?.application as Injector).createCreatorCamSubComponent().inject(this)

        creatorCamViewModel = ViewModelProvider(this,factory).get(CreatorCamViewModel::class.java)
        binding.creatorCamViewModel = creatorCamViewModel
        binding.lifecycleOwner = this

        adapter = CreatorCamRecyclerViewAdapter(arrayListOf())

        if(arguments != null) {
            document2Edit = requireArguments().getString(Constants.DOCUMENT, "")
            if(document2Edit.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch { context?.let {
                    creatorCamViewModel.renderPages(document2Edit,
                        it
                    ) }
                }
            }else{
                //Todo: se creara un pdf desde la camara

            }
        }
    }

    private fun initRecyclerView() {
        binding.rvPages.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = this@CreatorCamFrag.adapter
        }


    }

    private fun setListeners(){
        binding.apply {
            ibCancel.setOnClickListener{

            }
            ibCamera.setOnClickListener{

            }
            ibAccept.setOnClickListener{
                val direc = File(context?.filesDir,App.storagePdf)
                CoroutineScope(Dispatchers.IO).launch {
                    creatorCamViewModel?.createPdf(direc,document2Edit,adapter.getPages())
                }
            }
        }
    }

    private fun setObservers(){
        creatorCamViewModel.pagesObserver().observe(viewLifecycleOwner, Observer {
            if(it != null){
                adapter.setList(it.toMutableList())
            }
            else
                Toast.makeText(context,"couldn't render pages", Toast.LENGTH_LONG).show()
        })
        creatorCamViewModel.isLoadingObserver().observe(viewLifecycleOwner, Observer {
            binding.apply {
                if(it){
                    llContent.visibility = View.GONE
                    llLoading.visibility = View.VISIBLE

                }else{
                    llContent.visibility = View.VISIBLE
                    llLoading.visibility = View.GONE
                }
            }
        })
        creatorCamViewModel.isLoadingMessageObserver().observe(viewLifecycleOwner, Observer {
            binding.tvRenderingProgress.text = it
        })
        creatorCamViewModel.newDocumentObserver().observe(viewLifecycleOwner, Observer {
            Dialogs.createSelectNameDoc(this@CreatorCamFrag, context,layoutInflater)
        })
    }

    override fun onNameDocSelected(name: String, uri: Uri?, size: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            creatorCamViewModel.savePdf(name)
        }
    }
}