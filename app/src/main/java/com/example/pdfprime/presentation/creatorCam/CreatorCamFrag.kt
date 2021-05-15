package com.example.pdfprime.presentation.creatorCam

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfprime.R
import com.example.pdfprime.databinding.FragmentCreatorCamBinding
import com.example.pdfprime.presentation.di.Injector
import com.example.pdfprime.presentation.dialogs.Dialogs
import com.example.pdfprime.presentation.dialogs.NameDocDialogInterface
import com.example.pdfprime.presentation.utils.Constants
import com.example.pdfprime.presentation.utils.Constants.REQUEST_IMAGE_CAPTURE
import com.example.pdfprime.presentation.utils.Constants.REQUEST_IMAGE_CROP
import com.example.pdfprime.presentation.utils.RendererCoroutines
import com.example.pdfprime.presentation.utils.Utilities
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.yalantis.ucrop.UCrop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import javax.inject.Inject

/**
 * This fragment is used to create a pdf from camera
 */
class CreatorCamFrag : Fragment() , NameDocDialogInterface{
    @Inject lateinit var factory : CreatorCamViewModelFactory
    private lateinit var binding : FragmentCreatorCamBinding
    private lateinit var adapter : CreatorCamRecyclerViewAdapter
    private lateinit var creatorCamViewModel: CreatorCamViewModel
    private lateinit var documents2Edit : String
    private var photoUri : Uri? = null

    //Drag and drop pages
    private val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
                or ItemTouchHelper.DOWN
                or ItemTouchHelper.START
                or ItemTouchHelper.END,0){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            (recyclerView.adapter as CreatorCamRecyclerViewAdapter).moveItem(
                viewHolder.adapterPosition,
                target.adapterPosition
            )
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }
    }
    private val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)

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

        adapter = CreatorCamRecyclerViewAdapter(arrayListOf(),creatorCamViewModel.getInterface())

        if(arguments != null) {
            documents2Edit = requireArguments().getString(Constants.DOCUMENT, "")
            if(documents2Edit.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch { context?.let {
                    creatorCamViewModel.renderPages(documents2Edit,
                        it
                    ) }
                }
            }else{
                //Todo: se creara un pdf desde la camara

            }
        }

        itemTouchHelper.attachToRecyclerView(binding.rvPages)
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
                NavHostFragment.findNavController(this@CreatorCamFrag).navigate(R.id.action_creatorCamFrag_to_documentFrag)
            }
            ibCamera.setOnClickListener{
                Dexter.withContext(activity)
                    .withPermission(android.Manifest.permission.CAMERA)
                    .withListener(object : PermissionListener{
                        override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                            startCamera()
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            p0: PermissionRequest?,
                            p1: PermissionToken?
                        ) {
                            TODO("Not yet implemented")
                        }

                        override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                            Toast.makeText(activity,"This permission is required",Toast.LENGTH_LONG).show()
                        }

                    }).check()
            }
            ibAccept.setOnClickListener{
                //val direc = File(context?.filesDir,App.storagePdf)
                CoroutineScope(Dispatchers.IO).launch {
                    //creatorCamViewModel?.createPdf(direc,documents2Edit,adapter.getPages())
                    creatorCamViewModel?.createPdf()
                }
            }
        }
    }

    fun startCamera(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                takePictureIntent -> takePictureIntent.resolveActivity(requireActivity().packageManager).also {

            val photoFile : File? = try{
                Utilities.Camera.createImageFile()
            }catch (ex : IOException){
                null
            }

            photoFile?.also {
                photoUri = FileProvider.getUriForFile(
                    requireContext(),
                "com.example.pdfprime.fileprovider",
                it)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                //takePictureIntent.putExtra()
                startActivityForResult(takePictureIntent,Constants.REQUEST_IMAGE_CAPTURE)
            }
            }
        }
    }

    private fun setObservers(){
        creatorCamViewModel.apply {
            pagesObserver().observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    adapter.setList(it.toMutableList())
                } else
                    Toast.makeText(context, "couldn't render pages", Toast.LENGTH_LONG).show()
            })
            isLoadingObserver().observe(viewLifecycleOwner, Observer {
                binding.apply {
                    if (it) {
                        llContent.visibility = View.GONE
                        llLoading.visibility = View.VISIBLE
                    } else {
                        llContent.visibility = View.VISIBLE
                        llLoading.visibility = View.GONE
                    }
                }
            })
            isLoadingMessageObserver().observe(viewLifecycleOwner, Observer {
                binding.tvRenderingProgress.text = it
            })
            newDocumentObserver().observe(viewLifecycleOwner, Observer {
                Dialogs.createSelectNameDoc(
                    this@CreatorCamFrag,
                    context,
                    layoutInflater,
                    R.string.titleChooseNameDoc
                )
            })
            finishFlag().observe(viewLifecycleOwner, Observer {
                if(it) {
                    NavHostFragment.findNavController(this@CreatorCamFrag).navigate(R.id.action_creatorCamFrag_to_documentFrag)

                }
            })
            pageToCropOberver().observe(viewLifecycleOwner, Observer {
                if(it != null){
//                    val destinationFileName: String = "SampleCropImage.jpg"
                    val sourceFileNameArray = it.imageUri.toString().split("/")
                    val sourceFileName = sourceFileNameArray[sourceFileNameArray.size - 1]  //getting source file's name
                    val destinationFileName =
                        if(!sourceFileName.contains("Cropped"))
                            sourceFileName.substring(0,sourceFileName.length - 4) + "Cropped1.jpg"
                        else{
                            //This page has already been cropped
                            val fileNameSplitted = sourceFileName.split("Cropped")
                            val numberStr = fileNameSplitted[1].replace(".jpg","")
                            val number = numberStr.toInt() + 1  //number of cropping
                            fileNameSplitted[0] + "Cropped" + (number) + ".jpg"
                        }


                    val uCrop = it.imageUri?.let { it1 ->
                        UCrop.of(it1, Uri.fromFile(File(Utilities.Direc.img(), destinationFileName)))
                    }
                    uCrop!!.start(requireActivity(),this@CreatorCamFrag, REQUEST_IMAGE_CROP)
                }
            })
        }
    }

    override fun onNameDocSelected(name: String, uri: Uri?, size: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            creatorCamViewModel.savePdf(name)
            RendererCoroutines.createFistPage(name)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == RESULT_OK){
            when(requestCode){
                REQUEST_IMAGE_CAPTURE -> {
                    if(photoUri != null)
                        creatorCamViewModel.newPageFromCamera(photoUri)
                    photoUri = null
                }
                REQUEST_IMAGE_CROP -> {
                    //creatorCamViewModel.newCroppedImage(data?.data)
                    val uri = data?.let { UCrop.getOutput(it) }
                    creatorCamViewModel.newCroppedImage(uri)

//                    uri?.let { ResultActivity.startWithUri(requireContext(), it) }
                }
            }

//            if(photoUri != null)
//                creatorCamViewModel.newPageFromCamera(photoUri)

//            photoUri = null

        }


    }


}