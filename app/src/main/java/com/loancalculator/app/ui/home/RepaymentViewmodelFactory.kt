package com.loancalculator.app.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class RepaymentViewmodelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RepaymentViewModel::class.java)){
            return RepaymentViewModel(application) as T
        }
        throw IllegalArgumentException("Viewmodel class not found")
    }

}