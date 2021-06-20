package com.jimzmx.pdfprime.presentation.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Method to get current fragment from tha navigation component
 */
val FragmentManager.currentNavigationFragment : Fragment?
get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

