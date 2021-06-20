package com.jimzmx.pdfprime.presentation.utils

import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage
import com.jimzmx.pdfprime.App

/**
 * Method to get current fragment from tha navigation component
 */
val FragmentManager.currentNavigationFragment : Fragment?
get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

/**
 * Method to get metadata from image and solve problem of rotated images
 */
fun Uri.fixRotation() : Int{
    val inputStream = App.appContext.contentResolver.openInputStream(this)
    val ei = ExifInterface(inputStream!!)

    val orientation : Int = ei.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )

    return when(orientation){
        ExifInterface.ORIENTATION_ROTATE_90 -> 90
        ExifInterface.ORIENTATION_ROTATE_180 -> 180
        ExifInterface.ORIENTATION_ROTATE_270 -> 270
        ExifInterface.ORIENTATION_NORMAL -> 0
        else -> 0
    }
}
