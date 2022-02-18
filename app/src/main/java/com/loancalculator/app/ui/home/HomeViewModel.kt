package com.loancalculator.app.ui.home

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.*
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.loancalculator.app.R
import com.loancalculator.data.LoanData

class HomeViewModel (val context : Application) : AndroidViewModel(context) {

    private val _loanText = MutableLiveData<String>().apply {
        value = "10000"
    }
    val loanText: LiveData<String> = _loanText

    private val _itemPosition = MutableLiveData<String>().apply {
        value = context.getString(R.string.annuityloan)
    }
    val itemPosition: LiveData<String> =  Transformations.map(_itemPosition){
        it
    }

    private var _navigateToRepayFrag = MutableLiveData<LoanData>()
    var navigateToRepayFrag : LiveData<LoanData> = _navigateToRepayFrag

    private var _numOfYearsLive = MutableLiveData(12)
    var numOfYearsLive : LiveData<Int> =  Transformations.map(_numOfYearsLive){
        it
    }

    private var _interestLive = MutableLiveData(3.6f)
    var interestLive : LiveData<Float>  =  Transformations.map(_interestLive){
        it
    }


    fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        _loanText.postValue(p0.toString())
    }

    fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        p1?.let {
            val spinner = p1 as MaterialTextView
            _itemPosition.postValue(spinner.text.toString())
        }

    }


/*    fun onTextChanged(): TextWatcher {

        return object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               _loanText.postValue(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
    }*/

/*
    fun onLike(view : View){
        val action = HomeFragmentDirections.actionNavHomeToRepaymentListFragment()
        Navigation.findNavController(view).navigate(action)
     //   _navigateToRepayFrag.postValue()
    }*/

    var numOfYears = ""

    private var _seekbarData = MutableLiveData<String>()
    var seekbarData : LiveData<String> = Transformations.map(_seekbarData){
        it
    }

    fun onSeekBarChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        _interestLive.postValue(progress/10.toFloat())
    }

    fun onSeekBarChangedForYears(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
       numOfYears = progress.toString()
        _numOfYearsLive.postValue(progress)
    }


}