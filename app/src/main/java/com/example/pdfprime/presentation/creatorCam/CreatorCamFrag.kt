package com.example.pdfprime.presentation.creatorCam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pdfprime.R
import com.example.pdfprime.databinding.FragmentCreatorCamBinding
import com.example.pdfprime.presentation.di.Injector
import com.example.pdfprime.presentation.utils.Constants
import com.example.pdfprime.presentation.utils.Renderer
import java.util.ArrayList
import javax.inject.Inject

/**
 * This fragment is used to create a pdf from camera
 */
class CreatorCamFrag : Fragment() {
    @Inject lateinit var factory : CreatorCamViewModelFactory
    private lateinit var binding : FragmentCreatorCamBinding
    private lateinit var adapter : CreatorCamRecyclerViewAdapter
    private lateinit var creatorCamViewModel: CreatorCamViewModel
    private lateinit var document2Edit : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVariables(inflater,container)
        initRecyclerView()
        setListeners()
        return binding.root
    }

    private fun initVariables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_creator_cam,container,false)

        (activity?.application as Injector).createCreatorCamSubComponent().inject(this)

        creatorCamViewModel = ViewModelProvider(this,factory).get(CreatorCamViewModel::class.java)

        adapter = CreatorCamRecyclerViewAdapter(listOf<Page>())

        if(arguments != null){
            document2Edit = requireArguments().getString(Constants.DOCUMENT,"")
            var pages = ArrayList<Page>()
            var images = Renderer.renderPages(context,document2Edit)
            for(image in 0 until images.size){
                pages.add(Page(images[image],image.toString(),image,image,false))
            }
            adapter.setList(pages)
        }

    }

    private fun initRecyclerView() {
        binding.rvPages.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = this@CreatorCamFrag.adapter
        }
    }

    private fun setListeners(){

    }

    companion object {}
}