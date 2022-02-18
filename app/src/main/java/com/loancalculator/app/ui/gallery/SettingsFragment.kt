package com.loancalculator.app.ui.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textview.MaterialTextView
import com.loancalculator.app.Constants
import com.loancalculator.app.MainActivity
import com.loancalculator.app.R
import com.loancalculator.app.databinding.FragmentHomeBinding
import com.loancalculator.app.databinding.FragmentSettingsBinding
import com.loancalculator.app.ui.home.HomeViewModel
import com.loancalculator.app.ui.home.HomeViewmodelFactory
import com.loancalculator.app.ui.home.RepaymentViewModel
import com.loancalculator.app.ui.home.RepaymentViewmodelFactory
import com.loancalculator.app.ui.sharedpref.SharedPref
import java.util.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {


    private lateinit var homeViewModel: SettingsViewModel


    lateinit var binding: FragmentSettingsBinding

    var curLang = ""

    lateinit var sharedPref : SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = SharedPref(requireContext())

        var settingsViewmodelFactory = SettingsViewmodelFactory(requireActivity().application)
        homeViewModel = ViewModelProvider(this,settingsViewmodelFactory).get(SettingsViewModel::class.java)

        binding.viewmodel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.settingsfragment = this

        curLang = sharedPref.getLanguage().toString()


    }


    fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p1?.let {
           val spinner = p1 as MaterialTextView
            if (spinner.text.toString() != curLang){
                sharedPref.setLanguage(spinner.text.toString())
                homeViewModel._itemPosition.postValue(  if (spinner.text.toString() == "Norwegian") {
                    1
                } else {
                    0
                })
                if (spinner.text.toString() == "Norwegian") {
                    setAppLocale("no")
                } else {
                    setAppLocale("en")
                }
            }
        }

    }



    fun setAppLocale(language : String) {
        Constants.isLanguageChanged = true
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = requireActivity().resources.configuration
        config.setLocale(locale)
        requireContext().createConfigurationContext(config)
        requireContext().resources.updateConfiguration(config, requireActivity().resources.displayMetrics)
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }








}