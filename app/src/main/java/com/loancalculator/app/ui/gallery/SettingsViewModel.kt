package com.loancalculator.app.ui.gallery

import android.app.Application
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import com.loancalculator.app.ui.sharedpref.SharedPref
import android.widget.CompoundButton
import androidx.lifecycle.*
import com.google.android.material.textview.MaterialTextView
import com.loancalculator.app.Constants
import com.loancalculator.app.MainActivity
import java.util.*


class SettingsViewModel (val context : Application) : AndroidViewModel(context) {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    val sharedPref = SharedPref(context)

    val _isColumn1Visible  = MutableLiveData<Boolean>().apply {
        value = sharedPref.getColumn1Visible()
    }

    val _isColumn2Visible  = MutableLiveData<Boolean>().apply {
        value = sharedPref.getColumn2Visible()
    }

    val _isColumn3Visible  = MutableLiveData<Boolean>().apply {
        value = sharedPref.getColumn3Visible()
    }

    val _isColumn4Visible  = MutableLiveData<Boolean>().apply {
        value = sharedPref.getColumn4Visible()
    }




    val isColumn1Visible : LiveData<Boolean> = Transformations.map(_isColumn1Visible){
       it
    }
    val isColumn2Visible : LiveData<Boolean> = Transformations.map(_isColumn2Visible){
        it
    }
    val isColumn3Visible : LiveData<Boolean> = Transformations.map(_isColumn3Visible){
        it
    }
    val isColumn4Visible : LiveData<Boolean> = Transformations.map(_isColumn4Visible){
        it
    }

    val _itemPosition  = MutableLiveData<Int>().apply {
        value =     if (sharedPref.getLanguage() == "English") {
            0
        } else {
            1
        }
    }

    val itemPosition : LiveData<Int> =  Transformations.map(_itemPosition){
        it
    }



    fun onCheckedChangeForColumn1(button: CompoundButton?, check: Boolean) {
      sharedPref.setColumn1Visible(check)
        _isColumn1Visible.postValue(check)
    }

    fun onCheckedChangeForColumn2(button: CompoundButton?, check: Boolean) {
        sharedPref.setColumn2Visible(check)
        _isColumn2Visible.postValue(check)
    }

    fun onCheckedChangeForColumn3(button: CompoundButton?, check: Boolean) {
        sharedPref.setColumn3Visible(check)
        _isColumn3Visible.postValue(check)
    }

    fun onCheckedChangeForColumn4(button: CompoundButton?, check: Boolean) {
        sharedPref.setColumn4Visible(check)
        _isColumn4Visible.postValue(check)
    }

}