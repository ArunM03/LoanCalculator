package com.loancalculator.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.loancalculator.app.R
import com.loancalculator.app.databinding.FragmentHomeBinding
import com.loancalculator.app.databinding.FragmentRepaymentBinding
import com.loancalculator.app.ui.loan.Loan
import com.loancalculator.data.LoanData
import com.loancalculator.data.RepaymentData

class RepaymentListFragment : Fragment(R.layout.fragment_repayment) {

    private lateinit var homeViewModel: RepaymentViewModel

    lateinit var binding: FragmentRepaymentBinding

    val args: RepaymentListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        binding = FragmentRepaymentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var repaymentViewmodelFactory = RepaymentViewmodelFactory(requireActivity().application)
        homeViewModel = ViewModelProvider(this,repaymentViewmodelFactory).get(RepaymentViewModel::class.java)

        val loan = Loan()



        if (args.loanData.loanType ==  getString(R.string.annuityloan)) {
            Toast.makeText(requireContext(), args.loanData.loanType, Toast.LENGTH_SHORT).show()
            homeViewModel.createTable(binding.tablelayout,loan.getAnnuityLoanList(args.loanData))
        } else {
            Toast.makeText(requireContext(), args.loanData.loanType, Toast.LENGTH_SHORT).show()
            homeViewModel.createTable(binding.tablelayout,loan.getSerialLoanList(args.loanData))
        }



    }



}