package com.loancalculator.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.loancalculator.app.R
import com.loancalculator.app.databinding.FragmentHomeBinding
import com.loancalculator.data.LoanData


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeViewModel: HomeViewModel


    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var homeViewmodelFactory = HomeViewmodelFactory(requireActivity().application)
        homeViewModel = ViewModelProvider(this,homeViewmodelFactory).get(HomeViewModel::class.java)

        binding.viewmodel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homefragment = this



        homeViewModel.itemPosition.observe(viewLifecycleOwner, Observer {
/*
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()*/

        })




    }

    fun calculate(){

        val itemPosition = homeViewModel.itemPosition
        val loanText = homeViewModel.loanText
        val interestLive = homeViewModel.interestLive
        val numOfYearsLive = homeViewModel.numOfYearsLive

        val loanData =  LoanData(itemPosition.value.toString(), loanText.value.toString(),interestLive.value.toString(),numOfYearsLive.value.toString())

        val action = HomeFragmentDirections.actionNavHomeToRepaymentListFragment(loanData)
        view?.let { Navigation.findNavController(it).navigate(action) }

    }




}