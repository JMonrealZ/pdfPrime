package com.jimzmx.pdfprime.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SeekBar
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jimzmx.pdfprime.R
import com.jimzmx.pdfprime.databinding.FragmentSettingsBinding
import com.jimzmx.pdfprime.presentation.di.Injector
import javax.inject.Inject

/**
 *  This class is used to set general settings like language, page size...
 */
class SettingsFrag : Fragment(){
    @Inject lateinit var factory : SettingsViewModelFactory
    private lateinit var binding : FragmentSettingsBinding
    private lateinit var settingsViewModel : SettingsViewModel
    private lateinit var adapter : PageSizeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initVariables(inflater,container)
        setListeners()
        setObservers()
        initBiometrics()
        return binding.root
    }

    private fun initVariables(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings,container,false)

        (activity?.application as Injector).createSettingsSubComponent()
            .inject(this)

        settingsViewModel = ViewModelProvider(this,factory).get(SettingsViewModel::class.java)

        adapter = PageSizeAdapter(requireContext(),R.layout.list_item_page_size, mutableListOf())
        binding.spPageSize.adapter = adapter
    }

    private fun setListeners(){
        binding.apply {
            spPageSize.onItemSelectedListener = onItemSelectedListener
            sbPageQuality.setOnSeekBarChangeListener(onSeekBarChangeListener)
        }
    }

    private fun setObservers(){
        settingsViewModel.apply {
            getPagesSizes().observe(viewLifecycleOwner, Observer{
                adapter.clear()
                adapter.addAll(it)
            })
            getImagesPagesQuality().observe(viewLifecycleOwner, Observer {
                binding.apply {
                    tvPageQuality.text = (it * 100F).toString()
                }
            })
        }



    }

    private val onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {
            //Do nothing
        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            settingsViewModel.updatePageSizeDefault(p2)
        }

    }

    var a : Int = 0
    private val onSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) = binding.tvPageQuality.setText(p1.toFloat().toString())
        override fun onStartTrackingTouch(p0: SeekBar?) {}
        override fun onStopTrackingTouch(p0: SeekBar?) = settingsViewModel.updatePageQualityDefault(p0!!.progress.toFloat() / 100)
    }

    fun initBiometrics(){
        val biometricManager = BiometricManager.from(requireContext())
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                settingsViewModel.updateBiometricReaderFlag(true)
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                settingsViewModel.updateBiometricReaderFlag(false)
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                settingsViewModel.updateBiometricReaderFlag(false) //Todo here when is needed?
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
//                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
//                    putExtra(
//                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
//                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
//                    )
//                }
//                startActivityForResult(enrollIntent, 100)
                settingsViewModel.updateBiometricEnrollFlag(false)
            }
        }
    }
}